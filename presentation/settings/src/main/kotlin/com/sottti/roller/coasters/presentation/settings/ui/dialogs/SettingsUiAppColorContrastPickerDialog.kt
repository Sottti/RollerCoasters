package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
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
    DialogWithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.appColorContrasts.map { contrast -> contrast.toRadioButtonOption() },
        onOptionSelected = { selectedOption ->
            onAction(
                AppColorContrastPickerSelectionChange(
                    selectedOption.toAppColorContrastUi(state.appColorContrasts)
                ),
            )
        },
        onConfirm = {
            onAction(
                ConfirmColorContrastPickerSelection(
                    state.appColorContrasts.findSelectedAppColorContrast(),
                )
            )
        },
        onDismiss = { onAction(DismissAppColorContrastPicker) },
    )
}

private fun List<AppColorContrastUi>.findSelectedAppColorContrast(): AppColorContrastUi =
    find { it.selected } ?: first()