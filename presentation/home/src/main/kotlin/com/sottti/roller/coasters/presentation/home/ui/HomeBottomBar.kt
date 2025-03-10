package com.sottti.roller.coasters.presentation.home.ui

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.home.model.HomeActions
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItem
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItems

@Composable
internal fun BottomBar(
    actions: HomeActions,
    navController: NavController,
    navigationBarItems: HomeNavigationBarItems,
    onNavigationBarItemClick: (item: HomeNavigationBarItem) -> Unit,
) {
    NavigationBar {
        navigationBarItems.items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon) },
                label = { Text.Vanilla(item.labelResId) },
                selected = navigationBarItems.selectedItem.route == item.destination.route,
                onClick = {
                    onClick(
                        actions = actions,
                        item = item,
                        navController = navController,
                        onNavigationBarItemClick = onNavigationBarItemClick,
                    )
                }
            )
        }
    }
}

private fun onClick(
    actions: HomeActions,
    item: HomeNavigationBarItem,
    navController: NavController,
    onNavigationBarItemClick: (item: HomeNavigationBarItem) -> Unit,
) {
    onNavigationBarItemClick(item)
    actions.onDestinationSelected(item.destination)
    navController.navigate(item.destination.route) {
        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}