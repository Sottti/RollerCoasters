package com.sottti.roller.coasters.presentation.roller.coaster.details.model

internal sealed interface RollerCoasterDetailsAction {
    data object ToggleFavourite : RollerCoasterDetailsAction
}