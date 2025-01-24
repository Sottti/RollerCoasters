package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class SettingsState(
    val appTheme: AppThemeState,
    val appThemePicker: AppThemePickerState?,
    val dynamicColor: DynamicColorState?,
    val topBar: TopBarState,
)

@Immutable
internal data class TopBarState(
    @StringRes val title: Int,
    val icon: IconState,
)

@Immutable
internal data class DynamicColorState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val checked: Boolean,
    val icon: IconState,
)

@Immutable
internal data class AppThemeState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    @StringRes val trailing: Int,
    val icon: IconState,
)

@Immutable
internal data class AppThemePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val selectedTheme: ThemeWithText,
    val themes: List<ThemeWithText>,
)

@Immutable
internal sealed class ThemeWithText(
    @StringRes val text: Int,
) {
    class DarkTheme(@StringRes text: Int) : ThemeWithText(text)
    class LightTheme(@StringRes text: Int) : ThemeWithText(text)
    class SystemTheme(@StringRes text: Int) : ThemeWithText(text)
}