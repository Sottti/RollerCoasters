package com.sottti.roller.coasters.utils.device.managers

import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.theme.Theme
import com.sottti.roller.coasters.utils.device.mappers.toAppCompatDelegateNightMode
import com.sottti.roller.coasters.utils.device.mappers.toUiModeManagerNightMode
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