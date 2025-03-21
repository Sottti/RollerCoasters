package com.sottti.roller.coasters.data.settings.managers

import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.data.settings.mappers.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.data.settings.mappers.toUiModeManagerNightMode
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.theme.Theme
import javax.inject.Inject

public class ThemeManager @Inject constructor(
    private val features: Features,
    private val uiModeManager: UiModeManager?,
) {
    public fun setTheme(theme: Theme) {
        when {
            features.setPersistentNightModeAvailable() ->
                uiModeManager?.setApplicationNightMode(theme.toUiModeManagerNightMode())

            else -> AppCompatDelegate.setDefaultNightMode(theme.toAppCompatDelegateNightMode())
        }
    }
}