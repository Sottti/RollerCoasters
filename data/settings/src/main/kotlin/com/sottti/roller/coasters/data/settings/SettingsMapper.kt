package com.sottti.roller.coasters.data.settings

import android.app.UiModeManager
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.domain.settings.model.Theme

internal fun Theme.toUiModeManagerNightMode() = when (this) {
    Theme.DarkTheme -> UiModeManager.MODE_NIGHT_YES
    Theme.LightTheme -> UiModeManager.MODE_NIGHT_NO
    Theme.SystemTheme -> UiModeManager.MODE_NIGHT_AUTO
}

internal fun Theme.toAppCompatDelegateNightMode() = when (this) {
    Theme.DarkTheme -> AppCompatDelegate.MODE_NIGHT_YES
    Theme.LightTheme -> AppCompatDelegate.MODE_NIGHT_NO
    Theme.SystemTheme -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
}