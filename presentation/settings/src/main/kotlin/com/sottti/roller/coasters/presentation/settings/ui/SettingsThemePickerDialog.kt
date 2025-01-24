package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.Dialog
import com.sottti.roller.coasters.presentation.settings.model.AppThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemeSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker

@Composable
internal fun AppThemePickerDialog(
    state: AppThemePickerState,
    onAction: (SettingsAction) -> Unit,
) {
    Dialog.WithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.themes.map { theme -> theme.text },
        initialSelection = state.selectedTheme.text,
        onConfirm = { onAction(ConfirmThemeSelection(state.themes.first { theme -> theme.text == it })) },
        onDismiss = { onAction(DismissThemePicker) },
    )
}