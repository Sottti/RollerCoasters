package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppThemeUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.AppThemePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.ConfirmAppThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.DismissAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.ThemePickerState

@Composable
internal fun AppThemePickerDialog(
    state: ThemePickerState,
    onAction: (SettingsAction) -> Unit,
) {
    val options = remember(state.appThemes) {
        state.appThemes.map { appTheme -> appTheme.toRadioButtonOption() }
    }
    val selectedTheme = remember(state.appThemes) { state.appThemes.findSelectedTheme() }
    val onOptionSelected = remember(onAction, state.appThemes) {
        { selectedOption: DialogRadioButtonOption ->
            val newSelection = selectedOption.toAppThemeUi(state.appThemes)
            onAction(AppThemePickerSelectionChange(newSelection))
        }
    }
    val onConfirm = remember(onAction, selectedTheme) {
        { onAction(ConfirmAppThemePickerSelection(selectedTheme)) }
    }
    val onDismiss = remember(onAction) { { onAction(DismissAppThemePicker) } }

    DialogWithRadioButtons(
        confirm = state.confirm,
        dismiss = state.dismiss,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        onOptionSelected = onOptionSelected,
        options = options,
        title = state.title,
    )
}

private fun List<AppThemeUi>.findSelectedTheme(): AppThemeUi =
    find { it.selected }
        ?: firstOrNull()
        ?: error("AppTheme list must not be empty")