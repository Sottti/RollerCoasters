package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import com.sottti.roller.coasters.data.settings.mapper.toSystemColorContrast
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import javax.inject.Inject

internal class SystemColorContrastManager @Inject constructor(
    private val features: Features,
    private val uiModeManager: UiModeManager?,
) {
    val systemColorContrast: SystemColorContrast
        get() = when {
            features.systemColorContrastAvailable() -> {
                val contrast = uiModeManager?.contrast ?: 0f
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }
}
