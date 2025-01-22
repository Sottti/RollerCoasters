package com.sottti.roller.coasters.app.ui

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.presentation.design.system.text.Text
import com.sottti.roller.coasters.app.model.NavigationBarActions
import com.sottti.roller.coasters.app.model.NavigationBarItems

@Composable
internal fun BottomBar(
    navController: NavController,
    navigationBarItems: NavigationBarItems,
    navigationBarActions: NavigationBarActions,
) {
    NavigationBar {
        navigationBarItems.items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon) },
                label = { Text(item.labelResId) },
                selected = navigationBarItems.selectedItem.route == item.destination.route,
                onClick = {
                    navigationBarActions.onDestinationSelected(item.destination)
                    navController.navigate(item.destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}