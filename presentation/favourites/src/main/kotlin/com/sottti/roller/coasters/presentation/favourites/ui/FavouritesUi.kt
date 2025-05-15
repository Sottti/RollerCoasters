package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.roller.coasters.presentation.favourites.data.FavouritesViewModel
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesAction
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import com.sottti.roller.coasters.presentation.favourites.ui.FavouritesEvent.ScrollToTop
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
public fun FavouritesUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
) {
    FavouritesUi(
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        onScrollToTop = onScrollToTop,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FavouritesUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    viewModel: FavouritesViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rollerCoasters = state.rollerCoasters.collectAsLazyPagingItems()

    FavouritesUi(
        onAction = viewModel.onAction,
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onListCreated = { lazyListState, scrollBehavior ->
            FavouritesUiEffects(
                events = viewModel.events,
                lazyListState = lazyListState,
                scrollBehavior = scrollBehavior,
                onScrollToTop = onScrollToTop,
            )
        },
        onNavigateToSettings = onNavigateToSettings,
        rollerCoasters = rollerCoasters,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FavouritesUi(
    onAction: (FavouritesAction) -> Unit,
    onListCreated: @Composable (LazyListState, TopAppBarScrollBehavior) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
) {
    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    onListCreated(lazyListState, scrollBehavior)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                scrollBehavior = scrollBehavior,
                onNavigateToSettings = onNavigateToSettings
            )
        },
    ) { paddingValues ->
        FavouritesRollerCoastersList(
            listState = lazyListState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = onAction,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            paddingValues = paddingValues,
            rollerCoasters = rollerCoasters,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FavouritesUiEffects(
    events: Flow<FavouritesEvent>,
    lazyListState: LazyListState,
    onScrollToTop: (() -> Unit) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        onScrollToTop {
            coroutineScope.launch {
                lazyListState.animateScrollToItem(0)
                scrollBehavior.state.contentOffset = 0f
            }
        }
    }

    LaunchedEffect(events) {
        events.collect { event ->
            when (event) {
                ScrollToTop -> coroutineScope.launch {
                    lazyListState.animateScrollToItem(0)
                    scrollBehavior.state.contentOffset = 0f
                }
            }
        }
    }
}