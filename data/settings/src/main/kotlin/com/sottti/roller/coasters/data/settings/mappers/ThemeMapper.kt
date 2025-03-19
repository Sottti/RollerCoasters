package com.sottti.roller.coasters.data.settings.mappers

import androidx.annotation.VisibleForTesting
import com.sottti.roller.coasters.domain.settings.model.theme.Theme

@VisibleForTesting
internal const val THEME_KEY_DARK = "dark"

@VisibleForTesting
internal const val THEME_KEY_LIGHT = "light"

@VisibleForTesting
internal const val THEME_KEY_SYSTEM = "system"

internal val Theme.key: String
    get() = when (this) {
        Theme.DarkTheme -> THEME_KEY_DARK
        Theme.LightTheme -> THEME_KEY_LIGHT
        Theme.SystemTheme -> THEME_KEY_SYSTEM
    }

internal fun String.toTheme(): Theme =
    when (this) {
        THEME_KEY_DARK -> Theme.DarkTheme
        THEME_KEY_LIGHT -> Theme.LightTheme
        else -> Theme.SystemTheme
    }