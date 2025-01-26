package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sottti.roller.coasters.presentation.design.system.dialogs.Dialog
import com.sottti.roller.coasters.presentation.design.system.dialogs.RadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppTheme
import com.sottti.roller.coasters.presentation.settings.model.AppThemePickerState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmThemeSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ThemeSelected

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
        onOptionSelected = { onAction(ThemeSelected(it.toAppTheme(state.themes)))  },
        onConfirm = { onAction(ConfirmThemeSelection(state.themes.find { it.selected } ?: state.themes.first())) },
        onDismiss = { onAction(DismissThemePicker) },
    )
}

@Composable
private fun AppTheme.toRadioButtonOption(): RadioButtonOption =
    RadioButtonOption(text, icon, selected)

private fun RadioButtonOption.toAppTheme(
    themes: List<AppTheme>,
): AppTheme = themes.find { it.text == text } ?: themes.first()