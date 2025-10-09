package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface SettingsAction {

    @Immutable
    sealed interface SideEffect : SettingsAction

    @Immutable
    sealed interface StateMutation : SettingsAction

    @Immutable
    data object NoOp : StateMutation

    @Immutable
    data class DynamicColorCheckedChange(
        val checked: Boolean,
    ) : SettingsAction, SideEffect

    @Immutable
    data object LaunchAppThemePicker : StateMutation

    @Immutable
    data class AppThemePickerSelectionChange(
        val appTheme: AppThemeUi,
    ) : StateMutation

    @Immutable
    data class ConfirmAppThemePickerSelection(
        val appTheme: AppThemeUi,
    ) : StateMutation, SideEffect

    @Immutable
    data object DismissAppThemePicker : StateMutation

    @Immutable
    data object LaunchAppColorContrastPicker : StateMutation

    @Immutable
    data class AppColorContrastPickerSelectionChange(
        val appColorContrast: AppColorContrastUi,
    ) : StateMutation

    @Immutable
    data class ConfirmColorContrastPickerSelection(
        val appColorContrast: AppColorContrastUi,
    ) : StateMutation, SideEffect

    @Immutable
    data object DismissAppColorContrastPicker : StateMutation

    @Immutable
    data object DismissAppColorContrastNotAvailableMessage : StateMutation

    @Immutable
    data object LaunchAppLanguagePicker : StateMutation

    @Immutable
    data class AppLanguagePickerSelectionChange(
        val appLanguage: AppLanguageUi,
    ) : StateMutation

    @Immutable
    data class ConfirmAppLanguagePickerSelection(
        val appLanguage: AppLanguageUi,
    ) : StateMutation, SideEffect

    @Immutable
    data object DismissAppLanguagePicker : StateMutation

    @Immutable
    data object LaunchAppMeasurementSystemPicker : StateMutation

    @Immutable
    data class AppMeasurementSystemPickerSelectionChange(
        val appMeasurementSystem: AppMeasurementSystemUi,
    ) : StateMutation

    @Immutable
    data class ConfirmAppMeasurementSystemPickerSelection(
        val appMeasurementSystem: AppMeasurementSystemUi,
    ) : StateMutation, SideEffect

    @Immutable
    data object DismissAppMeasurementSystemPicker : StateMutation
}
