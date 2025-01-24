package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.playground.RainbowLazyColumn
import com.sottti.roller.coasters.presentation.explore.data.ExploreViewModel

@Composable
public fun Explore(
    nestedScrollConnection: NestedScrollConnection,
) {
    ExploreInternal(nestedScrollConnection = nestedScrollConnection)
}

@Composable
internal fun ExploreInternal(
    nestedScrollConnection: NestedScrollConnection,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RainbowLazyColumn(
        nestedScrollConnection = nestedScrollConnection,
        seedColor = state,
    )
}