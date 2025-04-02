package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogWithRadioButtons
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppMeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemPickerState
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppMeasurementSystemPickerSelection

@Composable
internal fun AppMeasurementSystemPickerDialog(
    state: AppMeasurementSystemPickerState,
    onAction: (SettingsAction) -> Unit,
) {
    DialogWithRadioButtons(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        options = state.appMeasurementSystems.map { measurementSystem ->
            measurementSystem.toRadioButtonOption()
        },
        onOptionSelected = { selectedOption ->
            val selectedMeasurementSystem =
                selectedOption.toAppMeasurementSystemUi(state.appMeasurementSystems)
            onAction(AppMeasurementSystemPickerSelectionChange(selectedMeasurementSystem))
        },
        onConfirm = {
            onAction(
                ConfirmAppMeasurementSystemPickerSelection(
                    state.appMeasurementSystems.findSelectedMeasurementSystem()
                ),
            )
        },
        onDismiss = { onAction(SettingsAction.DismissAppMeasurementSystemPicker) },
    )
}

private fun List<AppMeasurementSystemUi>.findSelectedMeasurementSystem(): AppMeasurementSystemUi =
    find { it.selected } ?: first()