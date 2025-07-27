package com.sottti.roller.coasters.presentation.favourites.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

@Immutable
@OptIn(ExperimentalMaterial3Api::class)
internal data class FavouritesPreviewState(
    val onListCreated: @Composable (LazyListState, TopAppBarScrollBehavior) -> Unit,
    val onNavigateToRollerCoaster: (Int) -> Unit,
    val onNavigateToSettings: () -> Unit,
    val padding: PaddingValues,
    val rollerCoasters: Flow<PagingData<FavouritesRollerCoaster>>,
)