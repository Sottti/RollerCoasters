package com.sottti.roller.coasters.presentation.settings.data

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.System
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.GetAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.SetAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveAppDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.SetAppDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.language.GetAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
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
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.NoOp
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.SideEffect
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.StateMutation
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.utils.combine
import com.sottti.roller.coasters.presentation.utils.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    observeAppColorContrast: ObserveAppColorContrast,
    observeAppDynamicColor: ObserveAppDynamicColor,
    observeAppLanguage: ObserveAppLanguage,
    observeAppMeasurementSystem: ObserveAppMeasurementSystem,
    observeAppTheme: ObserveAppTheme,
    private val features: Features,
    private val getAppColorContrast: GetAppColorContrast,
    private val getAppLanguage: GetAppLanguage,
    private val getAppMeasurementSystem: GetAppMeasurementSystem,
    private val getAppTheme: GetAppTheme,
    private val setAppColorContrast: SetAppColorContrast,
    private val setAppDynamicColor: SetAppDynamicColor,
    private val setAppLanguage: SetAppLanguage,
    private val setAppMeasurementSystem: SetAppMeasurementSystem,
    private val setAppTheme: SetAppTheme,
    @get:VisibleForTesting private val testInitialState: SettingsState? = null,
) : ViewModel() {

    private val initialState =
        testInitialState ?: initialState(features.systemDynamicColorAvailable())
    private val stateMutations: MutableSharedFlow<StateMutation> =
        MutableSharedFlow(extraBufferCapacity = 64)

    internal val state: StateFlow<SettingsState> =
        combine(
            flow = stateMutations.onStart { emit(NoOp) },
            flow2 = observeAppColorContrast(),
            flow3 = observeAppDynamicColor(),
            flow4 = observeAppLanguage(),
            flow5 = observeAppMeasurementSystem(),
            flow6 = observeAppTheme(),
        ) { stateMutation, appColorContrast, appDynamicColor, appLanguage, appMeasurementSystem, appTheme ->
            reducer(
                appColorContrast,
                appDynamicColor,
                appLanguage,
                appMeasurementSystem,
                appTheme,
                stateMutation,
            )
        }
            .scan(initialState) { previous, reduce -> reduce(previous) }
            .drop(1)
            .distinctUntilChanged()
            .stateInWhileSubscribed(initialState)

    private val reducer: (
        appColorContrast: AppColorContrast,
        appDynamicColor: AppDynamicColor,
        appLanguage: AppLanguage,
        appMeasurementSystem: AppMeasurementSystem,
        appTheme: AppTheme,
        stateMutation: StateMutation,
    ) -> suspend (SettingsState) -> SettingsState =
        { appColorContrast, appDynamicColor, appLanguage, appMeasurementSystem, appTheme, stateMutation ->
            { previous: SettingsState ->
                previous
                    .reduceState(stateMutation)
                    .updateAppColorContrast(appColorContrast)
                    .updateDynamicColor(appDynamicColor)
                    .updateAppLanguage(appLanguage)
                    .updateAppMeasurementSystem(appMeasurementSystem)
                    .updateAppTheme(appTheme)
            }
        }

    internal val onAction: (SettingsAction) -> Unit = { action: SettingsAction ->
        if (action is StateMutation) {
            stateMutations.tryEmit(action)
        }

        if (action is SideEffect) {
            handleSideEffect(action)
        }
    }

    private fun handleSideEffect(action: SideEffect) {
        viewModelScope.launch {
            when (action) {
                is DynamicColorCheckedChange -> {
                    val appDynamicColor = when {
                        action.checked -> AppDynamicColor.Enabled
                        else -> AppDynamicColor.Disabled
                    }

                    setAppDynamicColor(appDynamicColor)
                    if (action.checked) setAppColorContrast(System)
                }

                is ConfirmAppThemePickerSelection -> setAppTheme(action.appTheme.toDomain())

                is ConfirmColorContrastPickerSelection ->
                    setAppColorContrast(action.appColorContrast.toDomain())

                is ConfirmAppLanguagePickerSelection ->{
                    setAppLanguage(action.appLanguage.toDomain())
                }

                is ConfirmAppMeasurementSystemPickerSelection ->
                    setAppMeasurementSystem(action.appMeasurementSystem.toDomain())
            }
        }
    }

    private suspend fun SettingsState.reduceState(
        action: StateMutation,
    ): SettingsState =
        when (action) {
            LaunchAppThemePicker -> showAppThemePicker(
                lightDarkAppThemingAvailable = features.lightDarkSystemThemingAvailable(),
                selectedAppTheme = getAppTheme().toPresentationModel(selected = true),
            )

            is AppThemePickerSelectionChange ->
                updateAppThemePicker(selectedAppTheme = action.appTheme)

            is ConfirmAppThemePickerSelection -> hideAppThemePicker()
            DismissAppThemePicker -> hideAppThemePicker()

            LaunchAppColorContrastPicker -> showAppColorContrastPicker(
                selectedAppColorContrast = getAppColorContrast(),
                appColorContrastAvailable = features.systemColorContrastAvailable(),
            )

            is AppColorContrastPickerSelectionChange -> updateAppColorContrastPicker(
                appColorContrastAvailable = features.systemColorContrastAvailable(),
                selectedAppColorContrast = action.appColorContrast,
            )

            is ConfirmColorContrastPickerSelection -> hideAppColorContrastPicker()
            DismissAppColorContrastPicker -> hideAppColorContrastPicker()
            DismissAppColorContrastNotAvailableMessage -> hideAppColorContrastNotAvailableMessage()

            LaunchAppLanguagePicker -> showAppLanguagePicker(getAppLanguage())
            is AppLanguagePickerSelectionChange -> updateAppLanguagePicker(action.appLanguage)
            is ConfirmAppLanguagePickerSelection -> hideAppLanguagePicker()
            DismissAppLanguagePicker -> hideAppLanguagePicker()

            LaunchAppMeasurementSystemPicker ->
                showAppMeasurementSystemPicker(getAppMeasurementSystem())

            is AppMeasurementSystemPickerSelectionChange ->
                updateAppMeasurementSystemPicker(action.appMeasurementSystem)

            is ConfirmAppMeasurementSystemPickerSelection -> hideAppMeasurementSystemPicker()
            DismissAppMeasurementSystemPicker -> hideAppMeasurementSystemPicker()

            NoOp -> this
        }
}
