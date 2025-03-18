package com.sottti.roller.coasters.utils.device.managers

import android.app.UiModeManager
import com.sottti.roller.coasters.domain.model.SystemColorContrast
import com.sottti.roller.coasters.utils.device.mappers.toSystemColorContrast
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import javax.inject.Inject

public class ColorContrastManager @Inject constructor(
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