package com.sottti.roller.coasters.navigation.bar.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.navigation.bar.R
import com.sottti.roller.coasters.navigation.bar.model.NavigationBarItem
import com.sottti.roller.coasters.navigation.bar.model.NavigationBarItems
import com.sottti.roller.coasters.navigation.bar.navigation.NavigationBarDestination

internal fun navigationBarItems(
    selectedItem: NavigationBarDestination = NavigationBarDestination.Explore,
): NavigationBarItems =
    NavigationBarItems(
        items = listOf(explore(selectedItem), favourites(selectedItem), aboutMe(selectedItem)),
        selectedItem = selectedItem,
    )


private fun explore(
    selectedItem: NavigationBarDestination,
) = NavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_explore,
    icon = when (selectedItem) {
        NavigationBarDestination.Explore -> Icons.Explore.Filled
        else -> Icons.Explore.Outlined
    },
    destination = NavigationBarDestination.Explore,
)

private fun favourites(
    selectedItem: NavigationBarDestination,
) = NavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_favourites,
    icon = when (selectedItem) {
        NavigationBarDestination.Favourites -> Icons.Star.Filled
        else -> Icons.Star.Outlined
    },
    destination = NavigationBarDestination.Favourites,
)

private fun aboutMe(
    selectedItem: NavigationBarDestination,
) = NavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_about_me,
    icon = when (selectedItem) {
        NavigationBarDestination.AboutMe -> Icons.AccountCircle.Filled
        else -> Icons.AccountCircle.Outlined
    },
    destination = NavigationBarDestination.AboutMe,
)