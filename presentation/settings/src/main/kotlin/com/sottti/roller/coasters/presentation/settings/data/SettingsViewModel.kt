package com.sottti.roller.coasters.presentation.settings.data

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.GetAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.SetAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveAppDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.SetAppDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.language.GetLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.SetLanguage
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.SetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.theme.GetAppTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.ObserveAppTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.SetAppTheme
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
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrast
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val application: Application,
    private val getAppColorContrast: GetAppColorContrast,
    private val getLanguage: GetLanguage,
    private val getAppMeasurementSystem: GetAppMeasurementSystem,
    private val getAppTheme: GetAppTheme,
    private val observeAppColorContrast: ObserveAppColorContrast,
    private val observeAppDynamicColor: ObserveAppDynamicColor,
    private val observeAppMeasurementSystem: ObserveAppMeasurementSystem,
    private val observeAppTheme: ObserveAppTheme,
    private val features: Features,
    private val setAppColorContrast: SetAppColorContrast,
    private val setAppDynamicColor: SetAppDynamicColor,
    private val setLanguage: SetLanguage,
    private val setAppMeasurementSystem: SetAppMeasurementSystem,
    private val setAppTheme: SetAppTheme,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState(features.systemDynamicColorAvailable()))
    internal val state: StateFlow<SettingsState> = _state.asStateFlow()
    internal val onAction: (SettingsAction) -> Unit = { action -> processAction(action) }
    private val configObserver = object : ComponentCallbacks {
        override fun onConfigurationChanged(newConfig: Configuration) = updateLanguage()
        override fun onLowMemory() = Unit
    }

    init {
        if (features.systemDynamicColorAvailable()) {
            collectAppDynamicColor()
        }
        collectTheme()
        collectAppColorContrast()
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

    private fun collectAppDynamicColor() {
        viewModelScope.launch {
            observeAppDynamicColor()
                .collect { dynamicColorChecked -> _state.updateDynamicColor(dynamicColorChecked) }
        }
    }

    private fun collectTheme() {
        viewModelScope.launch {
            observeAppTheme()
                .collect { theme -> _state.updateTheme(theme) }
        }
    }

    private fun collectAppColorContrast() {
        viewModelScope.launch {
            observeAppColorContrast()
                .collect { appColorContrast -> _state.updateAppColorContrast(appColorContrast) }
        }
    }

    private fun updateLanguage() {
        viewModelScope.launch {
            _state.updateLanguage(getLanguage())
        }
    }

    private fun collectMeasurementSystem() {
        viewModelScope.launch {
            observeAppMeasurementSystem()
                .collect { measurementSystem -> _state.updateMeasurementSystem(measurementSystem) }
        }
    }

    private fun processAction(action: SettingsAction) {
        viewModelScope.launch {
            when (action) {
                is DynamicColorCheckedChange -> {
                    val appDynamicColor = when {
                        action.checked -> AppDynamicColor.Enabled
                        else -> AppDynamicColor.Disabled
                    }
                    setAppDynamicColor(appDynamicColor)
                    if (action.checked) {
                        setAppColorContrast(SystemContrast)
                    }
                }

                LaunchThemePicker -> {
                    val lightDarkSystemThemingAvailable =
                        features.lightDarkSystemThemingAvailable()
                    val theme = getAppTheme().toPresentationModel(isSelected = true)
                    _state
                        .showThemePicker(
                            lightDarkThemeThemingAvailable = lightDarkSystemThemingAvailable,
                            theme = theme,
                        )
                }

                is ThemePickerSelectionChange -> _state.updateThemePicker(
                    lightDarkThemeThemingAvailable = features.lightDarkSystemThemingAvailable(),
                    theme = action.theme,
                )

                is ConfirmThemePickerSelection -> {
                    _state.hideThemePicker()
                    setAppTheme(action.theme.toDomain())
                }

                DismissThemePicker -> _state.hideThemePicker()

                LaunchColorContrastPicker -> {
                    _state.showColorContrastPicker(
                        appColorContrast = getAppColorContrast(),
                        colorContrastAvailable = features.systemColorContrastAvailable(),
                    )
                }

                is ColorContrastPickerSelectionChange -> {
                    _state.updateColorContrastPicker(
                        colorContrastAvailable = features.systemColorContrastAvailable(),
                        selectedContrast = action.contrast,
                    )
                }

                is ConfirmColorContrastPickerSelection -> {
                    _state.hideColorContrastPicker()
                    setAppColorContrast(action.contrast.toDomain())
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
                    _state.showMeasurementSystemPicker(getAppMeasurementSystem())

                is MeasurementSystemPickerSelectionChange ->
                    _state.updateMeasurementSystemPicker(action.measurementSystem)

                is ConfirmMeasurementSystemPickerSelection -> {
                    _state.hideMeasurementSystemPicker()
                    setAppMeasurementSystem(action.measurementSystem.toDomain())
                }

                DismissMeasurementSystemPicker -> _state.hideMeasurementSystemPicker()

            }
        }
    }
}