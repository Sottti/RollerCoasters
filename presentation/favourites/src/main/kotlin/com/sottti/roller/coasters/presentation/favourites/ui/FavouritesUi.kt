package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.favourites.data.FavouritesViewModel
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesPreviewState
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar
import kotlinx.coroutines.launch

@Composable
public fun FavouritesUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    paddingValues: PaddingValues,
) {
    FavouritesUi(
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        onScrollToTop = onScrollToTop,
        paddingValues = paddingValues,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FavouritesUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    paddingValues: PaddingValues,
    viewModel: FavouritesViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rollerCoasters = state.rollerCoasters.collectAsLazyPagingItems()

    FavouritesUi(
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onListCreated = { lazyListState, scrollBehavior ->
            FavouritesUiEffects(
                lazyListState = lazyListState,
                scrollBehavior = scrollBehavior,
                onScrollToTop = onScrollToTop,
            )
        },
        onNavigateToSettings = onNavigateToSettings,
        paddingValues = paddingValues,
        rollerCoasters = rollerCoasters,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FavouritesUi(
    onListCreated: @Composable (LazyListState, TopAppBarScrollBehavior) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    paddingValues: PaddingValues,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
) {
    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    onListCreated(lazyListState, scrollBehavior)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                onNavigateToSettings = onNavigateToSettings,
                scrollBehavior = scrollBehavior,
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
        FavouritesRollerCoastersContent(
            listState = lazyListState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onNavigateToRollerCoaster = onNavigateToRollerCoaster,
            paddingValues = rememberedPaddingValues,
            rollerCoasters = rollerCoasters,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FavouritesUiEffects(
    lazyListState: LazyListState,
    onScrollToTop: (() -> Unit) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val coroutineScope = rememberCoroutineScope()
    val rememberedOnScrollToTop = rememberUpdatedState(onScrollToTop)
    LaunchedEffect(Unit) {
        rememberedOnScrollToTop.value {
            coroutineScope.launch {
                lazyListState.animateScrollToItem(0)
                scrollBehavior.state.contentOffset = 0f
            }
        }
    }
}

@Composable
@LightDarkThemePreview
@OptIn(ExperimentalMaterial3Api::class)
internal fun FavouritesUiPreview(
    @PreviewParameter(FavouritesUiStateProvider::class)
    state: FavouritesPreviewState,
) {
    RollerCoastersPreviewTheme {
        FavouritesUi(
            onListCreated = state.onListCreated,
            onNavigateToRollerCoaster = state.onNavigateToRollerCoaster,
            onNavigateToSettings = state.onNavigateToSettings,
            paddingValues = state.paddingValues,
            rollerCoasters = state.rollerCoasters.collectAsLazyPagingItems(),
        )
    }
}