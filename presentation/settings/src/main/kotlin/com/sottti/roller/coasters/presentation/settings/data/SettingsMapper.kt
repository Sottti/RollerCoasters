package com.sottti.roller.coasters.presentation.settings.data

import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ThemeWithText

internal fun ThemeWithText.toDomainModel() : Theme =
    when (this) {
        is ThemeWithText.DarkTheme -> Theme.DarkTheme
        is ThemeWithText.LightTheme -> Theme.LightTheme
        is ThemeWithText.SystemTheme -> Theme.SystemTheme
    }

internal fun Theme.toPresentationModel() : ThemeWithText =
    when (this) {
        is Theme.DarkTheme -> ThemeWithText.DarkTheme(R.string.theme_dark)
        is Theme.LightTheme -> ThemeWithText.LightTheme(R.string.theme_light)
        is Theme.SystemTheme -> ThemeWithText.SystemTheme(R.string.theme_system)
    }