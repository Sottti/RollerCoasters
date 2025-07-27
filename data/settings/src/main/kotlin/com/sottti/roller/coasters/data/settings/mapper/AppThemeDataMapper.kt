package com.sottti.roller.coasters.data.settings.mapper

import android.app.UiModeManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme

@VisibleForTesting
internal const val THEME_KEY_DARK = "dark"

@VisibleForTesting
internal const val THEME_KEY_LIGHT = "light"

@VisibleForTesting
internal const val THEME_KEY_SYSTEM = "system"

internal val AppTheme.key: String
    get() = when (this) {
        AppTheme.DarkAppTheme -> THEME_KEY_DARK
        AppTheme.LightAppTheme -> THEME_KEY_LIGHT
        AppTheme.System -> THEME_KEY_SYSTEM
    }

internal fun String.toTheme(): AppTheme =
    when (this) {
        THEME_KEY_DARK -> AppTheme.DarkAppTheme
        THEME_KEY_LIGHT -> AppTheme.LightAppTheme
        else -> AppTheme.System
    }

@RequiresApi(Build.VERSION_CODES.R)
internal fun AppTheme.toUiModeManagerNightMode() = when (this) {
    AppTheme.DarkAppTheme -> UiModeManager.MODE_NIGHT_YES
    AppTheme.LightAppTheme -> UiModeManager.MODE_NIGHT_NO
    AppTheme.System -> UiModeManager.MODE_NIGHT_CUSTOM
}

internal fun AppTheme.toAppCompatDelegateNightMode() = when (this) {
    AppTheme.DarkAppTheme -> AppCompatDelegate.MODE_NIGHT_YES
    AppTheme.LightAppTheme -> AppCompatDelegate.MODE_NIGHT_NO
    AppTheme.System -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
}
