package com.sottti.roller.coasters.presentation.settings.ui.dialogs

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.settings.data.mapper.toAppMeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.data.mapper.toRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemPickerState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.AppMeasurementSystemPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.ConfirmAppMeasurementSystemPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.DismissAppMeasurementSystemPicker

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
                errorMessage = MEASUREMENT_SYSTEM_EMPTY_ERROR
            )
        },
        onSelect = { onAction(AppMeasurementSystemPickerSelectionChange(it)) },
        onConfirm = { onAction(ConfirmAppMeasurementSystemPickerSelection(it)) },
        onDismiss = { onAction(DismissAppMeasurementSystemPicker) },
    )
}