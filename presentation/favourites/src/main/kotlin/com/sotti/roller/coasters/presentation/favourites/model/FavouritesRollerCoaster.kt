package com.sotti.roller.coasters.presentation.favourites.model

import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.domain.model.ImageUrl

@Immutable
internal data class FavouritesRollerCoaster(
    val id: Int,
    val imageUrl: ImageUrl?,
    val name: String,
    val parkName: String,
)
