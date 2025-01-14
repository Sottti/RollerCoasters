package com.sottti.roller.coasters.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.data.MainActivityViewModel

@Composable
internal fun MainActivityContent(
    viewModel: MainActivityViewModel,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                navController = navController,
                navigationBarItems = viewModel.state.collectAsState().value,
                navigationBarActions = viewModel.actions,
            )
        },
    ) { paddingValues -> NavHost(navController, paddingValues) }
}