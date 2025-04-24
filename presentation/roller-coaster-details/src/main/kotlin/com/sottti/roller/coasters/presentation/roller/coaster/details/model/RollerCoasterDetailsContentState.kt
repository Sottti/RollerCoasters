package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class RollerCoasterDetailsContentState {
    @Immutable
    data class Loaded(
        val rollerCoaster: RollerCoasterDetailsViewState,
    ) : RollerCoasterDetailsContentState()

    @Immutable
    data object Error : RollerCoasterDetailsContentState()

    @Immutable
    object Loading : RollerCoasterDetailsContentState()
}