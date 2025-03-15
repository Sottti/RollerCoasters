package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toMeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.MeasurementSystemPickerState
import com.sottti.roller.coasters.presentation.settings.model.MeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmMeasurementSystemPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.MeasurementSystemPickerSelectionChange

@Composable
internal fun MeasurementSystemPickerDialog(
    state: MeasurementSystemPickerState,
    onAction: (SettingsAction) -> Unit,
) {
    DialogWithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.measurementSystems.map { measurementSystem ->
            measurementSystem.toRadioButtonOption()
        },
        onOptionSelected = { selectedOption ->
            val selectedMeasurementSystem =
                selectedOption.toMeasurementSystemUi(state.measurementSystems)
            onAction(MeasurementSystemPickerSelectionChange(selectedMeasurementSystem))
        },
        onConfirm = {
            onAction(
                ConfirmMeasurementSystemPickerSelection(
                    state.measurementSystems.findSelectedMeasurementSystem()
                ),
            )
        },
        onDismiss = { onAction(SettingsAction.DismissMeasurementSystemPicker) },
    )
}

private fun List<MeasurementSystemUi>.findSelectedMeasurementSystem(): MeasurementSystemUi =
    find { it.selected } ?: first()