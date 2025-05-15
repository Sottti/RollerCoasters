package com.sottti.roller.coasters.presentation.settings.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastListItemState
import com.sottti.roller.coasters.presentation.settings.model.AppColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.AppLanguageListItemState
import com.sottti.roller.coasters.presentation.settings.model.AppLanguageState
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemListItemState
import com.sottti.roller.coasters.presentation.settings.model.AppMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.AppSelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.AppThemeListItemState
import com.sottti.roller.coasters.presentation.settings.model.AppThemeState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.SettingsTopBarState

internal fun initialState(
    dynamicColorAvailable: Boolean,
): SettingsState = SettingsState(
    appColorContrast = appColorContrastInitialState(),
    dynamicColor = dynamicColorInitialState().takeIf { dynamicColorAvailable },
    appLanguage = appLanguageInitialState(),
    appMeasurementSystem = appMeasurementSystemInitialState(),
    appTheme = appThemeInitialState(),
    topBar = topBarInitialState(),
)

private fun appColorContrastInitialState() = AppColorContrastState(
    listItem = AppColorContrastListItemState(
        headline = R.string.color_contrast_color_headline,
        icon = Icons.Visibility.outlined,
        selectedAppColorContrast = SelectedAppColorContrastState.Loading,
        supporting = R.string.color_contrast_color_supporting,
    ),
    notAvailableMessage = null,
    picker = null,
)

private fun dynamicColorInitialState() = DynamicColorState(
    checkedState = DynamicColorCheckedState.Loading,
    headline = R.string.dynamic_color_headline,
    supporting = R.string.dynamic_color_supporting,
    icon = Icons.Palette.outlined,
)

private fun appLanguageInitialState() = AppLanguageState(
    listItem = AppLanguageListItemState(
        headline = R.string.language_headline,
        icon = Icons.Language.outlined,
        supporting = R.string.language_supporting,
        selectedAppLanguage = AppSelectedLanguageState.Loading,
    ),
    picker = null,
)


private fun appThemeInitialState() = AppThemeState(
    listItem = AppThemeListItemState(
        headline = R.string.theme_headline,
        supporting = R.string.theme_supporting,
        selectedAppTheme = SelectedAppThemeState.Loading,
        icon = Icons.BrightnessMedium.outlined
    ),
    picker = null,
)

private fun appMeasurementSystemInitialState() = AppMeasurementSystemState(
    listItem = AppMeasurementSystemListItemState(
        headline = R.string.measurement_system_headline,
        supporting = R.string.measurement_system_supporting,
        icon = Icons.Straighten.outlined,
        selectedAppMeasurementSystem = SelectedAppMeasurementSystemState.Loading,
    ),
    picker = null,
)

private fun topBarInitialState(): SettingsTopBarState =
    SettingsTopBarState(title = R.string.title, icon = Icons.ArrowBack.filled)