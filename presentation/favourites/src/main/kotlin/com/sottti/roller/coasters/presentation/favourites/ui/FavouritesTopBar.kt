package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesAction
import com.sottti.roller.coasters.presentation.top.bars.MainTopBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun FavouritesTopBar(
    lazyListState: LazyListState,
    onAction: (FavouritesAction) -> Unit,
    onNavigateToSettings: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    MainTopBar(scrollBehavior = scrollBehavior) { onNavigateToSettings() }
}