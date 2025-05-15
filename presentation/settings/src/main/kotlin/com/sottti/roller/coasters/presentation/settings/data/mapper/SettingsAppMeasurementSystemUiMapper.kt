package com.sottti.roller.coasters.presentation.settings.data.mapper

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemUi
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemUi.ImperialUK
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemUi.ImperialUS
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemUi.Metric
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemUi.SystemApp

@Composable
internal fun AppMeasurementSystemUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toAppMeasurementSystemUi(
    measurementSystems: List<AppMeasurementSystemUi>,
): AppMeasurementSystemUi =
    measurementSystems.find { it.text == text } ?: measurementSystems.first()

internal fun AppMeasurementSystem.toPresentationModel(
    selected: Boolean,
): AppMeasurementSystemUi =
    when (this) {
        AppMeasurementSystem.Metric -> metric(selected)
        AppMeasurementSystem.ImperialUs -> imperialUS(selected)
        AppMeasurementSystem.ImperialUk -> imperialUK(selected)
        AppMeasurementSystem.System -> system(selected)
    }

private fun system(
    selected: Boolean,
): SystemApp =
    SystemApp(
        text = R.string.measurement_system_system,
        icon = if (selected) Icons.Straighten.filled else Icons.Straighten.outlined,
        selected = selected,
    )

private fun imperialUK(
    selected: Boolean,
): ImperialUK = ImperialUK(
    text = R.string.measurement_system_imperial_united_kingdom,
    icon = if (selected) Icons.Straighten.filled else Icons.Straighten.outlined,
    selected = selected,
)

private fun imperialUS(
    selected: Boolean,
): ImperialUS = ImperialUS(
    text = R.string.measurement_system_imperial_united_states,
    icon = if (selected) Icons.Straighten.filled else Icons.Straighten.outlined,
    selected = selected,
)

private fun metric(
    selected: Boolean,
): Metric = Metric(
    text = R.string.measurement_system_metric,
    icon = if (selected) Icons.Straighten.filled else Icons.Straighten.outlined,
    selected = selected,
)

internal fun AppMeasurementSystemUi.toDomain(): AppMeasurementSystem =
    when (this) {
        is ImperialUK -> AppMeasurementSystem.ImperialUk
        is ImperialUS -> AppMeasurementSystem.ImperialUs
        is Metric -> AppMeasurementSystem.Metric
        is SystemApp -> AppMeasurementSystem.System
    }