package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.search.data.SearchViewModel
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchState
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar

@Composable
public fun SearchUi(
    paddingValues: PaddingValues,
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
    val state by viewModel.state.collectAsStateWithLifecycle()
    SearchUi(
        state = state,
        onAction = viewModel.onAction,
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUi(
    state: SearchState,
    onAction: (SearchAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val listState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                scrollBehavior = scrollBehavior,
                onNavigateToSettings = onNavigateToSettings,
            )
        },
    ) { paddingValues ->
        androidx.compose.foundation.layout.Column {
            SearchBar(
                query = state.searchBar.query,
                onQueryChange = { onAction(SearchAction.QueryChanged(it)) },
                onSearch = { onAction(SearchAction.SubmitQuery) },
                active = false,
                onActiveChange = {},
                placeholder = { androidx.compose.material3.Text(state.searchBar.hint) }
            )

            SearchResults(
                listState = listState,
                nestedScrollConnection = scrollBehavior.nestedScrollConnection,
                onAction = onAction,
                onNavigateToRollerCoaster = onNavigateToRollerCoaster,
                paddingValues = paddingValues,
                results = state.results,
            )
        }
    }
}