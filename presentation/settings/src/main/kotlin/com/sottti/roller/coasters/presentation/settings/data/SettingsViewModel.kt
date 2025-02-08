package com.sottti.roller.coasters.presentation.settings.data

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.data.settings.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import com.sottti.roller.coasters.presentation.settings.data.mapper.toDomain
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateColorContrast
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateLanguage
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateTheme
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ColorContrastPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmColorContrastPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmLanguagePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LanguagePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ThemePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val application: Application,
    private val sdkFeatures: SdkFeatures,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState(sdkFeatures.dynamicColorAvailable()))
    internal val state: StateFlow<SettingsState> = _state.asStateFlow()
    internal val onAction: (SettingsAction) -> Unit = { action -> processAction(action) }
    private val configObserver = object : ComponentCallbacks {
        override fun onConfigurationChanged(newConfig: Configuration) = updateLanguage()
        override fun onLowMemory() = Unit
    }

    init {
        if (sdkFeatures.dynamicColorAvailable()) {
            observeDynamicColor()
        }
        observeTheme()
        observeColorContrast()
        updateLanguage()
        registerForConfigChanges()
    }

    override fun onCleared() {
        unregisterForConfigChanges()
        super.onCleared()
    }

    private fun registerForConfigChanges() {
        application.registerComponentCallbacks(configObserver)
    }

    private fun unregisterForConfigChanges() {
        application.unregisterComponentCallbacks(configObserver)
    }

    private fun observeDynamicColor() {
        viewModelScope.launch {
            settingsRepository
                .observeDynamicColor()
                .collect { dynamicColorChecked -> _state.updateDynamicColor(dynamicColorChecked) }
        }
    }

    private fun observeTheme() {
        viewModelScope.launch {
            settingsRepository
                .observeTheme()
                .collect { theme -> _state.updateTheme(theme) }
        }
    }

    private fun observeColorContrast() {
        viewModelScope.launch {
            settingsRepository
                .observeColorContrast()
                .collect { colorContrast -> _state.updateColorContrast(colorContrast) }
        }
    }

    private fun updateLanguage() {
        viewModelScope.launch {
            _state.updateLanguage(settingsRepository.getLanguage())
        }
    }

    private fun processAction(action: SettingsAction) {
        viewModelScope.launch {
            when (action) {
                is DynamicColorCheckedChange -> {
                    settingsRepository.setDynamicColor(action.checked)
                    if (action.checked == true) {
                        settingsRepository.setColorContrast(SystemContrast)
                    }
                }

                LaunchThemePicker -> {
                    val lightDarkSystemThemingAvailable =
                        sdkFeatures.lightDarkSystemThemingAvailable()
                    val theme = settingsRepository.getTheme().toPresentationModel(isSelected = true)
                    _state
                        .showThemePicker(
                            lightDarkThemeThemingAvailable = lightDarkSystemThemingAvailable,
                            theme = theme,
                        )
                }

                is ThemePickerSelectionChange -> _state.updateThemePicker(
                    lightDarkThemeThemingAvailable = sdkFeatures.lightDarkSystemThemingAvailable(),
                    theme = action.theme,
                )

                is ConfirmThemePickerSelection -> {
                    _state.hideThemePicker()
                    settingsRepository.setTheme(action.theme.toDomain())
                }

                DismissThemePicker -> _state.hideThemePicker()

                LaunchColorContrastPicker -> {
                    _state.showColorContrastPicker(
                        colorContrast = settingsRepository.getColorContrast(),
                        colorContrastAvailable = sdkFeatures.colorContrastAvailable(),
                    )
                }

                is ColorContrastPickerSelectionChange -> {
                    _state.updateColorContrastPicker(
                        colorContrastAvailable = sdkFeatures.colorContrastAvailable(),
                        selectedContrast = action.contrast,
                    )
                }

                is ConfirmColorContrastPickerSelection -> {
                    _state.hideColorContrastPicker()
                    settingsRepository.setColorContrast(action.contrast.toDomain())
                }

                DismissColorContrastPicker -> _state.hideColorContrastPicker()
                DismissColorContrastNotAvailableMessage ->
                    _state.hideColorContrastNotAvailableMessage()

                LaunchLanguagePicker -> _state.showLanguagePicker(settingsRepository.getLanguage())

                is LanguagePickerSelectionChange -> _state.updateLanguagePicker(action.language)
                is ConfirmLanguagePickerSelection -> {
                    _state.hideLanguagePicker()
                    settingsRepository.setLanguage(action.language.toDomain())
                    updateLanguage()
                }

                DismissLanguagePicker -> _state.hideLanguagePicker()
            }
        }
    }
}