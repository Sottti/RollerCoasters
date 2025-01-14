package com.sottti.roller.coasters.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import com.sottti.roller.coasters.model.NavigationBarDestination
import com.sottti.roller.coasters.model.NavigationBarItem

internal fun navigationBarItems(): List<NavigationBarItem> = listOf(
    home(),
    favourites(),
    aboutMe(),
)

private fun home() = NavigationBarItem(
    label = "Home",
    icon = Icons.Filled.Home,
    route = NavigationBarDestination.Home.route,
)

private fun favourites() = NavigationBarItem(
    label = "Favourites",
    icon = Icons.Filled.Favorite,
    route = NavigationBarDestination.Favourites.route,
)

private fun aboutMe() = NavigationBarItem(
    label = "Profile",
    icon = Icons.Filled.AccountCircle,
    route = NavigationBarDestination.AboutMe.route,
)