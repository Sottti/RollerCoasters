package com.sottti.roller.coasters.presentation.favourites.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

internal data class FavouritesState(
    val rollerCoasters: Flow<PagingData<FavouritesRollerCoaster>>,
)