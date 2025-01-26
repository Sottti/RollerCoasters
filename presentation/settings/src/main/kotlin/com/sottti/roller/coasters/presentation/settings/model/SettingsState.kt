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
    val checkedState: DynamicColorCheckedState,
    val icon: IconState,
)

@Immutable
internal sealed class DynamicColorCheckedState {
    data object Loading : DynamicColorCheckedState()
    data class Loaded(
        val checked: Boolean
    ) : DynamicColorCheckedState()
}

@Immutable
internal data class AppThemeState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedTheme: CurrentThemeState,
    val icon: IconState,
)

@Immutable
internal sealed class CurrentThemeState {
    data object Loading : CurrentThemeState()
    data class Loaded(val theme: AppTheme) : CurrentThemeState()
}

@Immutable
internal data class AppThemePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val themes: List<AppTheme>,
)

@Immutable
internal sealed class AppTheme(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class DarkTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppTheme(text, icon, selected)

    class LightTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppTheme(text, icon, selected)

    class SystemTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppTheme(text, icon, selected)

}