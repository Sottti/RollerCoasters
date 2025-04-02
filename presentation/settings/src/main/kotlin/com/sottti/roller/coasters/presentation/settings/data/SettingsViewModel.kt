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
import com.sottti.roller.coasters.domain.settings.usecase.language.GetAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.SetAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.SetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.theme.GetAppTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.ObserveAppTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.SetAppTheme
import com.sottti.roller.coasters.presentation.settings.data.mapper.toDomain
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrast
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppLanguage
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystem
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppTheme
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguagePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppLanguagePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppMeasurementSystemPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmColorContrastPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppThemePicker
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
    private val features: Features,
    private val getAppColorContrast: GetAppColorContrast,
    private val getAppLanguage: GetAppLanguage,
    private val getAppMeasurementSystem: GetAppMeasurementSystem,
    private val getAppTheme: GetAppTheme,
    private val observeAppColorContrast: ObserveAppColorContrast,
    private val observeAppDynamicColor: ObserveAppDynamicColor,
    private val observeAppMeasurementSystem: ObserveAppMeasurementSystem,
    private val observeAppTheme: ObserveAppTheme,
    private val setAppColorContrast: SetAppColorContrast,
    private val setAppDynamicColor: SetAppDynamicColor,
    private val setAppLanguage: SetAppLanguage,
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
                .collect { theme -> _state.updateAppTheme(theme) }
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
            _state.updateAppLanguage(getAppLanguage())
        }
    }

    private fun collectMeasurementSystem() {
        viewModelScope.launch {
            observeAppMeasurementSystem()
                .collect { measurementSystem -> _state.updateAppMeasurementSystem(measurementSystem) }
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

                LaunchAppThemePicker -> {
                    val lightDarkSystemThemingAvailable =
                        features.lightDarkSystemThemingAvailable()
                    val theme = getAppTheme().toPresentationModel(selected = true)
                    _state
                        .showAppThemePicker(
                            selectedLightDarkAppThemingAvailable = lightDarkSystemThemingAvailable,
                            theme = theme,
                        )
                }

                is AppThemePickerSelectionChange -> _state.updateAppThemePicker(
                    selectedLightDarkAppThemingAvailable = features.lightDarkSystemThemingAvailable(),
                    theme = action.appTheme,
                )

                is ConfirmAppThemePickerSelection -> {
                    _state.hideAppThemePicker()
                    setAppTheme(action.appTheme.toDomain())
                }

                DismissAppThemePicker -> _state.hideAppThemePicker()

                LaunchAppColorContrastPicker -> {
                    _state.showAppColorContrastPicker(
                        appColorContrast = getAppColorContrast(),
                        appColorContrastAvailable = features.systemColorContrastAvailable(),
                    )
                }

                is AppColorContrastPickerSelectionChange -> {
                    _state.updateAppColorContrastPicker(
                        appColorContrastAvailable = features.systemColorContrastAvailable(),
                        selectedAppColorContrast = action.appColorContrast,
                    )
                }

                is ConfirmColorContrastPickerSelection -> {
                    _state.hideAppColorContrastPicker()
                    setAppColorContrast(action.appColorContrast.toDomain())
                }

                DismissAppColorContrastPicker -> _state.hideAppColorContrastPicker()

                DismissAppColorContrastNotAvailableMessage ->
                    _state.hideAppColorContrastNotAvailableMessage()

                LaunchAppLanguagePicker -> _state.showAppLanguagePicker(getAppLanguage())

                is AppLanguagePickerSelectionChange -> _state.updateAppLanguagePicker(action.appLanguage)

                is ConfirmAppLanguagePickerSelection -> {
                    _state.hideAppLanguagePicker()
                    setAppLanguage(action.appLanguage.toDomain())
                    updateLanguage()
                }

                DismissAppLanguagePicker -> _state.hideAppLanguagePicker()

                LaunchAppMeasurementSystemPicker ->
                    _state.showAppMeasurementSystemPicker(getAppMeasurementSystem())

                is AppMeasurementSystemPickerSelectionChange ->
                    _state.updateAppMeasurementSystemPicker(action.appMeasurementSystem)

                is ConfirmAppMeasurementSystemPickerSelection -> {
                    _state.hideAppMeasurementSystemPicker()
                    setAppMeasurementSystem(action.appMeasurementSystem.toDomain())
                }

                DismissAppMeasurementSystemPicker -> _state.hideAppMeasurementSystemPicker()

            }
        }
    }
}