package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.playground.RainbowLazyColumn
import com.sottti.roller.coasters.presentation.favourites.data.FavouritesViewModel

@Composable
public fun FavouritesUi(
    nestedScrollConnection: NestedScrollConnection,
) {
    FavouritesUiInternal(nestedScrollConnection)
}

@Composable
private fun FavouritesUiInternal(
    nestedScrollConnection: NestedScrollConnection,
    viewModel: FavouritesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RainbowLazyColumn(
        nestedScrollConnection = nestedScrollConnection,
        seedColor = state,
    )
}