package com.sottti.roller.coasters.presentation.favourites.model

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class FavouritesState(
    val rollerCoasters: Flow<PagingData<FavouritesRollerCoaster>>,
)