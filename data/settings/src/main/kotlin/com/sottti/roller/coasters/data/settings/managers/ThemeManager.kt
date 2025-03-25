package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.data.settings.mapper.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.data.settings.mapper.toUiModeManagerNightMode
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import javax.inject.Inject

public class ThemeManager @Inject constructor(
    private val features: Features,
    private val uiModeManager: UiModeManager?,
) {
    public fun setTheme(appTheme: AppTheme) {
        when {
            features.setPersistentNightModeAvailable() ->
                uiModeManager?.setApplicationNightMode(appTheme.toUiModeManagerNightMode())

            else -> AppCompatDelegate.setDefaultNightMode(appTheme.toAppCompatDelegateNightMode())
        }
    }
}