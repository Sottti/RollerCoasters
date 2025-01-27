package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.Dialog
import com.sottti.roller.coasters.presentation.settings.data.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.data.toThemeDisplayable
import com.sottti.roller.coasters.presentation.settings.model.ThemeUi
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ThemePickerSelectionChange

@Composable
internal fun ThemePickerDialog(
    state: ThemePickerState,
    onAction: (SettingsAction) -> Unit,
) {

    Dialog.WithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.themes.map { theme -> theme.toRadioButtonOption() },
        onOptionSelected = { selectedOption ->
            onAction(ThemePickerSelectionChange(selectedOption.toThemeDisplayable(state.themes)))
        },
        onConfirm = { onAction(ConfirmThemePickerSelection(state.themes.findSelectedTheme())) },
        onDismiss = { onAction(DismissThemePicker) },
    )
}

private fun List<ThemeUi>.findSelectedTheme(): ThemeUi = find { it.selected } ?: first()

