package com.sottti.roller.coasters.presentation.settings.data

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.settings.usecase.GetColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.GetLanguage
import com.sottti.roller.coasters.domain.settings.usecase.GetMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.GetTheme
import com.sottti.roller.coasters.domain.settings.usecase.ObserveColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.ObserveDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.ObserveMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.ObserveTheme
import com.sottti.roller.coasters.domain.settings.usecase.SetColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.SetDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.SetLanguage
import com.sottti.roller.coasters.domain.settings.usecase.SetMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.SetTheme
import com.sottti.roller.coasters.presentation.settings.data.mapper.toDomain
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateColorContrast
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateLanguage
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateMeasurementSystem
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateTheme
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ColorContrastPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmColorContrastPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmLanguagePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmMeasurementSystemPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LanguagePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.MeasurementSystemPickerSelectionChange
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
    private val getColorContrast: GetColorContrast,
    private val getLanguage: GetLanguage,
    private val getMeasurementSystem: GetMeasurementSystem,
    private val getTheme: GetTheme,
    private val observeColorContrast: ObserveColorContrast,
    private val observeDynamicColor: ObserveDynamicColor,
    private val observeMeasurementSystem: ObserveMeasurementSystem,
    private val observeTheme: ObserveTheme,
    private val sdkFeatures: SdkFeatures,
    private val setColorContrast: SetColorContrast,
    private val setDynamicColor: SetDynamicColor,
    private val setLanguage: SetLanguage,
    private val setMeasurementSystem: SetMeasurementSystem,
    private val setTheme: SetTheme,
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
            collectDynamicColor()
        }
        collectTheme()
        collectColorContrast()
        updateLanguage()
        collectMeasurementSystem()
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

    private fun collectDynamicColor() {
        viewModelScope.launch {
            observeDynamicColor()
                .collect { dynamicColorChecked -> _state.updateDynamicColor(dynamicColorChecked) }
        }
    }

    private fun collectTheme() {
        viewModelScope.launch {
            observeTheme()
                .collect { theme -> _state.updateTheme(theme) }
        }
    }

    private fun collectColorContrast() {
        viewModelScope.launch {
            observeColorContrast()
                .collect { colorContrast -> _state.updateColorContrast(colorContrast) }
        }
    }

    private fun updateLanguage() {
        viewModelScope.launch {
            _state.updateLanguage(getLanguage())
        }
    }

    private fun collectMeasurementSystem() {
        viewModelScope.launch {
            observeMeasurementSystem()
                .collect { measurementSystem -> _state.updateMeasurementSystem(measurementSystem) }
        }
    }

    private fun processAction(action: SettingsAction) {
        viewModelScope.launch {
            when (action) {
                is DynamicColorCheckedChange -> {
                    setDynamicColor(action.checked)
                    if (action.checked == true) {
                        setColorContrast(SystemContrast)
                    }
                }

                LaunchThemePicker -> {
                    val lightDarkSystemThemingAvailable =
                        sdkFeatures.lightDarkSystemThemingAvailable()
                    val theme = getTheme().toPresentationModel(isSelected = true)
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
                    setTheme(action.theme.toDomain())
                }

                DismissThemePicker -> _state.hideThemePicker()

                LaunchColorContrastPicker -> {
                    _state.showColorContrastPicker(
                        colorContrast = getColorContrast(),
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
                    setColorContrast(action.contrast.toDomain())
                }

                DismissColorContrastPicker -> _state.hideColorContrastPicker()

                DismissColorContrastNotAvailableMessage ->
                    _state.hideColorContrastNotAvailableMessage()

                LaunchLanguagePicker -> _state.showLanguagePicker(getLanguage())

                is LanguagePickerSelectionChange -> _state.updateLanguagePicker(action.language)

                is ConfirmLanguagePickerSelection -> {
                    _state.hideLanguagePicker()
                    setLanguage(action.language.toDomain())
                    updateLanguage()
                }

                DismissLanguagePicker -> _state.hideLanguagePicker()

                LaunchMeasurementSystemPicker ->
                    _state.showMeasurementSystemPicker(getMeasurementSystem())

                is MeasurementSystemPickerSelectionChange ->
                    _state.updateMeasurementSystemPicker(action.measurementSystem)

                is ConfirmMeasurementSystemPickerSelection -> {
                    _state.hideMeasurementSystemPicker()
                    setMeasurementSystem(action.measurementSystem.toDomain())
                }

                DismissMeasurementSystemPicker -> _state.hideMeasurementSystemPicker()

            }
        }
    }
}