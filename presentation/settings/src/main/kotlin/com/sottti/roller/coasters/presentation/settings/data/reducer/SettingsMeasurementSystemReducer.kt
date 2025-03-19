package com.sottti.roller.coasters.presentation.settings.data.reducer

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem.Metric
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.MeasurementSystemPickerState
import com.sottti.roller.coasters.presentation.settings.model.MeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.model.SelectedMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun MutableStateFlow<SettingsState>.updateMeasurementSystem(
    measurementSystem: MeasurementSystem,
) {
    update { currentState ->
        currentState.copy(
            measurementSystem = currentState.measurementSystem.copy(
                listItem = currentState.measurementSystem.listItem.copy(
                    selectedMeasurementSystem = SelectedMeasurementSystemState.Loaded(
                        measurementSystem.toPresentationModel(isSelected = true),
                    )
                ),
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.showMeasurementSystemPicker(
    measurementSystem: MeasurementSystem,
) {
    update { currentState ->
        currentState.copy(
            measurementSystem = currentState.measurementSystem.copy(
                picker = measurementSystemPickerState(
                    measurementSystem.toPresentationModel(isSelected = true),
                ),
            )
        )
    }
}

internal fun MutableStateFlow<SettingsState>.updateMeasurementSystemPicker(
    selectedMeasurementSystem: MeasurementSystemUi,
) {
    update { currentState ->
        currentState.copy(
            measurementSystem = currentState.measurementSystem.copy(
                picker = measurementSystemPickerState(
                    selectedMeasurementSystem
                )
            ),
        )
    }
}

internal fun MutableStateFlow<SettingsState>.hideMeasurementSystemPicker() {
    update { currentState ->
        currentState.copy(
            measurementSystem = currentState.measurementSystem.copy(picker = null)
        )
    }
}

private fun measurementSystemPickerState(
    selectedMeasurementSystem: MeasurementSystemUi,
) = MeasurementSystemPickerState(
    title = R.string.measurement_system_picker_title,
    confirm = R.string.picker_confirm,
    dismiss = R.string.picker_dismiss,
    measurementSystems = measurementSystemsList(selectedMeasurementSystem),
)

private fun measurementSystemsList(
    selectedMeasurementSystem: MeasurementSystemUi,
) = listOf(
    MeasurementSystem.System.toPresentationModel(
        isSelected = selectedMeasurementSystem is MeasurementSystemUi.System,
    ),
    Metric.toPresentationModel(
        isSelected = selectedMeasurementSystem is MeasurementSystemUi.Metric,
    ),
    ImperialUs.toPresentationModel(
        isSelected = selectedMeasurementSystem is MeasurementSystemUi.ImperialUS,
    ),
    ImperialUk.toPresentationModel(
        isSelected = selectedMeasurementSystem is MeasurementSystemUi.ImperialUK,
    ),
)