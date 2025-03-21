package com.sottti.roller.coasters.utils.device.managers

import android.app.UiModeManager
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.utils.device.mappers.toSystemColorContrast
import javax.inject.Inject

public class SystemColorContrastManager @Inject constructor(
    private val features: Features,
    private val uiModeManager: UiModeManager?,
) {
    public val systemColorContrast: SystemColorContrast
        get() = when {
            features.systemColorContrastAvailable() -> {
                val contrast = uiModeManager?.contrast ?: 0f
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }
}