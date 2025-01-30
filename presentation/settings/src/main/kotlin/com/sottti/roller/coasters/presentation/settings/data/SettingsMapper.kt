package com.sottti.roller.coasters.presentation.settings.data

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons
import co.cuvva.presentation.design.system.icons.data.Icons.DarkMode
import co.cuvva.presentation.design.system.icons.data.Icons.LightMode
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.Language
import com.sottti.roller.coasters.data.settings.model.Theme
import com.sottti.roller.coasters.presentation.design.system.dialogs.RadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.HighContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.MediumContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.StandardContrast
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastUi.SystemContrast
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi.EnglishGbLanguage
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi.GalicianLanguage
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi.SpanishSpainLanguage
import com.sottti.roller.coasters.presentation.settings.model.LanguageUi.SystemLanguage
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

internal fun LanguageUi.toDomainModel(): Language =
    when (this) {
        is EnglishGbLanguage -> Language.EnglishGbLanguage
        is SpanishSpainLanguage -> Language.SpanishSpainLanguage
        is GalicianLanguage -> Language.GalicianLanguage
        is SystemLanguage -> Language.SystemLanguage
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

@Composable
internal fun ThemeUi.toRadioButtonOption(): RadioButtonOption =
    RadioButtonOption(text, icon, selected)

@Composable
internal fun ColorContrastUi.toRadioButtonOption(): RadioButtonOption =
    RadioButtonOption(text, icon, selected)

@Composable
internal fun LanguageUi.toRadioButtonOption(): RadioButtonOption =
    RadioButtonOption(text, icon, selected)

internal fun RadioButtonOption.toThemeUi(
    themes: List<ThemeUi>,
): ThemeUi = themes.find { it.text == text } ?: themes.first()

internal fun RadioButtonOption.toColorContrastUi(
    contrasts: List<ColorContrastUi>,
): ColorContrastUi = contrasts.find { it.text == text } ?: contrasts.first()

internal fun RadioButtonOption.toLanguageUi(
    languages: List<LanguageUi>,
): LanguageUi = languages.find { it.text == text } ?: languages.first()

internal fun ColorContrast.toPresentationModel(isSelected: Boolean): ColorContrastUi =
    when (this) {
        ColorContrast.SystemContrast -> systemContrast(isSelected)
        ColorContrast.StandardContrast -> standardContrast(isSelected)
        ColorContrast.MediumContrast -> mediumContrast(isSelected)
        ColorContrast.HighContrast -> highContrast(isSelected)
    }

internal fun Language.toPresentationModel(isSelected: Boolean): LanguageUi =
    when (this) {
        Language.EnglishGbLanguage -> EnglishGbLanguage(
            text = R.string.language_english_gb,
            icon = Icons.LanguageEnglishGb.Rounded,
            selected = isSelected,
        )

        Language.SpanishSpainLanguage -> SpanishSpainLanguage(
            text = R.string.language_spanish_spain,
            icon = Icons.LanguageSpanish.Rounded,
            selected = isSelected,
        )

        Language.GalicianLanguage -> GalicianLanguage(
            text = R.string.language_galician,
            icon = Icons.Language.Rounded,
            selected = isSelected,
        )

        Language.SystemLanguage -> SystemLanguage(
            text = R.string.language_system,
            icon = if (isSelected) Icons.Smartphone.Filled else Icons.Smartphone.Outlined,
            selected = isSelected,
        )
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