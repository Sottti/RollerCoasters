package com.sottti.roller.coasters.presentation.favourites.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.playground.RainbowLazyColumn
import com.sottti.roller.coasters.presentation.favourites.data.FavouritesViewModel

@Composable
public fun FavouritesUi() {
    FavouritesUi(viewModel = hiltViewModel())
}

@Composable
private fun FavouritesUi(
    viewModel: FavouritesViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RainbowLazyColumn(
        seedColor = state,
    )
}