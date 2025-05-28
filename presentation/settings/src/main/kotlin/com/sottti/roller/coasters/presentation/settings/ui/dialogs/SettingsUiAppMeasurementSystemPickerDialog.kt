package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppMeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemPickerState
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.AppMeasurementSystemPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.ConfirmAppMeasurementSystemPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.DismissAppMeasurementSystemPicker

@Composable
internal fun AppMeasurementSystemPickerDialog(
    state: AppMeasurementSystemPickerState,
    onAction: (SettingsAction) -> Unit,
) {
    val options = remember(state.appMeasurementSystems) {
        state
            .appMeasurementSystems
            .map { appMeasurementSystem -> appMeasurementSystem.toRadioButtonOption() }
    }
    val onOptionSelected = remember(onAction, state.appMeasurementSystems) {
        { selectedOption: DialogRadioButtonOption ->
            val selectedMeasurementSystem =
                selectedOption.toAppMeasurementSystemUi(state.appMeasurementSystems)
            onAction(AppMeasurementSystemPickerSelectionChange(selectedMeasurementSystem))
        }
    }
    val onConfirm = remember(onAction, state.appMeasurementSystems) {
        {
            onAction(
                ConfirmAppMeasurementSystemPickerSelection(
                    state.appMeasurementSystems.findSelectedMeasurementSystem()
                )
            )
        }
    }
    val onDismiss = remember(onAction) { { onAction(DismissAppMeasurementSystemPicker) } }

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

private fun List<AppMeasurementSystemUi>.findSelectedMeasurementSystem(): AppMeasurementSystemUi =
    find { it.selected }
        ?: firstOrNull()
        ?: error("app measurement system list should not be empty")