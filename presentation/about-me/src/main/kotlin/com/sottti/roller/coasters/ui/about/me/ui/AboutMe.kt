package com.sottti.roller.coasters.ui.about.me.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.design.system.playground.RainbowLazyColumn
import com.sottti.roller.coasters.ui.about.me.data.AboutMeViewModel

@Composable
fun AboutMe(
    nestedScrollConnection: NestedScrollConnection,
    paddingValues: PaddingValues,
    viewModel: AboutMeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RainbowLazyColumn(
        nestedScrollConnection = nestedScrollConnection,
        paddingValues = paddingValues,
        seedColor = state,
    )
}