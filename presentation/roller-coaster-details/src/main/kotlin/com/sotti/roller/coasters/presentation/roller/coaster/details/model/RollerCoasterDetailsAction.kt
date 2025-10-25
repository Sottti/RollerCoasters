package com.sotti.roller.coasters.presentation.roller.coaster.details.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface RollerCoasterDetailsAction {
    @Immutable
    data object ToggleFavourite : RollerCoasterDetailsAction
}
