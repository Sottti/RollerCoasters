package com.sottti.roller.coasters.presentation.home.data

import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.home.R
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItem
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

internal fun navigationBarItems(selected: NavigationDestination): List<HomeNavigationBarItem> = listOf(
    explore(selected),
    favourites(selected),
    search(selected),
    aboutMe(selected),
)

private fun explore(selected: NavigationDestination) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_explore,
    destination = NavigationDestination.Explore,
    icon = if (selected == NavigationDestination.Explore) Icons.Explore.filled else Icons.Explore.outlined,
)

private fun search(selected: NavigationDestination) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_search,
    destination = NavigationDestination.Search,
    icon = Icons.Search.outlined,
)

private fun favourites(selected: NavigationDestination) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_favourites,
    destination = NavigationDestination.Favourites,
    icon = if (selected == NavigationDestination.Favourites) Icons.Star.filled else Icons.Star.outlined,
)

private fun aboutMe(selected: NavigationDestination) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_about_me,
    destination = NavigationDestination.AboutMe,
    icon = if (selected == NavigationDestination.AboutMe) Icons.AccountCircle.filled else Icons.AccountCircle.outlined,
)
