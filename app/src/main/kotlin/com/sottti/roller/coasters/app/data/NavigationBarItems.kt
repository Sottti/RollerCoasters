package com.sottti.roller.coasters.app.data

import co.cuvva.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.R
import com.sottti.roller.coasters.app.model.NavigationBarItem
import com.sottti.roller.coasters.app.model.NavigationBarItems
import com.sottti.roller.coasters.app.navigation.NavigationBarDestination

internal fun navigationBarItems(
    selectedItem: NavigationBarDestination = NavigationBarDestination.Home,
): NavigationBarItems =
    NavigationBarItems(
        items = listOf(home(selectedItem), favourites(selectedItem), aboutMe(selectedItem)),
        selectedItem = selectedItem,
    )


private fun home(
    selectedItem: NavigationBarDestination,
) = NavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_home,
    icon = when (selectedItem) {
        NavigationBarDestination.Home -> Icons.Home.Filled
        else -> Icons.Home.Outlined
    },
    destination = NavigationBarDestination.Home,
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