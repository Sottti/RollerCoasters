package com.sottti.roller.coasters.data.settings.managers

import android.icu.util.LocaleData.getMeasurementSystem
import android.os.Build
import androidx.annotation.RequiresApi
import com.sottti.roller.coasters.data.settings.mapper.toSystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import javax.inject.Inject

internal class MeasurementSystemManager @Inject constructor(
    private val systemFeatures: SystemFeatures,
    private val localeManager: LocaleManager,
) {
    private companion object {
        private val imperialUsRegions = setOf("US", "LR", "MM")
        private val imperialUkRegions = setOf("GB")
    }

    val systemMeasurementSystem: SystemMeasurementSystem
        get() = when {
            systemFeatures.measurementSystemAvailable() -> getPreferredMeasurementSystem()
            else -> inferMeasurementSystem()
        }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun getPreferredMeasurementSystem(): SystemMeasurementSystem =
        getMeasurementSystem(localeManager.systemULocale)
            .toSystemMeasurementSystem()

    private fun inferMeasurementSystem(): SystemMeasurementSystem =
        when (localeManager.systemLocale.country) {
            in imperialUsRegions -> SystemMeasurementSystem.ImperialUs
            in imperialUkRegions -> SystemMeasurementSystem.ImperialUk
            else -> SystemMeasurementSystem.Metric
        }
}
