package com.sottti.roller.coasters.utils.device.managers

import android.icu.util.LocaleData
import android.icu.util.ULocale
import android.os.Build
import androidx.annotation.RequiresApi
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem
import com.sottti.roller.coasters.utils.device.mappers.toSystemMeasurementSystem
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import java.util.Locale
import javax.inject.Inject

public class MeasurementSystemManager @Inject constructor(
    private val sdkFeatures: SdkFeatures,
    private val languageManager: LanguageManager,
) {
    private companion object {
        private val imperialUsRegions = setOf("US", "LR", "MM")
        private val imperialUkRegions = setOf("GB")
    }

    public val measurementSystem: SystemMeasurementSystem
        get() = when {
            sdkFeatures.measurementSystemAvailable() -> getPreferredMeasurementSystem()
            else -> inferMeasurementSystem()
        }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun getPreferredMeasurementSystem(): SystemMeasurementSystem =
        LocaleData
            .getMeasurementSystem(ULocale.forLocale(languageManager.locale))
            .toSystemMeasurementSystem()

    private fun inferMeasurementSystem(): SystemMeasurementSystem =
        when (languageManager.locale.country.uppercase(Locale.US)) {
            in imperialUsRegions -> SystemMeasurementSystem.ImperialUs
            in imperialUkRegions -> SystemMeasurementSystem.ImperialUk
            else -> SystemMeasurementSystem.Metric
        }
}