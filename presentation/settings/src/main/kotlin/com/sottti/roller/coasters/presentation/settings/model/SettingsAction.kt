package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class SettingsAction {
    data class ConfirmAppThemePickerSelection(val theme: AppTheme) : SettingsAction()
    data class DynamicColorCheckedChange(val checked: Boolean) : SettingsAction()
    data class AppThemePickerSelectionChange(val theme: AppTheme) : SettingsAction()
    data object DismissAppThemePicker : SettingsAction()
    data object LaunchAppThemePicker : SettingsAction()
}