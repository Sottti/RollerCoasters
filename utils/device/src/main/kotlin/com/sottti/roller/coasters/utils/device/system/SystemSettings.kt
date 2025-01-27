package com.sottti.roller.coasters.utils.device.system

import android.app.UiModeManager
import com.sottti.roller.coasters.utils.device.sdk.SdkLevel
import com.sottti.roller.coasters.utils.device.sdk.isAtLeastSdk34
import javax.inject.Inject

public class SystemSettings @Inject constructor(
    private val sdkLevel: SdkLevel,
    private val uiModeManager: UiModeManager?,
) {
    public val colorContrast: SystemColorContrast
        get() = when {
            sdkLevel.isAtLeastSdk34() -> {
                val contrast = uiModeManager?.contrast ?: 0f
                when {
                    contrast < 0f -> SystemColorContrast.LowContrast
                    contrast < 0.5f -> SystemColorContrast.StandardContrast
                    contrast < 1.0f -> SystemColorContrast.MediumContrast
                    contrast >= 1.0f -> SystemColorContrast.HighContrast
                    else -> SystemColorContrast.StandardContrast
                }
            }

            else -> SystemColorContrast.StandardContrast
        }
}