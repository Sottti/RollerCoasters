package com.sottti.roller.coasters.presentation.home.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.home.R
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItem
import com.sottti.roller.coasters.presentation.home.model.HomeNavigationBarItems
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

internal fun navigationBarItems(
    selectedItem: NavigationDestination = NavigationDestination.Explore,
): HomeNavigationBarItems = HomeNavigationBarItems(
    items = listOf(
        explore(selectedItem),
        favourites(selectedItem),
        search(),
        aboutMe(selectedItem),
    ),
    selectedItem = selectedItem,
)


private fun explore(
    selectedItem: NavigationDestination,
) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_explore,
    icon = when (selectedItem) {
        NavigationDestination.Explore -> Icons.Explore.Filled
        else -> Icons.Explore.Outlined
    },
    destination = NavigationDestination.Explore,
)

private fun search() = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_search,
    icon = Icons.Search.Outlined,
    destination = NavigationDestination.Search,
)

private fun favourites(
    selectedItem: NavigationDestination,
) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_favourites,
    icon = when (selectedItem) {
        NavigationDestination.Favourites -> Icons.Star.Filled
        else -> Icons.Star.Outlined
    },
    destination = NavigationDestination.Favourites,
)

private fun aboutMe(
    selectedItem: NavigationDestination,
) = HomeNavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_about_me,
    icon = when (selectedItem) {
        NavigationDestination.AboutMe -> Icons.AccountCircle.Filled
        else -> Icons.AccountCircle.Outlined
    },
    destination = NavigationDestination.AboutMe,
)