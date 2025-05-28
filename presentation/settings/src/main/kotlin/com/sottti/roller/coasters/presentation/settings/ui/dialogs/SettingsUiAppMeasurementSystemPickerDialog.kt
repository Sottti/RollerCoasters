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
    val selectedSystem = remember(state.appMeasurementSystems) {
        state.appMeasurementSystems.findSelectedMeasurementSystem()
    }
    val onOptionSelected = remember(onAction, state.appMeasurementSystems) {
        { selectedOption: DialogRadioButtonOption ->
            val newSelection = selectedOption.toAppMeasurementSystemUi(state.appMeasurementSystems)
            onAction(AppMeasurementSystemPickerSelectionChange(newSelection))
        }
    }
    val onConfirm = remember(onAction, selectedSystem) {
        { onAction(ConfirmAppMeasurementSystemPickerSelection(selectedSystem)) }
    }
    val onDismiss = remember(onAction) { { onAction(DismissAppMeasurementSystemPicker) } }

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

private fun List<AppMeasurementSystemUi>.findSelectedMeasurementSystem(): AppMeasurementSystemUi =
    find { it.selected }
        ?: firstOrNull()
        ?: error("AppMeasurementSystem list must not be empty")