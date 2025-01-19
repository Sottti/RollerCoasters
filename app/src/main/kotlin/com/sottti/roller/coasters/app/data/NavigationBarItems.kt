package com.sottti.roller.coasters.app.data

import co.cuvva.presentation.design.system.icons.Icons
import com.sottti.roller.coasters.R
import com.sottti.roller.coasters.app.model.NavigationBarDestination
import com.sottti.roller.coasters.app.model.NavigationBarItem
import com.sottti.roller.coasters.app.model.NavigationBarItems

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
    iconResId = when (selectedItem) {
        NavigationBarDestination.Home -> Icons.Home.filled
        else -> Icons.Home.outlined
    },
    iconDescriptionResId = Icons.Home.descriptionResId,
    destination = NavigationBarDestination.Home,
)

private fun favourites(
    selectedItem: NavigationBarDestination,
) = NavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_favourites,
    iconResId = when (selectedItem) {
        NavigationBarDestination.Favourites -> Icons.Star.filled
        else -> Icons.Star.outlined
    },
    iconDescriptionResId = Icons.Star.descriptionResId,
    destination = NavigationBarDestination.Favourites,
)

private fun aboutMe(
    selectedItem: NavigationBarDestination,
) = NavigationBarItem(
    labelResId = R.string.navigation_bar_item_label_about_me,
    iconResId = when (selectedItem) {
        NavigationBarDestination.AboutMe -> Icons.AccountCircle.filled
        else -> Icons.AccountCircle.outlined
    },
    iconDescriptionResId = Icons.AccountCircle.descriptionResId,
    destination = NavigationBarDestination.AboutMe,
)