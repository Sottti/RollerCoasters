package com.sottti.roller.coasters.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sottti.roller.coasters.app.model.NavigationBarActions
import com.sottti.roller.coasters.app.model.NavigationBarItems

@Composable
internal fun MainActivityContent(
    actions: NavigationBarActions,
    state: NavigationBarItems,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                navController = navController,
                navigationBarItems = state,
                navigationBarActions = actions,
            )
        },
    ) { paddingValues -> NavHost(navController, paddingValues) }
}