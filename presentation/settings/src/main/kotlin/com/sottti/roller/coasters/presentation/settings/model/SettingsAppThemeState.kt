package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class AppThemeState(
    val listItem: AppThemeListItemState,
    val picker: ThemePickerState?,
)

@Immutable
internal data class AppThemeListItemState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedAppTheme: SelectedAppThemeState,
    val icon: IconState,
)

@Immutable
internal sealed class SelectedAppThemeState {
    data object Loading : SelectedAppThemeState()
    data class Loaded(val appTheme: AppThemeUi) : SelectedAppThemeState()
}

@Immutable
internal data class ThemePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val appThemes: List<AppThemeUi>,
)

@Immutable
internal sealed class AppThemeUi(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class DarkTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppThemeUi(text, icon, selected)

    class LightTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppThemeUi(text, icon, selected)

    class SystemTheme(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppThemeUi(text, icon, selected)
}