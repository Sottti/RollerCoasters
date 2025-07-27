package com.sottti.roller.coasters.data.settings.mapper

import androidx.annotation.VisibleForTesting
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System

@VisibleForTesting
internal const val MEASUREMENT_SYSTEM_METRIC = "metric"

@VisibleForTesting
internal const val MEASUREMENT_SYSTEM_IMPERIAL_US = "imperial_us"

@VisibleForTesting
internal const val MEASUREMENT_SYSTEM_IMPERIAL_UK = "imperial_uk"

@VisibleForTesting
internal const val MEASUREMENT_SYSTEM_SYSTEM = "system"

internal val AppMeasurementSystem.key: String
    get() = when (this) {
        ImperialUk -> MEASUREMENT_SYSTEM_IMPERIAL_UK
        ImperialUs -> MEASUREMENT_SYSTEM_IMPERIAL_US
        Metric -> MEASUREMENT_SYSTEM_METRIC
        System -> MEASUREMENT_SYSTEM_SYSTEM
    }

internal fun String.toAppMeasurementSystem(): AppMeasurementSystem =
    when (this) {
        MEASUREMENT_SYSTEM_IMPERIAL_UK -> ImperialUk
        MEASUREMENT_SYSTEM_IMPERIAL_US -> ImperialUs
        MEASUREMENT_SYSTEM_METRIC -> Metric
        else -> System
    }
