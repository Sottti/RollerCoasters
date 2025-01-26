package com.sottti.roller.coasters.utils.device

import android.app.UiModeManager
import javax.inject.Inject

public class DeviceAccessibility @Inject constructor(
    private val sdkLevel: SdkLevel,
    private val uiModeManager: UiModeManager?,
) {
    public val colorContrast: ColorContrast
        get() = when {
            sdkLevel.isAtLeastSdk34() -> {
                val contrast = uiModeManager?.contrast ?: 0f
                when {
                    contrast < 0f -> ColorContrast.LowContrast
                    contrast < 0.5f -> ColorContrast.StandardContrast
                    contrast < 1.0f -> ColorContrast.MediumContrast
                    contrast >= 1.0f -> ColorContrast.HighContrast
                    else -> ColorContrast.StandardContrast
                }
            }

            else -> ColorContrast.StandardContrast
        }
}