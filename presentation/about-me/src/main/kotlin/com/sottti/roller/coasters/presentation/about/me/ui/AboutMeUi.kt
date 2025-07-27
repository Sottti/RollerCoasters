package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.about.me.data.AboutMeViewModel
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar
import com.sottti.roller.coasters.presentation.utils.override
import com.sottti.roller.coasters.presentation.utils.plus
import kotlinx.coroutines.launch

@Composable
public fun AboutMeUi(
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    onShowBottomSheet: (@Composable ColumnScope.() -> Unit) -> Unit,
    paddingValues: PaddingValues,
) {
    AboutMeUi(
        onNavigateToSettings = onNavigateToSettings,
        onScrollToTop = onScrollToTop,
        onShowBottomSheet = onShowBottomSheet,
        paddingValues = paddingValues,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AboutMeUi(
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    onShowBottomSheet: (@Composable ColumnScope.() -> Unit) -> Unit,
    paddingValues: PaddingValues,
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
        paddingValues = paddingValues,
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
    paddingValues: PaddingValues,
    state: AboutMeState,
) {
    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    key(lazyListState, scrollBehavior) { onListCreated(lazyListState, scrollBehavior) }
    val showTitleAfterIndex = 2
    val showTitle by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > showTitleAfterIndex }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                onNavigateToSettings = onNavigateToSettings,
                scrollBehavior = scrollBehavior,
                showTitle = showTitle,
                titleResId = state.title,
            )
        }
    ) { innerPaddingValues ->
        val mergedPaddingValues =
            paddingValues
                .override(top = dimensions.padding.zero) +
                    innerPaddingValues
                        .override(bottom = dimensions.padding.zero)
        AboutMeUiContent(
            listState = lazyListState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = onAction,
            onShowBottomSheet = onShowBottomSheet,
            paddingValues = mergedPaddingValues,
            state = state,
        )
    }
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
            paddingValues = state.paddingValues,
            state = state.state,
        )
    }
}