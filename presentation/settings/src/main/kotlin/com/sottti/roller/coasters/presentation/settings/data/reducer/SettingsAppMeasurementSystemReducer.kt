package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemPickerState
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.model.ImperialUk
import com.sottti.roller.coasters.presentation.settings.model.ImperialUs
import com.sottti.roller.coasters.presentation.settings.model.Metric
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.SystemApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun SettingsState.updateAppMeasurementSystem(
    newAppMeasurementSystem: AppMeasurementSystem,
): SettingsState = copy(
    appMeasurementSystem = appMeasurementSystem.copy(
        listItem = appMeasurementSystem.listItem.copy(
            selectedAppMeasurementSystem = SelectedAppMeasurementSystemState.Loaded(
                newAppMeasurementSystem.toPresentationModel(selected = true),
            )
        ),
    ),
)

internal fun MutableStateFlow<SettingsState>.updateAppMeasurementSystem(
    newAppMeasurementSystem: AppMeasurementSystem,
): MutableStateFlow<SettingsState> = apply {
    update { currentState ->
        currentState.updateAppMeasurementSystem(newAppMeasurementSystem)
    }
}

internal fun SettingsState.showAppMeasurementSystemPicker(
    selectedAppMeasurementSystem: AppMeasurementSystem,
): SettingsState = copy(
    appMeasurementSystem = appMeasurementSystem.copy(
        picker = appMeasurementSystemPickerState(
            selectedAppMeasurementSystem.toPresentationModel(selected = true),
        ),
    ),
)

internal fun MutableStateFlow<SettingsState>.showAppMeasurementSystemPicker(
    selectedAppMeasurementSystem: AppMeasurementSystem,
): MutableStateFlow<SettingsState> = apply {
    update { currentState ->
        currentState.showAppMeasurementSystemPicker(selectedAppMeasurementSystem)
    }
}

internal fun SettingsState.updateAppMeasurementSystemPicker(
    selectedAppMeasurementSystem: AppMeasurementSystemUi,
): SettingsState = copy(
    appMeasurementSystem = appMeasurementSystem.copy(
        picker = appMeasurementSystemPickerState(selectedAppMeasurementSystem)
    ),
)

internal fun MutableStateFlow<SettingsState>.updateAppMeasurementSystemPicker(
    selectedAppMeasurementSystem: AppMeasurementSystemUi,
): MutableStateFlow<SettingsState> = apply {
    update { currentState ->
        currentState.updateAppMeasurementSystemPicker(selectedAppMeasurementSystem)
    }
}

internal fun SettingsState.hideAppMeasurementSystemPicker(): SettingsState {
    return copy(
        appMeasurementSystem = appMeasurementSystem.copy(picker = null),
    )
}

internal fun MutableStateFlow<SettingsState>.hideAppMeasurementSystemPicker(): MutableStateFlow<SettingsState> =
    apply {
        update { currentState -> currentState.hideAppMeasurementSystemPicker() }
    }

private fun appMeasurementSystemPickerState(
    selectedAppMeasurementSystem: AppMeasurementSystemUi,
) = AppMeasurementSystemPickerState(
    title = R.string.measurement_system_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    appMeasurementSystems = appMeasurementSystemsList(selectedAppMeasurementSystem),
)

private fun appMeasurementSystemsList(
    selectedMeasurementSystem: AppMeasurementSystemUi,
) = listOf(
    System.toPresentationModel(selected = selectedMeasurementSystem is SystemApp),
    Metric.toPresentationModel(selected = selectedMeasurementSystem is Metric),
    ImperialUs.toPresentationModel(selected = selectedMeasurementSystem is ImperialUs),
    ImperialUk.toPresentationModel(selected = selectedMeasurementSystem is ImperialUk),
)