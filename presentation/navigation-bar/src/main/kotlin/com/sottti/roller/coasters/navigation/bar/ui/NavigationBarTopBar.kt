package com.sottti.roller.coasters.navigation.bar.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import co.cuvva.presentation.design.system.icons.model.IconState
import co.cuvva.presentation.design.system.icons.ui.Icon
import com.sottti.roller.coasters.navigation.bar.data.TopBarState
import com.sottti.roller.coasters.navigation.bar.navigation.NavigationDestination

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopBar(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    state: TopBarState,
) {
    val currentDestination =
        navController.currentBackStackEntryAsState().value?.destination?.route

    TopAppBar(
        title = {},
        actions = { SettingsAction(icon = state.settingsIcon, navController = navController) },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
private fun SettingsAction(
    icon: IconState,
    navController: NavController,
) {
    Icon(
        state = icon,
        onClick = { navController.navigate(NavigationDestination.Settings.route) },
    )
}