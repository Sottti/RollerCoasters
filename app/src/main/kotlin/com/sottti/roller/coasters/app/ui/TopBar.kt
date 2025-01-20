package com.sottti.roller.coasters.app.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import co.cuvva.presentation.design.system.icons.Icon
import co.cuvva.presentation.design.system.icons.Icons
import com.sottti.roller.coasters.app.navigation.NavigationDestination

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val currentDestination =
        navController.currentBackStackEntryAsState().value?.destination?.route

    TopAppBar(
        title = {},
        actions = { SettingsAction(navController) },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
private fun SettingsAction(navController: NavController) {
    Icon(
        contentDescriptionResId = Icons.Settings.descriptionResId,
        iconResId = Icons.Settings.outlined,
        onClick = { navController.navigate(NavigationDestination.Settings.route) },
    )
}