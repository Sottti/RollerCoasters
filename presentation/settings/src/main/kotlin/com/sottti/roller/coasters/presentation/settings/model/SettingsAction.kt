package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class SettingsAction {
    data class DynamicColorCheckedChange(val checked: Boolean) : SettingsAction()

    data object LaunchThemePicker : SettingsAction()
    data class ThemePickerSelectionChange(val theme: ThemeUi) : SettingsAction()
    data class ConfirmThemePickerSelection(val theme: ThemeUi) : SettingsAction()
    data object DismissThemePicker : SettingsAction()

    data object LaunchColorContrastPicker : SettingsAction()
    data class ColorContrastPickerSelectionChange(val contrast: ColorContrastUi) : SettingsAction()
    data class ConfirmColorContrastPickerSelection(val contrast: ColorContrastUi) : SettingsAction()
    data object DismissColorContrastPicker : SettingsAction()
    data object DismissColorContrastNotAvailableMessage : SettingsAction()

    data object LaunchLanguagePicker : SettingsAction()
    data class LanguagePickerSelectionChange(val language: LanguageUi) : SettingsAction()
    data class ConfirmLanguagePickerSelection(val language: LanguageUi) : SettingsAction()
    data object DismissLanguagePicker : SettingsAction()

    data object LaunchMeasurementSystemPicker : SettingsAction()
    data class MeasurementSystemPickerSelectionChange(
        val measurementSystem: MeasurementSystemUi,
    ) : SettingsAction()

    data class ConfirmMeasurementSystemPickerSelection(
        val measurementSystem: MeasurementSystemUi,
    ) : SettingsAction()

    data object DismissMeasurementSystemPicker : SettingsAction()
}