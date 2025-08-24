package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.favourites.data.FavouritesViewModel
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesPreviewState
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.utils.OnScrollToTopUiEffects

@Composable
public fun FavouritesUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    padding: PaddingValues,
) {
    FavouritesUi(
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        onScrollToTop = onScrollToTop,
        padding = padding,
        viewModel = hiltViewModel(),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FavouritesUi(
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onScrollToTop: (() -> Unit) -> Unit,
    padding: PaddingValues,
    viewModel: FavouritesViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rollerCoasters = state.rollerCoasters.collectAsLazyPagingItems()

    FavouritesUi(
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onListCreated = { lazyListState, scrollBehavior ->
            OnScrollToTopUiEffects(
                lazyListState = lazyListState,
                scrollBehavior = scrollBehavior,
                onScrollToTop = onScrollToTop,
            )
        },
        onNavigateToSettings = onNavigateToSettings,
        padding = padding,
        rollerCoasters = rollerCoasters,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FavouritesUi(
    onListCreated: @Composable (LazyListState, TopAppBarScrollBehavior) -> Unit,
    onNavigateToRollerCoaster: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    padding: PaddingValues,
    rollerCoasters: LazyPagingItems<FavouritesRollerCoaster>,
) {
    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    onListCreated(lazyListState, scrollBehavior)

    FavouritesContent(
        lazyListState = lazyListState,
        onNavigateToRollerCoaster = onNavigateToRollerCoaster,
        onNavigateToSettings = onNavigateToSettings,
        outerPadding = padding,
        rollerCoasters = rollerCoasters,
        scrollBehavior = scrollBehavior,
    )
}

@Composable
@RollerCoastersPreview
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
            padding = state.padding,
            rollerCoasters = state.rollerCoasters.collectAsLazyPagingItems(),
        )
    }
}