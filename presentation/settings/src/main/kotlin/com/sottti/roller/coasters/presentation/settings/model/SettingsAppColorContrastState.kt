package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

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
internal sealed interface SelectedAppColorContrastState {
    @Immutable
    data object Loading : SelectedAppColorContrastState

    @Immutable
    data class Loaded(val appColorContrast: AppColorContrastUi) : SelectedAppColorContrastState
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
internal sealed interface AppColorContrastUi {
    @get:StringRes val text: Int
    val icon: IconState
    val selected: Boolean
}

@Immutable
internal data class StandardContrast(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppColorContrastUi

@Immutable
internal data class MediumContrast(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppColorContrastUi

@Immutable
internal data class HighContrast(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppColorContrastUi

@Immutable
internal data class SystemContrast(
    @StringRes override val text: Int,
    override val icon: IconState,
    override val selected: Boolean,
) : AppColorContrastUi