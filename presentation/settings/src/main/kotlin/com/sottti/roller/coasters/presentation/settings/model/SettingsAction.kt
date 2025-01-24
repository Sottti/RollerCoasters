package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class SettingsAction {
    data class DynamicColorCheckedChange(val checked: Boolean) : SettingsAction()
    data class ConfirmThemeSelection(val theme: ThemeWithText) : SettingsAction()
    data object DismissThemePicker : SettingsAction()
    data object LaunchAppThemePicker : SettingsAction()
}