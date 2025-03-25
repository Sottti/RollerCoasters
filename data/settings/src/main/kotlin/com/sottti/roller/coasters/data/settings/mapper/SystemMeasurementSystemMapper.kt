package com.sottti.roller.coasters.data.settings.mapper

import android.icu.util.LocaleData
import android.os.Build
import androidx.annotation.RequiresApi
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric

@RequiresApi(Build.VERSION_CODES.P)
internal fun LocaleData.MeasurementSystem.toSystemMeasurementSystem() =
    when (this) {
        LocaleData.MeasurementSystem.SI -> Metric
        LocaleData.MeasurementSystem.UK -> ImperialUk
        LocaleData.MeasurementSystem.US -> ImperialUs
        else -> Metric
    }