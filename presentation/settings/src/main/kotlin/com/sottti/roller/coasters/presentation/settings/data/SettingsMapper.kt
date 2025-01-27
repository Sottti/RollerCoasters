package com.sottti.roller.coasters.presentation.settings.data

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import co.cuvva.presentation.design.system.icons.data.Icons.DarkMode
import co.cuvva.presentation.design.system.icons.data.Icons.LightMode
import co.cuvva.presentation.design.system.icons.data.Icons.Smartphone
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.Theme
import com.sottti.roller.coasters.presentation.design.system.dialogs.RadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.HighContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.MediumContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.StandardContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.SystemContrast
import com.sottti.roller.coasters.presentation.settings.model.ThemeUi

internal fun ColorContrastUi.toDomainModel(): ColorContrast =
    when (this) {
        is HighContrast -> ColorContrast.HighContrast
        is MediumContrast -> ColorContrast.MediumContrast
        is StandardContrast -> ColorContrast.StandardContrast
        is SystemContrast -> ColorContrast.SystemContrast
    }

internal fun ThemeUi.toDomainModel(): Theme =
    when (this) {
        is ThemeUi.DarkTheme -> Theme.DarkTheme
        is ThemeUi.LightTheme -> Theme.LightTheme
        is ThemeUi.SystemTheme -> Theme.SystemTheme
    }

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
    icon = if (isSelected) Smartphone.Filled else Smartphone.Outlined,
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

@Composable
internal fun ThemeUi.toRadioButtonOption(): RadioButtonOption =
    RadioButtonOption(text, icon, selected)

@Composable
internal fun ColorContrastUi.toRadioButtonOption(): RadioButtonOption =
    RadioButtonOption(text, icon, selected)

internal fun RadioButtonOption.toThemeUi(
    themes: List<ThemeUi>,
): ThemeUi = themes.find { it.text == text } ?: themes.first()

internal fun RadioButtonOption.toColorContrastUi(
    contrasts: List<ColorContrastUi>,
): ColorContrastUi = contrasts.find { it.text == text } ?: contrasts.first()

internal fun ColorContrast.toPresentationModel(isSelected: Boolean): ColorContrastUi =
    when (this) {
        ColorContrast.SystemContrast -> systemContrast(isSelected)
        ColorContrast.StandardContrast -> standardContrast(isSelected)
        ColorContrast.MediumContrast -> mediumContrast(isSelected)
        ColorContrast.HighContrast -> highContrast(isSelected)
    }

private fun systemContrast(
    isSelected: Boolean,
): SystemContrast = SystemContrast(
    icon = if (isSelected) Icons.BrightnessAuto.Filled else Icons.BrightnessAuto.Outlined,
    selected = isSelected,
    text = R.string.color_contrast_system_contrast,
)

private fun standardContrast(
    isSelected: Boolean,
): StandardContrast = StandardContrast(
    icon = if (isSelected) Icons.BrightnessStandard.Filled else Icons.BrightnessStandard.Outlined,
    selected = isSelected,
    text = R.string.color_contrast_standard_contrast,
)

private fun mediumContrast(
    isSelected: Boolean,
): MediumContrast = MediumContrast(
    icon = if (isSelected) Icons.BrightnessMedium.Filled else Icons.BrightnessMedium.Outlined,
    selected = isSelected,
    text = R.string.color_contrast_medium_contrast,
)

private fun highContrast(
    isSelected: Boolean,
): HighContrast = HighContrast(
    icon = if (isSelected) Icons.BrightnessHigh.Filled else Icons.BrightnessHigh.Outlined,
    selected = isSelected,
    text = R.string.color_contrast_high_contrast,
)