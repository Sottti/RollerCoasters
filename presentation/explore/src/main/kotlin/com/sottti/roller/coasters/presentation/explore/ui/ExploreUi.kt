package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sottti.roller.coasters.presentation.explore.data.ExploreViewModel

@Composable
public fun ExploreUi(navController: NavHostController) {
    ExploreUiInternal(navController)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExploreUiInternal(
    navController: NavHostController,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val lazyListState = rememberLazyListState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ExploreTopBar(lazyListState, navController) },
    ) { paddingValues ->
        val state by viewModel.state.collectAsStateWithLifecycle()
        Column(modifier = Modifier.padding(paddingValues)) {

            RollerCoastersList(listState = lazyListState, state = state)
        }
    }
}