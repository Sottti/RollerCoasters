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

internal fun SettingsState.updateAppMeasurementSystem(
    newAppMeasurementSystem: AppMeasurementSystem,
): SettingsState = copy(
    appMeasurementSystem = this.appMeasurementSystem.copy(
        listItem = this.appMeasurementSystem.listItem.copy(
            selectedAppMeasurementSystem = SelectedAppMeasurementSystemState.Loaded(
                newAppMeasurementSystem.toPresentationModel(selected = true),
            )
        ),
    ),
)

internal fun SettingsState.showAppMeasurementSystemPicker(
    selectedAppMeasurementSystem: AppMeasurementSystem,
): SettingsState = copy(
    appMeasurementSystem = this.appMeasurementSystem.copy(
        picker = appMeasurementSystemPickerState(
            selectedAppMeasurementSystem.toPresentationModel(selected = true),
        ),
    ),
)

internal fun SettingsState.updateAppMeasurementSystemPicker(
    selectedAppMeasurementSystem: AppMeasurementSystemUi,
): SettingsState = copy(
    appMeasurementSystem = this.appMeasurementSystem.copy(
        picker = appMeasurementSystemPickerState(selectedAppMeasurementSystem)
    ),
)

internal fun SettingsState.hideAppMeasurementSystemPicker(): SettingsState {
    return this.copy(
        appMeasurementSystem = this.appMeasurementSystem.copy(picker = null)
    )
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