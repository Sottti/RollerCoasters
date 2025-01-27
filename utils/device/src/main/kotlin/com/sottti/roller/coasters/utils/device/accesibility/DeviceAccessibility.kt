package com.sottti.roller.coasters.utils.device.accesibility

import android.app.UiModeManager
import com.sottti.roller.coasters.utils.device.sdk.SdkLevel
import com.sottti.roller.coasters.utils.device.sdk.isAtLeastSdk34
import javax.inject.Inject

public class DeviceAccessibility @Inject constructor(
    private val sdkLevel: SdkLevel,
    private val uiModeManager: UiModeManager?,
) {
    public val colorContrast: DeviceColorContrast
        get() = when {
            sdkLevel.isAtLeastSdk34() -> {
                val contrast = uiModeManager?.contrast ?: 0f
                when {
                    contrast < 0f -> DeviceColorContrast.LowContrast
                    contrast < 0.5f -> DeviceColorContrast.StandardContrast
                    contrast < 1.0f -> DeviceColorContrast.MediumContrast
                    contrast >= 1.0f -> DeviceColorContrast.HighContrast
                    else -> DeviceColorContrast.StandardContrast
                }
            }

            else -> DeviceColorContrast.StandardContrast
        }
}