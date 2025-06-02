package com.sottti.roller.coasters.presentation.explore.model

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class ExplorePreviewState(
    val filters: Filters,
    val onAction: (ExploreAction) -> Unit,
    val onListCreated: @Composable (LazyListState) -> Unit,
    val rollerCoasters: Flow<PagingData<ExploreRollerCoaster>>,
)