package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.about.me.data.AboutMeViewModel
import com.sottti.roller.coasters.presentation.design.system.playground.RainbowLazyColumn

@Composable
public fun AboutMe(
    nestedScrollConnection: NestedScrollConnection,
) {
    AboutMeInternal(nestedScrollConnection)
}

@Composable
internal fun AboutMeInternal(
    nestedScrollConnection: NestedScrollConnection,
    viewModel: AboutMeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RainbowLazyColumn(
        nestedScrollConnection = nestedScrollConnection,
        seedColor = state,
    )
}