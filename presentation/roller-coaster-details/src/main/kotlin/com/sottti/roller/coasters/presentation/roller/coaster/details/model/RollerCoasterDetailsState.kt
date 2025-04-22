package com.sottti.roller.coasters.presentation.roller.coaster.details.model

import com.sottti.roller.coasters.domain.model.ImageUrl

internal sealed class RollerCoasterDetailsState {
    data class Loaded(val rollerCoaster: RollerCoasterDetails) : RollerCoasterDetailsState()
    data object Error : RollerCoasterDetailsState()
    object Loading : RollerCoasterDetailsState()
}

internal data class RollerCoasterDetails(
    val imageUrl: ImageUrl?,
    val parkName: String,
    val rollerCoasterName: String,
)