package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.search.data.SearchViewModel
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchPreviewState
import com.sottti.roller.coasters.presentation.search.model.SearchViewState

@Composable
public fun SearchUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    padding: PaddingValues,
) {
    SearchUi(
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        onScrollToTop = onScrollToTop,
        padding = padding,
        viewModel = hiltViewModel()
    )
}

@Composable
private fun SearchUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    padding: PaddingValues,
    viewModel: SearchViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SearchUi(
        onAction = viewModel.onAction,
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        padding = padding,
        state = state,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUi(
    onAction: (SearchAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    padding: PaddingValues,
    state: SearchViewState,
) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val listState = rememberLazyListState()

    SearchUiContent(
        listState = listState,
        onAction = onAction,
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        outerPadding = padding,
        scrollBehavior = scrollBehavior,
        state = state,
    )
}

@Composable
@RollerCoastersPreview
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUiPreview(
    @PreviewParameter(SearchUiStateProvider::class) state: SearchPreviewState,
) {
    RollerCoastersPreviewTheme {
        SearchUi(
            onAction = state.onAction,
            onNavigateToRollerCoaster = state.onNavigateToRollerCoaster,
            onNavigateToSettings = state.onNavigateToSettings,
            padding = state.padding,
            state = state.state,
        )
    }
}
