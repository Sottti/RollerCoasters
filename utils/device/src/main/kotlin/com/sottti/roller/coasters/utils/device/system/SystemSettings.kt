package com.sottti.roller.coasters.utils.device.system

import android.app.UiModeManager
import android.icu.util.LocaleData
import android.icu.util.ULocale
import android.os.Build
import androidx.annotation.RequiresApi
import com.sottti.roller.coasters.domain.model.SystemColorContrast
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem
import com.sottti.roller.coasters.utils.device.mappers.toSystemColorContrast
import com.sottti.roller.coasters.utils.device.mappers.toSystemMeasurementSystem
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import java.util.Locale
import javax.inject.Inject

public class SystemSettings @Inject constructor(
    private val localeProvider: LocaleProvider,
    private val sdkFeatures: SdkFeatures,
    private val uiModeManager: UiModeManager?,
) {
    private companion object {
        private val imperialUsRegions = setOf("US", "LR", "MM")
        private val imperialUkRegions = setOf("GB")
    }

    public val locale: Locale
        get() = localeProvider.getAppLocale() ?: localeProvider.getDefaultLocale()

    public val colorContrast: SystemColorContrast
        get() = when {
            sdkFeatures.colorContrastAvailable() -> {
                val contrast = uiModeManager?.contrast ?: 0f
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }

    public val measurementSystem: SystemMeasurementSystem
        get() = when {
            sdkFeatures.measurementSystemAvailable() -> getPreferredMeasurementSystem()
            else -> inferMeasurementSystem()
        }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun getPreferredMeasurementSystem(): SystemMeasurementSystem =
        LocaleData
            .getMeasurementSystem(ULocale.forLocale(locale))
            .toSystemMeasurementSystem()

    private fun inferMeasurementSystem(): SystemMeasurementSystem =
        when (locale.country.uppercase(Locale.US)) {
            in imperialUsRegions -> SystemMeasurementSystem.ImperialUs
            in imperialUkRegions -> SystemMeasurementSystem.ImperialUk
            else -> SystemMeasurementSystem.Metric
        }
}