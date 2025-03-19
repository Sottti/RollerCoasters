package com.sottti.roller.coasters.utils.device.mappers

import android.icu.util.LocaleData
import android.os.Build
import androidx.annotation.RequiresApi
import com.sottti.roller.coasters.domain.settings.model.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.SystemMeasurementSystem.Metric

@RequiresApi(Build.VERSION_CODES.P)
internal fun LocaleData.MeasurementSystem.toSystemMeasurementSystem() =
    when (this) {
        LocaleData.MeasurementSystem.SI -> Metric
        LocaleData.MeasurementSystem.UK -> ImperialUk
        LocaleData.MeasurementSystem.US -> ImperialUs
        else -> Metric
    }