package com.sottti.roller.coasters.presentation.favourites.model

import com.sottti.roller.coasters.domain.model.ImageUrl

internal data class FavouritesRollerCoaster(
    val id: Int,
    val imageUrl: ImageUrl?,
    val name: String,
    val parkName: String,
)