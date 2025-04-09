package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.presentation.explore.data.filtersInitialState
import com.sottti.roller.coasters.presentation.explore.model.ExplorePreviewState
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.navigation.ExploreNavigator
import com.sottti.roller.coasters.presentation.fixtures.anotherStat
import com.sottti.roller.coasters.presentation.fixtures.anotherStatDetail
import com.sottti.roller.coasters.presentation.fixtures.stat
import com.sottti.roller.coasters.presentation.fixtures.statDetail
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

private fun explorePreviewState(
    refreshState: LoadState = NotLoading(endOfPaginationReached = false),
    appendState: LoadState = NotLoading(endOfPaginationReached = false),
    prependState: LoadState = NotLoading(endOfPaginationReached = false),
    data: List<ExploreRollerCoaster> = exploreRollerCoasters(),
) = ExplorePreviewState(
    filters = filtersInitialState(),
    navigator = previewNavigator(),
    onAction = {},
    onListStateCreated = {},
    rollerCoasters = exploreRollerCoastersPagingDataFlow(
        appendState = appendState,
        data = data,
        prependState = prependState,
        refreshState = refreshState,
    ),
)

private fun previewNavigator(): ExploreNavigator = object : ExploreNavigator {
    override fun navigateBack() {}
    override fun navigateToRollerCoasterDetails(rollerCoasterId: RollerCoasterId) {}
    override fun navigateToSettings() {}
}

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

private fun exploreRollerCoasters() =
    listOf(
        exploreRollerCoaster(),
        anotherExploreRollerCoaster(),
    )

private fun exploreRollerCoaster(): ExploreRollerCoaster =
    ExploreRollerCoaster(
        imageUrl = rollerCoaster.pictures.main?.url,
        parkName = rollerCoaster.park.name.value,
        rollerCoasterName = rollerCoaster.name.current.value,
        stat = stat,
        statDetail = statDetail,
    )

private fun anotherExploreRollerCoaster(): ExploreRollerCoaster =
    ExploreRollerCoaster(
        imageUrl = anotherRollerCoaster.pictures.main?.url,
        parkName = anotherRollerCoaster.park.name.value,
        rollerCoasterName = anotherRollerCoaster.name.current.value,
        stat = anotherStat,
        statDetail = anotherStatDetail,
    )