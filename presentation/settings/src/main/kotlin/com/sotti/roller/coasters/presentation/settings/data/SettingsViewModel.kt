package com.sotti.roller.coasters.presentation.settings.data

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sotti.roller.coasters.domain.system.features.SystemFeatures
import com.sotti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sotti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.System
import com.sotti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sotti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sotti.roller.coasters.domain.settings.usecase.colorContrast.GetAppColorContrast
import com.sotti.roller.coasters.domain.settings.usecase.colorContrast.ObserveAppColorContrast
import com.sotti.roller.coasters.domain.settings.usecase.colorContrast.SetAppColorContrast
import com.sotti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveAppDynamicColor
import com.sotti.roller.coasters.domain.settings.usecase.dynamicColor.SetAppDynamicColor
import com.sotti.roller.coasters.domain.settings.usecase.language.GetAppLanguage
import com.sotti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sotti.roller.coasters.domain.settings.usecase.language.SetAppLanguage
import com.sotti.roller.coasters.domain.settings.usecase.measurementSystem.GetAppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveAppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.usecase.measurementSystem.SetAppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.usecase.theme.GetAppTheme
import com.sotti.roller.coasters.domain.settings.usecase.theme.ObserveAppTheme
import com.sotti.roller.coasters.domain.settings.usecase.theme.SetAppTheme
import com.sotti.roller.coasters.presentation.settings.data.mapper.toDomain
import com.sotti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sotti.roller.coasters.presentation.settings.data.reducer.hideAppColorContrastNotAvailableMessage
import com.sotti.roller.coasters.presentation.settings.data.reducer.hideAppColorContrastPicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.hideAppLanguagePicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.hideAppMeasurementSystemPicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.hideAppThemePicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.showAppColorContrastPicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.showAppLanguagePicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.showAppMeasurementSystemPicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.showAppThemePicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrast
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrastPicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppLanguage
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppLanguagePicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystem
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystemPicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppTheme
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppThemePicker
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastPickerSelectionChange
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguagePickerSelectionChange
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemPickerSelectionChange
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.AppThemePickerSelectionChange
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppLanguagePickerSelection
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppMeasurementSystemPickerSelection
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppThemePickerSelection
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmColorContrastPickerSelection
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppColorContrastNotAvailableMessage
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppColorContrastPicker
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppLanguagePicker
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppMeasurementSystemPicker
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppThemePicker
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppColorContrastPicker
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppLanguagePicker
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppMeasurementSystemPicker
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.LaunchAppThemePicker
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.NoOp
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.SideEffectAction
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.StateMutationAction
import com.sotti.roller.coasters.presentation.settings.model.SettingsState
import com.sotti.roller.coasters.presentation.utils.combine
import com.sotti.roller.coasters.presentation.utils.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val systemFeatures: SystemFeatures,
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
        testInitialState ?: initialState(systemFeatures.systemDynamicColorAvailable())
    private val stateMutationActionActions: MutableSharedFlow<StateMutationAction> =
        MutableSharedFlow(extraBufferCapacity = 64)

    internal val state: StateFlow<SettingsState> =
        combine(
            flow = stateMutationActionActions.onStart { emit(NoOp) },
            flow2 = observeAppColorContrast(),
            flow3 = observeAppDynamicColor(),
            flow4 = observeAppLanguage(),
            flow5 = observeAppMeasurementSystem(),
            flow6 = observeAppTheme(),
        ) { stateMutationAction, appColorContrast, appDynamicColor, appLanguage, appMeasurementSystem, appTheme ->
            reducer(
                appColorContrast,
                appDynamicColor,
                appLanguage,
                appMeasurementSystem,
                appTheme,
                stateMutationAction,
            )
        }
            .scan(initialState) { previous, reduce -> reduce(previous) }
            .drop(1)
            .stateInWhileSubscribed(initialState)

    private val reducer: (
        appColorContrast: AppColorContrast,
        appDynamicColor: AppDynamicColor,
        appLanguage: AppLanguage,
        appMeasurementSystem: AppMeasurementSystem,
        appTheme: AppTheme,
        stateMutationAction: StateMutationAction,
    ) -> suspend (SettingsState) -> SettingsState =
        { appColorContrast, appDynamicColor, appLanguage, appMeasurementSystem, appTheme, stateMutationAction ->
            { previous: SettingsState ->
                previous
                    .handleStateMutationAction(stateMutationAction)
                    .updateAppColorContrast(appColorContrast)
                    .updateDynamicColor(appDynamicColor)
                    .updateAppLanguage(appLanguage)
                    .updateAppMeasurementSystem(appMeasurementSystem)
                    .updateAppTheme(appTheme)
            }
        }

    internal val onAction: (SettingsAction) -> Unit = { action: SettingsAction ->
        if (action is StateMutationAction) {
            stateMutationActionActions.tryEmit(action)
        }

        if (action is SideEffectAction) {
            handleSideEffectAction(action)
        }
    }

    private fun handleSideEffectAction(action: SideEffectAction) {
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

                is ConfirmAppLanguagePickerSelection ->
                    setAppLanguage(action.appLanguage.toDomain())

                is ConfirmAppMeasurementSystemPickerSelection ->
                    setAppMeasurementSystem(action.appMeasurementSystem.toDomain())
            }
        }
    }

    private suspend fun SettingsState.handleStateMutationAction(
        action: StateMutationAction,
    ): SettingsState =
        when (action) {
            LaunchAppThemePicker -> showAppThemePicker(
                lightDarkAppThemingAvailable = systemFeatures.lightDarkSystemThemingAvailable(),
                selectedAppTheme = getAppTheme().toPresentationModel(selected = true),
            )

            is AppThemePickerSelectionChange ->
                updateAppThemePicker(selectedAppTheme = action.appTheme)

            is ConfirmAppThemePickerSelection -> hideAppThemePicker()

            DismissAppThemePicker -> hideAppThemePicker()

            LaunchAppColorContrastPicker -> showAppColorContrastPicker(
                selectedAppColorContrast = getAppColorContrast(),
                appColorContrastAvailable = systemFeatures.systemColorContrastAvailable(),
            )

            is AppColorContrastPickerSelectionChange -> updateAppColorContrastPicker(
                appColorContrastAvailable = systemFeatures.systemColorContrastAvailable(),
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
