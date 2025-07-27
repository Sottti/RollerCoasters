package com.sottti.roller.coasters.domain.settings.mapper

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem

internal fun AppMeasurementSystem.toResolvedMeasurementSystem(
    getSystemMeasurementSystem: () -> SystemMeasurementSystem,
): ResolvedMeasurementSystem = when (this) {
    AppMeasurementSystem.ImperialUk -> ResolvedMeasurementSystem.ImperialUk
    AppMeasurementSystem.ImperialUs -> ResolvedMeasurementSystem.ImperialUs
    AppMeasurementSystem.Metric -> ResolvedMeasurementSystem.Metric
    AppMeasurementSystem.System -> getSystemMeasurementSystem().toResolvedMeasurementSystem()
}

private fun SystemMeasurementSystem.toResolvedMeasurementSystem(): ResolvedMeasurementSystem =
    when (this) {
        SystemMeasurementSystem.ImperialUk -> ResolvedMeasurementSystem.ImperialUk
        SystemMeasurementSystem.ImperialUs -> ResolvedMeasurementSystem.ImperialUs
        SystemMeasurementSystem.Metric -> ResolvedMeasurementSystem.Metric
    }