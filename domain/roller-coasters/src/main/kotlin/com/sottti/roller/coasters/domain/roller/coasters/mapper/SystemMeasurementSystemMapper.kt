package com.sottti.roller.coasters.domain.roller.coasters.mapper

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem

public fun AppMeasurementSystem.toSystemMeasurementSystem(
    getSystemMeasurementSystem: () -> SystemMeasurementSystem,
): SystemMeasurementSystem = when (this) {
    AppMeasurementSystem.ImperialUk -> SystemMeasurementSystem.ImperialUk
    AppMeasurementSystem.ImperialUs -> SystemMeasurementSystem.ImperialUs
    AppMeasurementSystem.Metric -> SystemMeasurementSystem.Metric
    AppMeasurementSystem.System -> getSystemMeasurementSystem()
}