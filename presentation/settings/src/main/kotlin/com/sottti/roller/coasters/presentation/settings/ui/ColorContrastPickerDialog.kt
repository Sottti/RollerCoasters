package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.Dialog
import com.sottti.roller.coasters.presentation.settings.data.toColorContrastUi
import com.sottti.roller.coasters.presentation.settings.data.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastPickerState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmColorContrastPickerSelection

@Composable
internal fun ColorContrastPickerDialog(
    state: ColorContrastPickerState,
    onAction: (SettingsAction) -> Unit,
) {
    Dialog.WithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.contrasts.map { contrast -> contrast.toRadioButtonOption() },
        onOptionSelected = { selectedOption ->
            onAction(
                SettingsAction.ColorContrastPickerSelectionChange(
                    selectedOption.toColorContrastUi(state.contrasts)
                ),
            )
        },
        onConfirm = {
            onAction(
                ConfirmColorContrastPickerSelection(state.contrasts.findSelectedColorContrast())
            )
        },
        onDismiss = { onAction(SettingsAction.DismissColorContrastPicker) },
    )
}

private fun List<ColorContrastUi>.findSelectedColorContrast(): ColorContrastUi =
    find { it.selected } ?: first()