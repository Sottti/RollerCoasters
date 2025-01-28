package com.sottti.roller.coasters.data.settings

import android.app.UiModeManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.Theme

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
        Theme.DarkTheme -> "dark"
        Theme.LightTheme -> "light"
        Theme.SystemTheme -> "system"
    }

internal val ColorContrast.key: String
    get() = when (this) {
        ColorContrast.HighContrast -> "high"
        ColorContrast.MediumContrast -> "medium"
        ColorContrast.StandardContrast -> "standard"
        ColorContrast.SystemContrast -> "system"
    }