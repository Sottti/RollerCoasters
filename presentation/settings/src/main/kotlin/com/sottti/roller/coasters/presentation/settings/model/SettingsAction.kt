package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface SettingsAction {
    @Immutable
    data object LoadUi : SettingsAction

    @Immutable
    data class DynamicColorCheckedChange(val checked: Boolean) : SettingsAction

    @Immutable
    sealed interface AppThemeActions : SettingsAction {
        @Immutable
        data object LaunchAppThemePicker : AppThemeActions

        @Immutable
        data class AppThemePickerSelectionChange(val appTheme: AppThemeUi) : AppThemeActions

        @Immutable
        data class ConfirmAppThemePickerSelection(val appTheme: AppThemeUi) : AppThemeActions

        @Immutable
        data object DismissAppThemePicker : AppThemeActions
    }

    @Immutable
    sealed interface AppColorContrastActions : SettingsAction {
        @Immutable
        data object LaunchAppColorContrastPicker : AppColorContrastActions

        @Immutable
        data class AppColorContrastPickerSelectionChange(
            val appColorContrast: AppColorContrastUi,
        ) : AppColorContrastActions

        @Immutable
        data class ConfirmColorContrastPickerSelection(
            val appColorContrast: AppColorContrastUi,
        ) : AppColorContrastActions

        @Immutable
        data object DismissAppColorContrastPicker : AppColorContrastActions

        @Immutable
        data object DismissAppColorContrastNotAvailableMessage : AppColorContrastActions
    }

    @Immutable
    sealed interface AppLanguageActions : SettingsAction {
        @Immutable
        data object LaunchAppLanguagePicker : AppLanguageActions

        @Immutable
        data class AppLanguagePickerSelectionChange(
            val appLanguage: AppLanguageUi,
        ) : AppLanguageActions

        @Immutable
        data class ConfirmAppLanguagePickerSelection(
            val appLanguage: AppLanguageUi,
        ) : AppLanguageActions

        @Immutable
        data object DismissAppLanguagePicker : AppLanguageActions
    }

    @Immutable
    sealed interface AppMeasurementSystemActions : SettingsAction {
        @Immutable
        data object LaunchAppMeasurementSystemPicker : AppMeasurementSystemActions

        @Immutable
        data class AppMeasurementSystemPickerSelectionChange(
            val appMeasurementSystem: AppMeasurementSystemUi,
        ) : AppMeasurementSystemActions

        @Immutable
        data class ConfirmAppMeasurementSystemPickerSelection(
            val appMeasurementSystem: AppMeasurementSystemUi,
        ) : AppMeasurementSystemActions

        @Immutable
        data object DismissAppMeasurementSystemPicker : AppMeasurementSystemActions
    }
}