package com.sottti.roller.coasters.navigation.bar.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.navigation.bar.data.NavigationBarViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun NavigationBar(
    mainNavHostController: NavHostController,
    viewModel: NavigationBarViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val nestedScrollConnection = scrollBehavior.nestedScrollConnection
    val navigationBarNavHost = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                navController = mainNavHostController,
                scrollBehavior = scrollBehavior,
                state = state.topBar
            )
        },
        bottomBar = {
            BottomBar(
                navController = navigationBarNavHost,
                navigationBarItems = state.items,
                navigationBarActions = viewModel.actions,
            )
        },
    ) { paddingValues ->
        NavigationBarNavHost(
            navController = navigationBarNavHost,
            nestedScrollConnection = nestedScrollConnection,
            paddingValues = paddingValues,
        )
    }
}