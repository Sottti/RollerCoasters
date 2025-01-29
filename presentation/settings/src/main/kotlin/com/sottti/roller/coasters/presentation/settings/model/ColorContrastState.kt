package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState

@Immutable
internal data class ColorContrastState(
    val listItem: ColorContrastListItemState,
    val notAvailableMessage: ColorContrastNotAvailableMessageState?,
    val picker: ColorContrastPickerState?,
)

@Immutable
internal data class ColorContrastListItemState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedColorContrast: SelectedColorContrastState,
    val icon: IconState,
)

@Immutable
internal sealed class SelectedColorContrastState {
    data object Loading : SelectedColorContrastState()
    data class Loaded(val colorContrast: ColorContrastUi) : SelectedColorContrastState()
}

@Immutable
internal data class ColorContrastNotAvailableMessageState(
    @StringRes val dismiss: Int,
    @StringRes val text: Int,
    @StringRes val title: Int,
)

@Immutable
internal data class ColorContrastPickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val contrasts: List<ColorContrastUi>,
)

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