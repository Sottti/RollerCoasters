package com.sottti.roller.coasters.utils.device.mappers

import android.icu.util.LocaleData
import android.os.Build
import androidx.annotation.FloatRange
import androidx.annotation.RequiresApi
import com.sottti.roller.coasters.domain.model.SystemColorContrast
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem

internal fun toSystemColorContrast(
    @FloatRange(from = -1.0, to = 1.0) contrast: Float,
): SystemColorContrast =
    when {
        contrast < 0f -> SystemColorContrast.LowContrast
        contrast < 0.5f -> SystemColorContrast.StandardContrast
        contrast < 1.0f -> SystemColorContrast.MediumContrast
        contrast >= 1.0f -> SystemColorContrast.HighContrast
        else -> SystemColorContrast.StandardContrast
    }

@RequiresApi(Build.VERSION_CODES.P)
internal fun LocaleData.MeasurementSystem.toSystemMeasurementSystem() =
    when (this) {
        LocaleData.MeasurementSystem.SI -> SystemMeasurementSystem.Metric
        LocaleData.MeasurementSystem.UK -> SystemMeasurementSystem.ImperialUk
        LocaleData.MeasurementSystem.US -> SystemMeasurementSystem.ImperialUs
        else -> SystemMeasurementSystem.Metric
    }