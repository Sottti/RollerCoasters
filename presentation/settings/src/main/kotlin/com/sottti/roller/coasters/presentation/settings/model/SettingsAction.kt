package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class SettingsAction {
    data class DynamicColorCheckedChange(val checked: Boolean) : SettingsAction()

    data object LaunchAppThemePicker : SettingsAction()
    data class AppThemePickerSelectionChange(val appTheme: AppThemeUi) : SettingsAction()
    data class ConfirmAppThemePickerSelection(val appTheme: AppThemeUi) : SettingsAction()
    data object DismissAppThemePicker : SettingsAction()

    data object LaunchAppColorContrastPicker : SettingsAction()
    data class AppColorContrastPickerSelectionChange(
        val appColorContrast: AppColorContrastUi,
    ) : SettingsAction()

    data class ConfirmColorContrastPickerSelection(
        val appColorContrast: AppColorContrastUi,
    ) : SettingsAction()

    data object DismissAppColorContrastPicker : SettingsAction()
    data object DismissAppColorContrastNotAvailableMessage : SettingsAction()

    data object LaunchAppLanguagePicker : SettingsAction()
    data class AppLanguagePickerSelectionChange(val appLanguage: AppLanguageUi) : SettingsAction()
    data class ConfirmAppLanguagePickerSelection(val appLanguage: AppLanguageUi) : SettingsAction()
    data object DismissAppLanguagePicker : SettingsAction()

    data object LaunchAppMeasurementSystemPicker : SettingsAction()
    data class AppMeasurementSystemPickerSelectionChange(
        val appMeasurementSystem: AppMeasurementSystemUi,
    ) : SettingsAction()

    data class ConfirmAppMeasurementSystemPickerSelection(
        val appMeasurementSystem: AppMeasurementSystemUi,
    ) : SettingsAction()

    data object DismissAppMeasurementSystemPicker : SettingsAction()
}