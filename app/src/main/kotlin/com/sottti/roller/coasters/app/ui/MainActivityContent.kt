package com.sottti.roller.coasters.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.app.data.MainActivityViewModel

@Composable
internal fun MainActivityContent(
    viewModel: MainActivityViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                navController = navController,
                navigationBarItems = state,
                navigationBarActions = viewModel.actions,
            )
        },
    ) { paddingValues -> NavHost(navController, paddingValues) }
}