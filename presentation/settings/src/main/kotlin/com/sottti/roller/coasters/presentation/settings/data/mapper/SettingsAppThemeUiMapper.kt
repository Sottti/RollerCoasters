package com.sottti.roller.coasters.presentation.settings.data.mapper

import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons.DarkMode
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons.LightMode
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi
import com.sottti.roller.coasters.presentation.settings.model.DarkTheme
import com.sottti.roller.coasters.presentation.settings.model.LightTheme
import com.sottti.roller.coasters.presentation.settings.model.SystemTheme

internal fun AppThemeUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toAppThemeUi(
    themes: List<AppThemeUi>,
): AppThemeUi = themes.find { it.text == text } ?: themes.first()

internal fun AppTheme.toPresentationModel(selected: Boolean): AppThemeUi =
    when (this) {
        AppTheme.LightAppTheme -> lightTheme(selected)
        AppTheme.DarkAppTheme -> darkTheme(selected)
        AppTheme.System -> systemTheme(selected)
    }

internal fun AppThemeUi.toDomain(): AppTheme =
    when (this) {
        is DarkTheme -> AppTheme.DarkAppTheme
        is LightTheme -> AppTheme.LightAppTheme
        is SystemTheme -> AppTheme.System
    }

private fun systemTheme(
    selected: Boolean,
): SystemTheme =
    SystemTheme(
        text = R.string.theme_system,
        icon = if (selected) Icons.BrightnessAuto.filled else Icons.BrightnessAuto.outlined,
        selected = selected,
    )

private fun lightTheme(
    selected: Boolean,
): LightTheme =
    LightTheme(
        text = R.string.theme_light,
        icon = if (selected) LightMode.filled else LightMode.outlined,
        selected = selected,
    )

private fun darkTheme(
    selected: Boolean,
): DarkTheme =
    DarkTheme(
        text = R.string.theme_dark,
        icon = if (selected) DarkMode.filled else DarkMode.outlined,
        selected = selected,
    )
