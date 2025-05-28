package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class SettingsAction {
    @Immutable
    data class DynamicColorCheckedChange(val checked: Boolean) : SettingsAction()

    @Immutable
    sealed class AppThemeActions : SettingsAction() {
        data object LaunchAppThemePicker : AppThemeActions()
        data class AppThemePickerSelectionChange(val appTheme: AppThemeUi) : AppThemeActions()
        data class ConfirmAppThemePickerSelection(val appTheme: AppThemeUi) : AppThemeActions()
        data object DismissAppThemePicker : AppThemeActions()
    }

    @Immutable
    sealed class AppColorContrastActions : SettingsAction() {
        @Immutable
        data object LaunchAppColorContrastPicker : AppColorContrastActions()
        @Immutable
        data class AppColorContrastPickerSelectionChange(
            val appColorContrast: AppColorContrastUi,
        ) : AppColorContrastActions()

        @Immutable
        data class ConfirmColorContrastPickerSelection(
            val appColorContrast: AppColorContrastUi,
        ) : AppColorContrastActions()

        @Immutable
        data object DismissAppColorContrastPicker : AppColorContrastActions()
        @Immutable
        data object DismissAppColorContrastNotAvailableMessage : AppColorContrastActions()
    }

    sealed class AppLanguageActions : SettingsAction() {
        @Immutable
        data object LaunchAppLanguagePicker : AppLanguageActions()
        @Immutable
        data class AppLanguagePickerSelectionChange(
            val appLanguage: AppLanguageUi,
        ) : AppLanguageActions()

        @Immutable
        data class ConfirmAppLanguagePickerSelection(
            val appLanguage: AppLanguageUi,
        ) : AppLanguageActions()

        @Immutable
        data object DismissAppLanguagePicker : AppLanguageActions()
    }

    @Immutable
    sealed class AppMeasurementSystemActions : SettingsAction() {
        @Immutable
        data object LaunchAppMeasurementSystemPicker : AppMeasurementSystemActions()
        @Immutable
        data class AppMeasurementSystemPickerSelectionChange(
            val appMeasurementSystem: AppMeasurementSystemUi,
        ) : AppMeasurementSystemActions()

        @Immutable
        data class ConfirmAppMeasurementSystemPickerSelection(
            val appMeasurementSystem: AppMeasurementSystemUi,
        ) : AppMeasurementSystemActions()

        @Immutable
        data object DismissAppMeasurementSystemPicker : AppMeasurementSystemActions()
    }
}