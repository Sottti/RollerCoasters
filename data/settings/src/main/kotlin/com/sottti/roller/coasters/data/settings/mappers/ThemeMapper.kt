package com.sottti.roller.coasters.data.settings.mappers

import android.app.UiModeManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.domain.model.Theme

@VisibleForTesting
internal const val THEME_KEY_DARK = "dark"
@VisibleForTesting
internal const val THEME_KEY_LIGHT = "light"
@VisibleForTesting
internal const val THEME_KEY_SYSTEM = "system"

@RequiresApi(Build.VERSION_CODES.R)
internal fun Theme.toUiModeManagerNightMode() = when (this) {
    Theme.DarkTheme -> UiModeManager.MODE_NIGHT_YES
    Theme.LightTheme -> UiModeManager.MODE_NIGHT_NO
    Theme.SystemTheme -> UiModeManager.MODE_NIGHT_CUSTOM
}

internal fun Theme.toAppCompatDelegateNightMode() = when (this) {
    Theme.DarkTheme -> AppCompatDelegate.MODE_NIGHT_YES
    Theme.LightTheme -> AppCompatDelegate.MODE_NIGHT_NO
    Theme.SystemTheme -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
}

internal val Theme.key: String
    get() = when (this) {
        Theme.DarkTheme -> THEME_KEY_DARK
        Theme.LightTheme -> THEME_KEY_LIGHT
        Theme.SystemTheme -> THEME_KEY_SYSTEM
    }