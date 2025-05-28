package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

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
internal sealed interface SelectedAppThemeState {
    data object Loading : SelectedAppThemeState
    data class Loaded(val appTheme: AppThemeUi) : SelectedAppThemeState
}

@Immutable
internal data class ThemePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val appThemes: List<AppThemeUi>,
)

@Immutable
internal sealed interface AppThemeUi {
    @get:StringRes
    val text: Int
    val icon: IconState
    val selected: Boolean
}

@Immutable
internal data class DarkTheme(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppThemeUi

@Immutable
internal data class LightTheme(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppThemeUi

@Immutable
internal data class SystemTheme(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppThemeUi