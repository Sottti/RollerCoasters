package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import com.sottti.roller.coasters.data.settings.mapper.toSystemColorContrast
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import javax.inject.Inject

internal class SystemColorContrastManager @Inject constructor(
    private val systemFeatures: SystemFeatures,
    private val uiModeManager: UiModeManager?,
) {
    val systemColorContrast: SystemColorContrast
        get() = when {
            systemFeatures.systemColorContrastAvailable() -> {
                // uiModeManager.contrast returns 0f for standard, 0.5f for medium and 1f for high
                val contrast = uiModeManager?.contrast ?: 0f
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }
}
