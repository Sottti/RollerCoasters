package com.sottti.roller.coasters.ui.favourites.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.playground.RainbowLazyColumn
import com.sottti.roller.coasters.ui.favourites.data.FavouritesViewModel

@Composable
public fun Favourites(
    nestedScrollConnection: NestedScrollConnection,
) {
    FavouritesInternal(nestedScrollConnection)
}

@Composable
internal fun FavouritesInternal(
    nestedScrollConnection: NestedScrollConnection,
    viewModel: FavouritesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RainbowLazyColumn(
        nestedScrollConnection = nestedScrollConnection,
        seedColor = state,
    )
}