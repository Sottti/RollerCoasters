package com.sottti.roller.coasters.presentation.favourites.model

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.domain.model.ImageUrl

@Immutable
internal data class FavouritesRollerCoaster(
    val id: Int,
    val imageUrl: ImageUrl?,
    val name: String,
    val parkName: String,
)
