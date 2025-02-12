package com.sottti.roller.coasters.utils.device.system

import android.app.UiModeManager
import androidx.annotation.FloatRange
import com.sottti.roller.coasters.domain.model.SystemColorContrast
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import javax.inject.Inject

public class SystemSettings @Inject constructor(
    private val sdkFeatures: SdkFeatures,
    private val uiModeManager: UiModeManager?,
) {
    public val colorContrast: SystemColorContrast
        get() = when {
            sdkFeatures.colorContrastAvailable() -> {
                val contrast = uiModeManager?.contrast ?: 0f
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }
}

private fun toSystemColorContrast(
    @FloatRange(from = -1.0, to = 1.0) contrast: Float,
): SystemColorContrast =
    when {
        contrast < 0f -> SystemColorContrast.LowContrast
        contrast < 0.5f -> SystemColorContrast.StandardContrast
        contrast < 1.0f -> SystemColorContrast.MediumContrast
        contrast >= 1.0f -> SystemColorContrast.HighContrast
        else -> SystemColorContrast.StandardContrast
    }