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
    val onOptionSelected = remember(onAction, state.appThemes) {
        { selectedOption: DialogRadioButtonOption ->
            onAction(AppThemePickerSelectionChange(selectedOption.toAppThemeUi(state.appThemes)))
        }
    }
    val onConfirm = remember(onAction, state.appThemes) {
        { onAction(ConfirmAppThemePickerSelection(state.appThemes.findSelectedTheme())) }
    }
    val onDismiss = remember(onAction) { { onAction(DismissAppThemePicker) } }

    DialogWithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = options,
        onOptionSelected = onOptionSelected,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
    )
}

private fun List<AppThemeUi>.findSelectedTheme(): AppThemeUi =
    find { it.selected }
        ?: firstOrNull()
        ?: error("app measurement system list should not be empty")