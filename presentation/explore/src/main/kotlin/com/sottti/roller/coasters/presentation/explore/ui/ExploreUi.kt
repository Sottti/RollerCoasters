package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.fillMaxSize
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
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.explore.data.ExploreViewModel
import com.sottti.roller.coasters.presentation.explore.model.ExploreEvent.ScrollToTop
import com.sottti.roller.coasters.presentation.explore.navigation.ExploreNavigator
import kotlinx.coroutines.launch

@Composable
public fun ExploreUi(
    navigator: ExploreNavigator,
    onScrollToTop: (() -> Unit) -> Unit,
) {
    ExploreUiInternal(navigator = navigator, onScrollToTop = onScrollToTop)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExploreUiInternal(
    navigator: ExploreNavigator,
    onScrollToTop: (() -> Unit) -> Unit,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onAction = viewModel.onAction

    LaunchedEffect(Unit) {
        onScrollToTop {
            coroutineScope.launch { lazyListState.animateScrollToItem(0) }
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                ScrollToTop -> coroutineScope.launch { lazyListState.scrollToItem(0) }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ExploreTopBar(
                filters = state.filters,
                lazyListState = lazyListState,
                navigator = navigator,
                onAction = onAction,
            )
        },
    ) { paddingValues ->
        RollerCoastersList(
            listState = lazyListState,
            paddingValues = paddingValues,
            state = state.rollerCoastersFlow,
        )
    }
}

@Composable
internal fun ExploreUiPreview() {
    RollerCoastersPreviewTheme {

    }
}