package com.sotti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sotti.roller.coasters.presentation.settings.data.mapper.toAppMeasurementSystemUi
import com.sotti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sotti.roller.coasters.presentation.settings.model.AppMeasurementSystemPickerState
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemPickerSelectionChange
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.ConfirmAppMeasurementSystemPickerSelection
import com.sotti.roller.coasters.presentation.settings.model.SettingsAction.DismissAppMeasurementSystemPicker

private const val MEASUREMENT_SYSTEM_EMPTY_ERROR = "Measurement system list must not be empty"

@Composable
internal fun AppMeasurementSystemPickerDialog(
    state: AppMeasurementSystemPickerState,
    onAction: (SettingsAction) -> Unit,
) {
    GenericPickerDialog(
        title = state.title,
        confirm = state.confirm,
        dismiss = state.dismiss,
        items = state.appMeasurementSystems,
        toOption = { appMeasurementSystem -> appMeasurementSystem.toRadioButtonOption() },
        fromOption = { option, list -> option.toAppMeasurementSystemUi(list) },
        findSelected = { appMeasurementSystems ->
            appMeasurementSystems.firstSelectedOrFirst(
                isSelected = { appMeasurementSystem -> appMeasurementSystem.selected },
                errorMessage = MEASUREMENT_SYSTEM_EMPTY_ERROR,
            )
        },
        onSelect = { onAction(AppMeasurementSystemPickerSelectionChange(it)) },
        onConfirm = { onAction(ConfirmAppMeasurementSystemPickerSelection(it)) },
        onDismiss = { onAction(DismissAppMeasurementSystemPicker) },
    )
}
