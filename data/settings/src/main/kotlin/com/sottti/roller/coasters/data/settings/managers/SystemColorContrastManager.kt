package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import com.sottti.roller.coasters.data.settings.mapper.toSystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.utils.lifecycle.observeConfigurationChanges
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class SystemColorContrastManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val systemFeatures: SystemFeatures,
    private val uiModeManager: UiModeManager?,
) {
    fun getSystemColorContrast(): SystemColorContrast =
        when {
            systemFeatures.systemColorContrastAvailable() -> {
                // uiModeManager.contrast returns 0f for standard, 0.5f for medium and 1f for high
                val contrast = uiModeManager?.contrast ?: 0f
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }

    fun observeSystemColorContrast() =
        context.observeConfigurationChanges({ getSystemColorContrast() })
}
