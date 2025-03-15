package com.sottti.roller.coasters.presentation.settings.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastListItemState
import com.sottti.roller.coasters.presentation.settings.model.ColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.LanguageListItemState
import com.sottti.roller.coasters.presentation.settings.model.LanguageState
import com.sottti.roller.coasters.presentation.settings.model.MeasurementSystemListItemState
import com.sottti.roller.coasters.presentation.settings.model.MeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SelectedColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.SelectedMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SelectedThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.ThemeListItemState
import com.sottti.roller.coasters.presentation.settings.model.ThemeState
import com.sottti.roller.coasters.presentation.settings.model.TopBarState

internal fun initialState(
    dynamicColorAvailable: Boolean,
): SettingsState = SettingsState(
    colorContrast = colorContrastInitialState(),
    dynamicColor = dynamicColorInitialState().takeIf { dynamicColorAvailable },
    language = languageInitialState(),
    measurementSystem = measurementSystemInitialState(),
    theme = themeInitialState(),
    topBar = topBarInitialState(),
)

private fun colorContrastInitialState() = ColorContrastState(
    listItem = ColorContrastListItemState(
        headline = R.string.color_contrast_color_headline,
        icon = Icons.Visibility.Outlined,
        selectedColorContrast = SelectedColorContrastState.Loading,
        supporting = R.string.color_contrast_color_supporting,
    ),
    notAvailableMessage = null,
    picker = null,
)

private fun dynamicColorInitialState() = DynamicColorState(
    checkedState = DynamicColorCheckedState.Loading,
    headline = R.string.dynamic_color_headline,
    supporting = R.string.dynamic_color_supporting,
    icon = Icons.Palette.Outlined,
)

private fun languageInitialState() = LanguageState(
    listItem = LanguageListItemState(
        headline = R.string.language_headline,
        icon = Icons.Language.Outlined,
        supporting = R.string.language_supporting,
        selectedLanguage = SelectedLanguageState.Loading,
    ),
    picker = null,
)


private fun themeInitialState() = ThemeState(
    listItem = ThemeListItemState(
        headline = R.string.theme_headline,
        supporting = R.string.theme_supporting,
        selectedTheme = SelectedThemeState.Loading,
        icon = Icons.BrightnessMedium.Outlined
    ),
    picker = null,
)

private fun measurementSystemInitialState() = MeasurementSystemState(
    listItem = MeasurementSystemListItemState(
        headline = R.string.measurement_system_headline,
        supporting = R.string.measurement_system_supporting,
        icon = Icons.Straighten.Outlined,
        selectedMeasurementSystem = SelectedMeasurementSystemState.Loading,
    ),
    picker = null,
)

private fun topBarInitialState(): TopBarState =
    TopBarState(title = R.string.title, icon = Icons.ArrowBack.Filled)