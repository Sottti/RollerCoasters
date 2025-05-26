package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class AppColorContrastState(
    val listItem: AppColorContrastListItemState,
    val notAvailableMessage: AppColorContrastNotAvailableMessageState?,
    val picker: AppColorContrastPickerState?,
)

@Immutable
internal data class AppColorContrastListItemState(
    @StringRes val headline: Int,
    @StringRes val supporting: Int,
    val selectedAppColorContrast: SelectedAppColorContrastState,
    val icon: IconState,
)

@Immutable
internal sealed class SelectedAppColorContrastState {
    data object Loading : SelectedAppColorContrastState()
    data class Loaded(val appColorContrast: AppColorContrastUi) : SelectedAppColorContrastState()
}

@Immutable
internal data class AppColorContrastNotAvailableMessageState(
    @StringRes val dismiss: Int,
    @StringRes val text: Int,
    @StringRes val title: Int,
)

@Immutable
internal data class AppColorContrastPickerState(
    @StringRes val confirm: Int,
    @StringRes val dismiss: Int,
    @StringRes val title: Int,
    val appColorContrasts: List<AppColorContrastUi>,
)

@Immutable
internal sealed class AppColorContrastUi(
    @StringRes val text: Int,
    val icon: IconState,
    val selected: Boolean,
) {
    class StandardContrast(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppColorContrastUi(text, icon, selected)

    class MediumContrast(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppColorContrastUi(text, icon, selected)

    class HighContrast(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppColorContrastUi(text, icon, selected)

    class SystemContrast(
        @StringRes text: Int,
        icon: IconState,
        selected: Boolean,
    ) : AppColorContrastUi(text, icon, selected)
}