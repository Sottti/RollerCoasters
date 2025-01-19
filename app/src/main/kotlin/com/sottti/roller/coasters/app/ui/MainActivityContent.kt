package com.sottti.roller.coasters.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.app.data.MainActivityViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun MainActivityContent(
    viewModel: MainActivityViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val nestedScrollConnection = scrollBehavior.nestedScrollConnection

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController = navController, scrollBehavior = scrollBehavior) },
        bottomBar = {
            BottomBar(
                navController = navController,
                navigationBarItems = state,
                navigationBarActions = viewModel.actions,
            )
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            nestedScrollConnection = nestedScrollConnection,
            paddingValues = paddingValues
        )
    }
}