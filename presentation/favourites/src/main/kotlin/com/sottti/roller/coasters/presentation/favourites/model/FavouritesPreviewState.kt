package com.sottti.roller.coasters.presentation.favourites.model

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

internal data class FavouritesPreviewState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val onListCreated: @Composable (LazyListState, TopAppBarScrollBehavior) -> Unit,
    val onNavigateToRollerCoaster: (Int) -> Unit,
    val onNavigateToSettings: () -> Unit,
    val rollerCoasters: Flow<PagingData<FavouritesRollerCoaster>>,
)