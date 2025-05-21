package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.about.me.data.AboutMeViewModel
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar
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
            AboutMeUiEffects(
                lazyListState = lazyListState,
                onScrollToTop = onScrollToTop,
                scrollBehavior = scrollBehavior,
            )
        },
        onNavigateToSettings = onNavigateToSettings,
        onShowBottomSheet = onShowBottomSheet,
        paddingValues = paddingValues,
        state = state,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AboutMeUiEffects(
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
    onListCreated(lazyListState, scrollBehavior)

    val contentWindowInsets =
        ScaffoldDefaults
            .contentWindowInsets
            .only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)

    val showTitle by remember(lazyListState) {
        derivedStateOf { lazyListState.firstVisibleItemIndex > 2 }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 0.dp,
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                bottom = paddingValues.calculateBottomPadding(),
            ),
        topBar = {
            MainTopBar(
                onNavigateToSettings = onNavigateToSettings,
                scrollBehavior = scrollBehavior,
                showTitle = showTitle,
                titleResId = state.title,
            )
        },
        contentWindowInsets = contentWindowInsets

    ) { innerPaddingValues ->
        AboutMeUiContent(
            listState = lazyListState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = onAction,
            onShowBottomSheet = onShowBottomSheet,
            paddingValues = innerPaddingValues,
            state = state,
        )
    }
}