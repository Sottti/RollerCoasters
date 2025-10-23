package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import com.sottti.roller.coasters.data.settings.mapper.toSystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.utils.lifecycle.observeConfigurationChanges
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SystemColorContrastManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val systemFeatures: SystemFeatures,
    private val uiModeManager: UiModeManager?,
) {
    fun getSystemColorContrast(): SystemColorContrast =
        when {
            systemFeatures.systemColorContrastAvailable() -> {
                val contrast = uiModeManager?.contrast
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }

    fun observeSystemColorContrast(): Flow<SystemColorContrast> =
        context.observeConfigurationChanges({ getSystemColorContrast() })
}
