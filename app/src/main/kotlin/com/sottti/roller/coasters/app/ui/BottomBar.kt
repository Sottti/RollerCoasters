package com.sottti.roller.coasters.app.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.sottti.roller.coasters.app.model.NavigationBarActions
import com.sottti.roller.coasters.app.model.NavigationBarItem
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
                icon = { NavigationBarItemIcon(item) },
                label = { Text(stringResource(item.labelResId)) },
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

@Composable
private fun NavigationBarItemIcon(item: NavigationBarItem) {
    Icon(
        painter = painterResource(item.iconResId),
        contentDescription = stringResource(item.iconDescriptionResId),
    )
}