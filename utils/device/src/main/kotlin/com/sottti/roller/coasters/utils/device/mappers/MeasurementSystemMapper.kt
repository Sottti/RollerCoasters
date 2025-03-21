package com.sottti.roller.coasters.utils.device.mappers

import android.icu.util.LocaleData
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric

internal fun LocaleData.MeasurementSystem.toSystemMeasurementSystem() =
    when (this) {
        LocaleData.MeasurementSystem.SI -> Metric
        LocaleData.MeasurementSystem.UK -> ImperialUk
        LocaleData.MeasurementSystem.US -> ImperialUs
        else -> Metric
    }