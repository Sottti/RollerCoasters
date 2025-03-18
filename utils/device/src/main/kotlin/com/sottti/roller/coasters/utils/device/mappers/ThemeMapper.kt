package com.sottti.roller.coasters.utils.device.mappers

import android.app.UiModeManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import com.sottti.roller.coasters.domain.model.Theme

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