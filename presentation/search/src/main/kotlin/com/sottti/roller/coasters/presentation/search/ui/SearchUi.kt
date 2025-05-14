package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.search.data.SearchViewModel
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchResult
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar

@Composable
public fun SearchUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
) {
    SearchUi(
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        onScrollToTop = onScrollToTop,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    viewModel: SearchViewModel,
) {
//    val state by viewModel.state.collectAsStateWithLifecycle()
//
//    SearchUi(
//        onAction = viewModel.onAction,
//        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
//        onListStateCreated = { lazyListState, scrollBehavior ->
//            FavouritesUiEffects(
//                events = viewModel.events,
//                lazyListState = lazyListState,
//                scrollBehavior = scrollBehavior,
//                onScrollToTop = onScrollToTop,
//            )
//        },
//        onNavigateToSettings = onNavigateToSettings,
//        rollerCoasters = rollerCoasters,
//    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUi(
    onAction: (SearchAction) -> Unit,
    onListStateCreated: @Composable (LazyListState, TopAppBarScrollBehavior) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    results: SearchResult,
) {
    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    onListStateCreated(lazyListState, scrollBehavior)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                scrollBehavior = scrollBehavior,
                onNavigateToSettings = onNavigateToSettings
            )
        },
    ) { paddingValues ->
        SearchResults(
            listState = lazyListState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = onAction,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            paddingValues = paddingValues,
            results = results,
        )
    }
}