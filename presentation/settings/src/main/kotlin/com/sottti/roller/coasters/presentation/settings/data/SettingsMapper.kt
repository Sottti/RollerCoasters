package com.sottti.roller.coasters.presentation.settings.data

import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.data.Icons.DarkMode
import co.cuvva.presentation.design.system.icons.data.Icons.LightMode
import co.cuvva.presentation.design.system.icons.data.Icons.Smartphone
import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.presentation.design.system.dialogs.RadioButtonOption
import com.sottti.roller.coasters.presentation.settings.R
import com.sottti.roller.coasters.presentation.settings.model.AppTheme

internal fun AppTheme.toDomainModel(): Theme =
    when (this) {
        is AppTheme.DarkTheme -> Theme.DarkTheme
        is AppTheme.LightTheme -> Theme.LightTheme
        is AppTheme.SystemTheme -> Theme.SystemTheme
    }

internal fun Theme.toPresentationModel(isSelectedTheme: Boolean): AppTheme =
    when (this) {
        is Theme.DarkTheme -> darkTheme(isSelectedTheme)
        is Theme.LightTheme -> lightTheme(isSelectedTheme)
        is Theme.SystemTheme -> systemTheme(isSelectedTheme)
    }

private fun systemTheme(
    isSelectedTheme: Boolean,
): AppTheme.SystemTheme = AppTheme.SystemTheme(
    text = R.string.theme_system,
    icon = if (isSelectedTheme) Smartphone.Filled else Smartphone.Outlined,
    selected = isSelectedTheme,
)

private fun lightTheme(
    isSelectedTheme: Boolean,
): AppTheme.LightTheme = AppTheme.LightTheme(
    text = R.string.theme_light,
    icon = if (isSelectedTheme) LightMode.Filled else LightMode.Outlined,
    selected = isSelectedTheme,
)

private fun darkTheme(
    isSelectedTheme: Boolean,
): AppTheme.DarkTheme = AppTheme.DarkTheme(
    text = R.string.theme_dark,
    icon = if (isSelectedTheme) DarkMode.Filled else DarkMode.Outlined,
    selected = isSelectedTheme,
)

@Composable
internal fun AppTheme.toRadioButtonOption(): RadioButtonOption =
    RadioButtonOption(text, icon, selected)

internal fun RadioButtonOption.toAppTheme(
    themes: List<AppTheme>,
): AppTheme = themes.find { it.text == text } ?: themes.first()

