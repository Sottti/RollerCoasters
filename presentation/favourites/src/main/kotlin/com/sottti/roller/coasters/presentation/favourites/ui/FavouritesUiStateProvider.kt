package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.sottti.roller.coasters.presentation.favourites.fixtures.favouritesRollerCoasters
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesPreviewState
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class FavouritesUiStateProvider : PreviewParameterProvider<FavouritesPreviewState> {
    override val values: Sequence<FavouritesPreviewState>
        get() = sequenceOf(
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

private val favouritesRollerCoasters = favouritesRollerCoasters()

private val loadingState = favouritesPreviewState(
    refreshState = Loading,
    data = emptyList(),
)
private val loadedStateAppendLoading = favouritesPreviewState(appendState = Loading)
private val loadedStatePrependLoading = favouritesPreviewState(prependState = Loading)
private val loadedStateAppendPrependBothLoading =
    favouritesPreviewState(appendState = Loading, prependState = Loading)
private val loadedStateNoPagination = favouritesPreviewState()
private val loadedStateAppendEndReached = favouritesPreviewState(
    appendState = NotLoading(endOfPaginationReached = true),
)
private val loadedStatePrependEndReached = favouritesPreviewState(
    prependState = NotLoading(endOfPaginationReached = true)
)
private val loadedStateAppendPrependBothEndsReached = favouritesPreviewState(
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true)
)

private val emptyState = favouritesPreviewState(
    refreshState = NotLoading(endOfPaginationReached = true),
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true),
    data = emptyList(),
)

private val errorState = favouritesPreviewState(
    refreshState = LoadState.Error(Exception()),
    data = emptyList(),
)

@OptIn(ExperimentalMaterial3Api::class)
private fun favouritesPreviewState(
    refreshState: LoadState = NotLoading(endOfPaginationReached = false),
    appendState: LoadState = NotLoading(endOfPaginationReached = false),
    prependState: LoadState = NotLoading(endOfPaginationReached = false),
    data: List<FavouritesRollerCoaster> = favouritesRollerCoasters,
) = FavouritesPreviewState(
    onNavigateToSettings = {},
    onNavigateToRollerCoaster = {},
    onListCreated = { a, b -> },
    padding = PaddingValues(),
    rollerCoasters = favouritesRollerCoastersPagingDataFlow(
        appendState = appendState,
        data = data,
        prependState = prependState,
        refreshState = refreshState,
    ),
)

private fun favouritesRollerCoastersPagingDataFlow(
    refreshState: LoadState,
    appendState: LoadState,
    prependState: LoadState,
    data: List<FavouritesRollerCoaster>,
): Flow<PagingData<FavouritesRollerCoaster>> =
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
