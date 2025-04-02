package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppThemeUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState

@Composable
internal fun AppThemePickerDialog(
    state: ThemePickerState,
    onAction: (SettingsAction) -> Unit,
) {
    DialogWithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.appThemes.map { theme -> theme.toRadioButtonOption() },
        onOptionSelected = { selectedOption ->
            onAction(AppThemePickerSelectionChange(selectedOption.toAppThemeUi(state.appThemes)))
        },
        onConfirm = { onAction(ConfirmAppThemePickerSelection(state.appThemes.findSelectedTheme())) },
        onDismiss = { onAction(DismissAppThemePicker) },
    )
}

private fun List<AppThemeUi>.findSelectedTheme(): AppThemeUi = find { it.selected } ?: first()