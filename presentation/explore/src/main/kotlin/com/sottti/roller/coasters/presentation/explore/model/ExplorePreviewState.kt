package com.sottti.roller.coasters.presentation.explore.model

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import com.sottti.roller.coasters.presentation.explore.navigation.ExploreNavigator
import kotlinx.coroutines.flow.Flow

internal data class ExplorePreviewState(
    val filters: Filters,
    val navigator: ExploreNavigator,
    val onAction: (ExploreAction) -> Unit,
    val onListStateCreated: @Composable (LazyListState) -> Unit,
    val rollerCoasters: Flow<PagingData<ExploreRollerCoaster>>,
)