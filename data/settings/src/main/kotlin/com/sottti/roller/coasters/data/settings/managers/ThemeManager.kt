package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.data.settings.mapper.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.data.settings.mapper.toUiModeManagerNightMode
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.di.InAppThemeChangeSignal
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.SystemTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ThemeManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val features: Features,
    private val inAppThemeChangeSignal: InAppThemeChangeSignal,
    private val uiModeManager: UiModeManager?,
) {
    fun setTheme(
        appTheme: AppTheme,
        userTriggered: Boolean = true,
    ) {
        when {
            features.setPersistentNightModeAvailable() ->
                uiModeManager?.setApplicationNightMode(appTheme.toUiModeManagerNightMode())

            else -> {
                inAppThemeChangeSignal.activityRecreationNeeded = userTriggered
                AppCompatDelegate.setDefaultNightMode(appTheme.toAppCompatDelegateNightMode())
            }
        }
    }

    fun getSystemTheme(): SystemTheme =
        when (Configuration.UI_MODE_NIGHT_YES
        ) {
            (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ->
                SystemTheme.DarkAppTheme

            else -> SystemTheme.LightAppTheme
        }
}
