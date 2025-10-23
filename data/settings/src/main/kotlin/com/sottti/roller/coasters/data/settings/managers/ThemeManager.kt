package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.data.settings.mapper.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.data.settings.mapper.toUiModeManagerNightMode
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.SystemTheme
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.utils.lifecycle.observeConfigurationChanges
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ThemeManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val systemFeatures: SystemFeatures,
    private val uiModeManager: UiModeManager?,
) {
    fun setTheme(appTheme: AppTheme) {
        when {
            systemFeatures.setPersistentNightModeAvailable() ->
                uiModeManager?.setApplicationNightMode(appTheme.toUiModeManagerNightMode())

            else -> AppCompatDelegate.setDefaultNightMode(appTheme.toAppCompatDelegateNightMode())
        }
    }

    fun getSystemTheme(): SystemTheme =
        when (Configuration.UI_MODE_NIGHT_YES) {
            (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ->
                SystemTheme.DarkSystemTheme

            else -> SystemTheme.LightSystemTheme
        }

    fun observeSystemTheme(): Flow<SystemTheme> =
        context.observeConfigurationChanges { getSystemTheme() }
}
