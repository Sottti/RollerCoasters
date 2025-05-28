package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppColorContrastUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.AppColorContrastPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.ConfirmColorContrastPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.DismissAppColorContrastPicker

@Composable
internal fun AppColorContrastPickerDialog(
    state: AppColorContrastPickerState,
    onAction: (SettingsAction) -> Unit,
) {
    val options = remember(state.appColorContrasts) {
        state.appColorContrasts.map { it.toRadioButtonOption() }
    }
    val selectedContrast = remember(state.appColorContrasts) {
        state.appColorContrasts.findSelectedAppColorContrast()
    }
    val onOptionSelected = remember(onAction, state.appColorContrasts) {
        { selectedOption: DialogRadioButtonOption ->
            val newSelection = selectedOption.toAppColorContrastUi(state.appColorContrasts)
            onAction(AppColorContrastPickerSelectionChange(newSelection))
        }
    }
    val onConfirm = remember(onAction, selectedContrast) {
        { onAction(ConfirmColorContrastPickerSelection(selectedContrast)) }
    }
    val onDismiss = remember(onAction) { { onAction(DismissAppColorContrastPicker) } }

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

private fun List<AppColorContrastUi>.findSelectedAppColorContrast(): AppColorContrastUi =
    find { it.selected }
        ?: firstOrNull()
        ?: error("app color contrasts must not be empty")