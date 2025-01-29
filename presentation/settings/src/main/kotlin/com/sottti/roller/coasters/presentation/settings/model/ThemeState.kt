package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class ThemeState(
    val listItem: ThemeListItemState,
    val picker: ThemePickerState?,
)

@Immutable
internal data class ThemeListItemState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedTheme: SelectedThemeState,
    val icon: IconState,
)

@Immutable
internal sealed class SelectedThemeState {
    data object Loading : SelectedThemeState()
    data class Loaded(val theme: ThemeUi) : SelectedThemeState()
}

@Immutable
internal data class ThemePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val themes: List<ThemeUi>,
)

@Immutable
internal sealed class ThemeUi(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class DarkTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : ThemeUi(text, icon, selected)

    class LightTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : ThemeUi(text, icon, selected)

    class SystemTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : ThemeUi(text, icon, selected)
}