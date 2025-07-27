package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.about.me.data.AboutMeViewModel
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import kotlinx.coroutines.launch

@Composable
public fun AboutMeUi(
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    onShowBottomSheet: (@Composable ColumnScope.() -> Unit) -> Unit,
    padding: PaddingValues,
) {
    AboutMeUi(
        onNavigateToSettings = onNavigateToSettings,
        onScrollToTop = onScrollToTop,
        onShowBottomSheet = onShowBottomSheet,
        padding = padding,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AboutMeUi(
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    onShowBottomSheet: (@Composable ColumnScope.() -> Unit) -> Unit,
    padding: PaddingValues,
    viewModel: AboutMeViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AboutMeUi(
        onAction = viewModel.onAction,
        onListCreated = { lazyListState, scrollBehavior ->
            val coroutineScope = rememberCoroutineScope()
            val currentScrollBehavior by rememberUpdatedState(scrollBehavior)
            LaunchedEffect(key1 = onScrollToTop) {
                onScrollToTop {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                        currentScrollBehavior.state.contentOffset = 0f
                    }
                }
            }
        },
        onNavigateToSettings = onNavigateToSettings,
        onShowBottomSheet = onShowBottomSheet,
        padding = padding,
        state = state,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun AboutMeUi(
    onAction: (AboutMeAction) -> Unit,
    onListCreated: @Composable (LazyListState, TopAppBarScrollBehavior) -> Unit,
    onNavigateToSettings: () -> Unit,
    onShowBottomSheet: (@Composable ColumnScope.() -> Unit) -> Unit,
    padding: PaddingValues,
    state: AboutMeState,
) {
    val lazyListState = rememberLazyListState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    key(lazyListState, scrollBehavior) { onListCreated(lazyListState, scrollBehavior) }
    val showTitleAfterIndex = 2
    val showTitle by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > showTitleAfterIndex }
    }

    AboutMeContent(
        lazyListState = lazyListState,
        onAction = onAction,
        onNavigateToSettings = onNavigateToSettings,
        onShowBottomSheet = onShowBottomSheet,
        outerPadding = padding,
        scrollBehavior = scrollBehavior,
        showTitle = showTitle,
        state = state,
    )
}

@Composable
@RollerCoastersPreview
@OptIn(ExperimentalMaterial3Api::class)
internal fun AboutMeUiPreview(
    @PreviewParameter(AboutMeUiStateProvider::class)
    state: AboutMePreviewState,
) {
    RollerCoastersPreviewTheme {
        AboutMeUi(
            onAction = state.onAction,
            onListCreated = state.onListCreated,
            onNavigateToSettings = state.onNavigateToSettings,
            onShowBottomSheet = {},
            padding = state.padding,
            state = state.state,
        )
    }
}
