package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.data.mapper.toThemeUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ThemePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.ThemeUi

@Composable
internal fun ThemePickerDialog(
    state: ThemePickerState,
    onAction: (SettingsAction) -> Unit,
) {

    DialogWithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.themes.map { theme -> theme.toRadioButtonOption() },
        onOptionSelected = { selectedOption ->
            onAction(ThemePickerSelectionChange(selectedOption.toThemeUi(state.themes)))
        },
        onConfirm = { onAction(ConfirmThemePickerSelection(state.themes.findSelectedTheme())) },
        onDismiss = { onAction(DismissThemePicker) },
    )
}

private fun List<ThemeUi>.findSelectedTheme(): ThemeUi = find { it.selected } ?: first()