package com.sotti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface SettingsAction {

    @Immutable
    sealed interface SideEffectAction : SettingsAction

    @Immutable
    sealed interface StateMutationAction : SettingsAction

    @Immutable
    data object NoOp : StateMutationAction

    @Immutable
    data class DynamicColorCheckedChange(
        val checked: Boolean,
    ) : SettingsAction, SideEffectAction

    @Immutable
    data object LaunchAppThemePicker : StateMutationAction

    @Immutable
    data class AppThemePickerSelectionChange(
        val appTheme: AppThemeUi,
    ) : StateMutationAction

    @Immutable
    data class ConfirmAppThemePickerSelection(
        val appTheme: AppThemeUi,
    ) : StateMutationAction, SideEffectAction

    @Immutable
    data object DismissAppThemePicker : StateMutationAction

    @Immutable
    data object LaunchAppColorContrastPicker : StateMutationAction

    @Immutable
    data class AppColorContrastPickerSelectionChange(
        val appColorContrast: AppColorContrastUi,
    ) : StateMutationAction

    @Immutable
    data class ConfirmColorContrastPickerSelection(
        val appColorContrast: AppColorContrastUi,
    ) : StateMutationAction, SideEffectAction

    @Immutable
    data object DismissAppColorContrastPicker : StateMutationAction

    @Immutable
    data object DismissAppColorContrastNotAvailableMessage : StateMutationAction

    @Immutable
    data object LaunchAppLanguagePicker : StateMutationAction

    @Immutable
    data class AppLanguagePickerSelectionChange(
        val appLanguage: AppLanguageUi,
    ) : StateMutationAction

    @Immutable
    data class ConfirmAppLanguagePickerSelection(
        val appLanguage: AppLanguageUi,
    ) : StateMutationAction, SideEffectAction

    @Immutable
    data object DismissAppLanguagePicker : StateMutationAction

    @Immutable
    data object LaunchAppMeasurementSystemPicker : StateMutationAction

    @Immutable
    data class AppMeasurementSystemPickerSelectionChange(
        val appMeasurementSystem: AppMeasurementSystemUi,
    ) : StateMutationAction

    @Immutable
    data class ConfirmAppMeasurementSystemPickerSelection(
        val appMeasurementSystem: AppMeasurementSystemUi,
    ) : StateMutationAction, SideEffectAction

    @Immutable
    data object DismissAppMeasurementSystemPicker : StateMutationAction
}
