package com.sottti.roller.coasters.presentation.roller.coaster.details.model

internal sealed class RollerCoasterDetailsAction {
    data object ToggleFavourite : RollerCoasterDetailsAction()
}