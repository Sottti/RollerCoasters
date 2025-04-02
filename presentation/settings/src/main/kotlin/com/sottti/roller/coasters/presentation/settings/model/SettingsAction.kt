package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class SettingsAction {
    data class DynamicColorCheckedChange(val checked: Boolean) : SettingsAction()

    sealed class AppThemeActions : SettingsAction() {
        data object LaunchAppThemePicker : AppThemeActions()
        data class AppThemePickerSelectionChange(val appTheme: AppThemeUi) : AppThemeActions()
        data class ConfirmAppThemePickerSelection(val appTheme: AppThemeUi) : AppThemeActions()
        data object DismissAppThemePicker : AppThemeActions()
    }

    sealed class AppColorContrastActions : SettingsAction() {
        data object LaunchAppColorContrastPicker : AppColorContrastActions()
        data class AppColorContrastPickerSelectionChange(
            val appColorContrast: AppColorContrastUi,
        ) : AppColorContrastActions()

        data class ConfirmColorContrastPickerSelection(
            val appColorContrast: AppColorContrastUi,
        ) : AppColorContrastActions()

        data object DismissAppColorContrastPicker : AppColorContrastActions()
        data object DismissAppColorContrastNotAvailableMessage : AppColorContrastActions()
    }

    sealed class AppLanguageActions : SettingsAction() {
        data object LaunchAppLanguagePicker : AppLanguageActions()
        data class AppLanguagePickerSelectionChange(
            val appLanguage: AppLanguageUi,
        ) : AppLanguageActions()

        data class ConfirmAppLanguagePickerSelection(
            val appLanguage: AppLanguageUi,
        ) : AppLanguageActions()

        data object DismissAppLanguagePicker : AppLanguageActions()
    }

    sealed class AppMeasurementSystemActions : SettingsAction() {
        data object LaunchAppMeasurementSystemPicker : AppMeasurementSystemActions()
        data class AppMeasurementSystemPickerSelectionChange(
            val appMeasurementSystem: AppMeasurementSystemUi,
        ) : AppMeasurementSystemActions()

        data class ConfirmAppMeasurementSystemPickerSelection(
            val appMeasurementSystem: AppMeasurementSystemUi,
        ) : AppMeasurementSystemActions()

        data object DismissAppMeasurementSystemPicker : AppMeasurementSystemActions()
    }
}