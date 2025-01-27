package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class SettingsState(
    val dynamicColor: DynamicColorState?,
    val theme: ThemeState,
    val themePicker: ThemePickerState?,
    val colorContrast: ColorContrastState,
    val colorContrastPicker : ColorContrastPickerState?,
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
internal data class ThemeState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedTheme: SelectedThemeState,
    val icon: IconState,
)

@Immutable
internal data class ColorContrastState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedColorContrast: SelectedColorContrastState,
    val icon: IconState,
)

@Immutable
internal sealed class SelectedThemeState {
    data object Loading : SelectedThemeState()
    data class Loaded(val theme: ThemeUi) : SelectedThemeState()
}

@Immutable
internal sealed class SelectedColorContrastState {
    data object Loading : SelectedColorContrastState()
    data class Loaded(val colorContrast: ColorContrastUi) : SelectedColorContrastState()
}

@Immutable
internal data class ThemePickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val themes: List<ThemeUi>,
)

@Immutable
internal data class ColorContrastPickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val contrasts: List<ColorContrastUi>,
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

@Immutable
internal sealed class ColorContrastUi(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class StandardContrast(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : ColorContrastUi(text, icon, selected)

    class MediumContrast(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : ColorContrastUi(text, icon, selected)

    class HighContrast(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : ColorContrastUi(text, icon, selected)

    class SystemContrast(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : ColorContrastUi(text, icon, selected)
}