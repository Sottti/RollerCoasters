package com.sottti.roller.coasters.utils.device.mappers

import android.icu.util.LocaleData
import android.os.Build
import androidx.annotation.RequiresApi
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem

@RequiresApi(Build.VERSION_CODES.P)
internal fun LocaleData.MeasurementSystem.toSystemMeasurementSystem() =
    when (this) {
        LocaleData.MeasurementSystem.SI -> SystemMeasurementSystem.Metric
        LocaleData.MeasurementSystem.UK -> SystemMeasurementSystem.ImperialUk
        LocaleData.MeasurementSystem.US -> SystemMeasurementSystem.ImperialUs
        else -> SystemMeasurementSystem.Metric
    }