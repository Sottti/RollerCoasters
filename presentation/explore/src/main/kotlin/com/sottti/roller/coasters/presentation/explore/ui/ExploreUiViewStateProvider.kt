package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.sottti.roller.coasters.presentation.explore.data.filtersInitialState
import com.sottti.roller.coasters.presentation.explore.fixtures.exploreRollerCoasters
import com.sottti.roller.coasters.presentation.explore.model.ExplorePreviewState
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class ExploreUiViewStateProvider : PreviewParameterProvider<ExplorePreviewState> {
    override val values: Sequence<ExplorePreviewState> = sequenceOf(
        loadingState,
        loadedStateAppendLoading,
        loadedStatePrependLoading,
        loadedStateAppendPrependBothLoading,
        loadedStateNoPagination,
        loadedStateAppendEndReached,
        loadedStatePrependEndReached,
        loadedStateAppendPrependBothEndsReached,
        emptyState,
        errorState,
    )
}

private val loadingState = explorePreviewState(
    refreshState = Loading,
    data = emptyList(),
)
private val loadedStateAppendLoading = explorePreviewState(appendState = Loading)
private val loadedStatePrependLoading = explorePreviewState(prependState = Loading)
private val loadedStateAppendPrependBothLoading =
    explorePreviewState(appendState = Loading, prependState = Loading)
private val loadedStateNoPagination = explorePreviewState()
private val loadedStateAppendEndReached = explorePreviewState(
    appendState = NotLoading(endOfPaginationReached = true)
)
private val loadedStatePrependEndReached = explorePreviewState(
    prependState = NotLoading(endOfPaginationReached = true)
)
private val loadedStateAppendPrependBothEndsReached = explorePreviewState(
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true)
)

private val emptyState = explorePreviewState(
    refreshState = NotLoading(endOfPaginationReached = true),
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true),
    data = emptyList(),
)

private val errorState = explorePreviewState(
    refreshState = LoadState.Error(Exception()),
    data = emptyList(),
)

private fun explorePreviewState(
    refreshState: LoadState = NotLoading(endOfPaginationReached = false),
    appendState: LoadState = NotLoading(endOfPaginationReached = false),
    prependState: LoadState = NotLoading(endOfPaginationReached = false),
    data: List<ExploreRollerCoaster> = exploreRollerCoasters(),
) = ExplorePreviewState(
    filters = filtersInitialState(),
    onAction = {},
    onListCreated = {},
    rollerCoasters = exploreRollerCoastersPagingDataFlow(
        appendState = appendState,
        data = data,
        prependState = prependState,
        refreshState = refreshState,
    ),
)

private fun exploreRollerCoastersPagingDataFlow(
    refreshState: LoadState,
    appendState: LoadState,
    prependState: LoadState,
    data: List<ExploreRollerCoaster>,
): Flow<PagingData<ExploreRollerCoaster>> =
    MutableStateFlow(
        PagingData.from(
            data = data,
            sourceLoadStates = LoadStates(
                refresh = refreshState,
                prepend = prependState,
                append = appendState,
            )
        )
    )