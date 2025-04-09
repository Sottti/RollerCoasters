package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.roller.coasters.presentation.explore.data.ExploreViewModel
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreEvent
import com.sottti.roller.coasters.presentation.explore.model.ExploreEvent.ScrollToTop
import com.sottti.roller.coasters.presentation.explore.model.ExploreRollerCoaster
import com.sottti.roller.coasters.presentation.explore.model.Filters
import com.sottti.roller.coasters.presentation.explore.navigation.ExploreNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
public fun ExploreUi(
    navigator: ExploreNavigator,
    onScrollToTop: (() -> Unit) -> Unit,
) {
    ExploreUiWrapper(
        navigator = navigator,
        onScrollToTop = onScrollToTop,
    )
}

@Composable
private fun ExploreUiWrapper(
    navigator: ExploreNavigator,
    onScrollToTop: (() -> Unit) -> Unit,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rollerCoasters = state.rollerCoasters.collectAsLazyPagingItems()

    ExploreUiContent(
        filters = state.filters,
        navigator = navigator,
        onAction = viewModel.onAction,
        onListStateCreated = { lazyListState ->
            ExploreUiEffects(viewModel.events, lazyListState, onScrollToTop)
        },
        rollerCoasters = rollerCoasters,
    )
}

@Composable
private fun ExploreUiEffects(
    events: Flow<ExploreEvent>,
    lazyListState: LazyListState,
    onScrollToTop: (() -> Unit) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        onScrollToTop {
            coroutineScope.launch { lazyListState.animateScrollToItem(0) }
        }
    }

    LaunchedEffect(events) {
        events.collect { event ->
            when (event) {
                ScrollToTop -> coroutineScope.launch { lazyListState.scrollToItem(0) }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ExploreUiContent(
    filters: Filters,
    navigator: ExploreNavigator,
    onAction: (ExploreAction) -> Unit,
    onListStateCreated: @Composable (LazyListState) -> Unit,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    val lazyListState = rememberLazyListState()
    onListStateCreated(lazyListState)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ExploreTopBar(
                filters = filters,
                lazyListState = lazyListState,
                navigator = navigator,
                onAction = onAction,
            )
        },
    ) { paddingValues ->
        RollerCoastersList(
            listState = lazyListState,
            paddingValues = paddingValues,
            rollerCoasters = rollerCoasters,
        )
    }
}