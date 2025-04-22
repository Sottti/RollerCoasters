package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.roller.coasters.presentation.about.me.data.AboutMeViewModel
import com.sottti.roller.coasters.presentation.design.system.playground.RainbowLazyColumn

@Composable
public fun AboutMeUi(
) {
    AboutMeUi(viewModel = hiltViewModel())
}

@Composable
private fun AboutMeUi(
    viewModel: AboutMeViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RainbowLazyColumn(
        seedColor = state,
    )
}