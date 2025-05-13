package com.sottti.roller.coasters.presentation.favourites.ui

import android.R.attr.label
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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