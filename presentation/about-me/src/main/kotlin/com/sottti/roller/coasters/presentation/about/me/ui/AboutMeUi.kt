package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.about.me.data.AboutMeViewModel
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.utils.OnScrollToTopUiEffects

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
            OnScrollToTopUiEffects(
                lazyListState = lazyListState,
                scrollBehavior = scrollBehavior,
                onScrollToTop = onScrollToTop,
            )
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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    key(lazyListState, scrollBehavior) { onListCreated(lazyListState, scrollBehavior) }

    AboutMeContent(
        lazyListState = lazyListState,
        onAction = onAction,
        onNavigateToSettings = onNavigateToSettings,
        onShowBottomSheet = onShowBottomSheet,
        outerPadding = padding,
        scrollBehavior = scrollBehavior,
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