package com.sottti.roller.coasters.presentation.home.data

import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.home.R
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItem
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

internal fun navigationBarItems(): List<HomeNavigationBarItem> = listOf(
    explore(),
    favourites(),
    search(),
    aboutMe(),
)


private fun explore() = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_explore,
    destination = NavigationDestination.Explore,
    selectedIcon = Icons.Explore.filled,
    unselectedIcon = Icons.Explore.outlined,
)

private fun search() = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_search,
    destination = NavigationDestination.Search,
    selectedIcon = Icons.Search.outlined,
    unselectedIcon = Icons.Search.outlined,
)

private fun favourites() = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_favourites,
    destination = NavigationDestination.Favourites,
    selectedIcon = Icons.Star.filled,
    unselectedIcon = Icons.Star.outlined,
)

private fun aboutMe() = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_about_me,
    destination = NavigationDestination.AboutMe,
    selectedIcon = Icons.AccountCircle.filled,
    unselectedIcon = Icons.AccountCircle.outlined,
)
