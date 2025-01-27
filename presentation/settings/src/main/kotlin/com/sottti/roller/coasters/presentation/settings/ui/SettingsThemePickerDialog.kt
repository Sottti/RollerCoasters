package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.Dialog
import com.sottti.roller.coasters.presentation.settings.data.toAppTheme
import com.sottti.roller.coasters.presentation.settings.data.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppTheme
import com.sottti.roller.coasters.presentation.settings.model.AppThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemePickerSelectionChange

@Composable
internal fun AppThemePickerDialog(
    state: AppThemePickerState,
    onAction: (SettingsAction) -> Unit,
) {

    Dialog.WithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.themes.map { theme -> theme.toRadioButtonOption() },
        onOptionSelected = { selectedOption ->
            onAction(AppThemePickerSelectionChange(selectedOption.toAppTheme(state.themes)))
        },
        onConfirm = { onAction(ConfirmAppThemePickerSelection(state.themes.findSelectedTheme())) },
        onDismiss = { onAction(DismissAppThemePicker) },
    )
}

private fun List<AppTheme>.findSelectedTheme(): AppTheme = find { it.selected } ?: first()

