package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.search.data.SearchViewModel
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchPreviewState
import com.sottti.roller.coasters.presentation.search.model.SearchViewState
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar

@Composable
public fun SearchUi(
    paddingValues: PaddingValues,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
) {
    val viewModel = hiltViewModel<SearchViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    SearchUi(
        onAction = viewModel.onAction,
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        paddingValues = paddingValues,
        state = state,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUi(
    onAction: (SearchAction) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    paddingValues: PaddingValues,
    state: SearchViewState,
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
    ) { innerPaddingValues ->
        val rememberedPaddingValues = remember(innerPaddingValues, paddingValues) {
            PaddingValues(
                start = innerPaddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = innerPaddingValues.calculateEndPadding(LayoutDirection.Ltr),
                top = innerPaddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
            )
        }
        SearchUiContent(
            listState = listState,
            onAction = onAction,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            paddingValues = paddingValues,
            rememberedPaddingValues = rememberedPaddingValues,
            scrollBehavior = scrollBehavior,
            state = state,
        )
    }
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
            paddingValues = state.paddingValues,
            state = state.state,
        )
    }
}