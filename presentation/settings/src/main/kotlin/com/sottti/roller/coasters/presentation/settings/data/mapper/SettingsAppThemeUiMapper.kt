package com.sottti.roller.coasters.presentation.settings.data.mapper

import androidx.compose.runtime.Composable
import com.sotti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sotti.roller.coasters.presentation.design.system.icons.data.Icons.DarkMode
import com.sotti.roller.coasters.presentation.design.system.icons.data.Icons.LightMode
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.presentation.design.system.dialogs.radioButtons.DialogRadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppThemeUi

@Composable
internal fun AppThemeUi.toRadioButtonOption(): DialogRadioButtonOption =
    DialogRadioButtonOption(text, icon, selected)

internal fun DialogRadioButtonOption.toAppThemeUi(
    themes: List<AppThemeUi>,
): AppThemeUi = themes.find { it.text == text } ?: themes.first()

internal fun AppTheme.toPresentationModel(selected: Boolean): AppThemeUi =
    when (this) {
        AppTheme.LightAppTheme -> lightTheme(selected)
        AppTheme.DarkAppTheme -> darkTheme(selected)
        AppTheme.SystemAppTheme -> systemTheme(selected)
    }

private fun systemTheme(
    selected: Boolean,
): AppThemeUi.SystemTheme =
    AppThemeUi.SystemTheme(
        text = R.string.theme_system,
        icon = if (selected) Icons.BrightnessAuto.filled else Icons.BrightnessAuto.outlined,
        selected = selected,
    )

private fun lightTheme(
    selected: Boolean,
): AppThemeUi.LightTheme =
    AppThemeUi.LightTheme(
        text = R.string.theme_light,
        icon = if (selected) LightMode.filled else LightMode.outlined,
        selected = selected,
    )

private fun darkTheme(
    selected: Boolean,
): AppThemeUi.DarkTheme =
    AppThemeUi.DarkTheme(
        text = R.string.theme_dark,
        icon = if (selected) DarkMode.filled else DarkMode.outlined,
        selected = selected,
    )

internal fun AppThemeUi.toDomain(): AppTheme =
    when (this) {
        is AppThemeUi.DarkTheme -> AppTheme.DarkAppTheme
        is AppThemeUi.LightTheme -> AppTheme.LightAppTheme
        is AppThemeUi.SystemTheme -> AppTheme.SystemAppTheme
    }