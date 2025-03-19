package com.sottti.roller.coasters.presentation.settings.data.mapper

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import co.cuvva.presentation.design.system.icons.data.Icons.DarkMode
import co.cuvva.presentation.design.system.icons.data.Icons.LightMode
import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ThemeUi

@Composable
internal fun ThemeUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toThemeUi(
    themes: List<ThemeUi>,
): ThemeUi = themes.find { it.text == text } ?: themes.first()

internal fun Theme.toPresentationModel(isSelected: Boolean): ThemeUi =
    when (this) {
        is Theme.DarkTheme -> darkTheme(isSelected)
        is Theme.LightTheme -> lightTheme(isSelected)
        is Theme.SystemTheme -> systemTheme(isSelected)
    }

private fun systemTheme(
    isSelected: Boolean,
): ThemeUi.SystemTheme = ThemeUi.SystemTheme(
    text = R.string.theme_system,
    icon = if (isSelected) Icons.BrightnessAuto.Filled else Icons.BrightnessAuto.Outlined,
    selected = isSelected,
)

private fun lightTheme(
    isSelected: Boolean,
): ThemeUi.LightTheme = ThemeUi.LightTheme(
    text = R.string.theme_light,
    icon = if (isSelected) LightMode.Filled else LightMode.Outlined,
    selected = isSelected,
)

private fun darkTheme(
    isSelected: Boolean,
): ThemeUi.DarkTheme = ThemeUi.DarkTheme(
    text = R.string.theme_dark,
    icon = if (isSelected) DarkMode.Filled else DarkMode.Outlined,
    selected = isSelected,
)

internal fun ThemeUi.toDomain(): Theme =
    when (this) {
        is ThemeUi.DarkTheme -> Theme.DarkTheme
        is ThemeUi.LightTheme -> Theme.LightTheme
        is ThemeUi.SystemTheme -> Theme.SystemTheme
    }