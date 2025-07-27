package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
public fun ExploreUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    padding: PaddingValues,
) {
    ExploreUi(
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        onScrollToTop = onScrollToTop,
        padding = padding,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExploreUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    padding: PaddingValues,
    viewModel: ExploreViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rollerCoasters = viewModel.rollerCoasters.collectAsLazyPagingItems()

    ExploreUi(
        filters = state.filters,
        onAction = viewModel.onAction,
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onListCreated = { lazyListState ->
            ExploreUiEffects(
                events = viewModel.events,
                lazyListState = lazyListState,
                onScrollToTop = onScrollToTop,
            )
        },
        onNavigateToSettings = onNavigateToSettings,
        padding = padding,
        rollerCoasters = rollerCoasters,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ExploreUi(
    filters: Filters,
    onAction: (ExploreAction) -> Unit,
    onListCreated: @Composable (LazyListState) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<ExploreRollerCoaster>,
) {
    val lazyListState = rememberLazyListState()
    onListCreated(lazyListState)

    ExploreContent(
        filters = filters,
        lazyListState = lazyListState,
        onAction = onAction,
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        outerPadding = padding,
        rollerCoasters = rollerCoasters,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExploreUiEffects(
    events: Flow<ExploreEvent>,
    lazyListState: LazyListState,
    onScrollToTop: (() -> Unit) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        onScrollToTop { coroutineScope.launch { lazyListState.animateScrollToItem(0) } }
    }

    LaunchedEffect(events) {
        events.collect { event ->
            when (event) {
                ScrollToTop -> coroutineScope.launch { lazyListState.scrollToItem(0) }
            }
        }
    }
}