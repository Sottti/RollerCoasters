package com.sottti.roller.coasters.data.settings.mappers

import androidx.annotation.VisibleForTesting
import com.sottti.roller.coasters.domain.model.MeasurementSystem
import com.sottti.roller.coasters.domain.model.MeasurementSystem.ImperialUK
import com.sottti.roller.coasters.domain.model.MeasurementSystem.ImperialUS
import com.sottti.roller.coasters.domain.model.MeasurementSystem.Metric
import com.sottti.roller.coasters.domain.model.MeasurementSystem.System

@VisibleForTesting
internal const val MEASUREMENT_SYSTEM_METRIC = "metric"

@VisibleForTesting
internal const val MEASUREMENT_SYSTEM_IMPERIAL_US = "imperial_us"

@VisibleForTesting
internal const val MEASUREMENT_SYSTEM_IMPERIAL_UK = "imperial_uk"

@VisibleForTesting
internal const val MEASUREMENT_SYSTEM_SYSTEM = "system"

internal val MeasurementSystem.key: String
    get() = when (this) {
        ImperialUK -> MEASUREMENT_SYSTEM_IMPERIAL_UK
        ImperialUS -> MEASUREMENT_SYSTEM_IMPERIAL_US
        Metric -> MEASUREMENT_SYSTEM_METRIC
        System -> MEASUREMENT_SYSTEM_SYSTEM
    }