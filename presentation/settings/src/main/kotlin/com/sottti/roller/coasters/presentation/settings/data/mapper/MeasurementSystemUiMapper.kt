package com.sottti.roller.coasters.presentation.settings.data.mapper

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.MeasurementSystemUi

@Composable
internal fun MeasurementSystemUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toMeasurementSystemUi(
    measurementSystems: List<MeasurementSystemUi>,
): MeasurementSystemUi = measurementSystems.find { it.text == text } ?: measurementSystems.first()

internal fun MeasurementSystem.toPresentationModel(isSelected: Boolean): MeasurementSystemUi =
    when (this) {
        MeasurementSystem.Metric -> MeasurementSystemUi.Metric(
            text = R.string.measurement_system_metric,
            icon = if (isSelected) Icons.Straighten.Filled else Icons.Straighten.Outlined,
            selected = isSelected,
        )

        MeasurementSystem.ImperialUs -> MeasurementSystemUi.ImperialUS(
            text = R.string.measurement_system_imperial_united_states,
            icon = if (isSelected) Icons.Straighten.Filled else Icons.Straighten.Outlined,
            selected = isSelected,
        )

        MeasurementSystem.ImperialUk -> MeasurementSystemUi.ImperialUK(
            text = R.string.measurement_system_imperial_united_kingdom,
            icon = if (isSelected) Icons.Straighten.Filled else Icons.Straighten.Outlined,
            selected = isSelected,
        )

        MeasurementSystem.System -> MeasurementSystemUi.System(
            text = R.string.measurement_system_system,
            icon = if (isSelected) Icons.Straighten.Filled else Icons.Straighten.Outlined,
            selected = isSelected,
        )
    }

internal fun MeasurementSystemUi.toDomain(): MeasurementSystem =
    when (this) {
        is MeasurementSystemUi.ImperialUK -> MeasurementSystem.ImperialUk
        is MeasurementSystemUi.ImperialUS -> MeasurementSystem.ImperialUs
        is MeasurementSystemUi.Metric -> MeasurementSystem.Metric
        is MeasurementSystemUi.System -> MeasurementSystem.System
    }