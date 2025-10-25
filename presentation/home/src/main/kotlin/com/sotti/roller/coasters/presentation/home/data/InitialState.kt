package com.sotti.roller.coasters.presentation.home.data

import com.sotti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sotti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sotti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sotti.roller.coasters.domain.settings.model.theme.SystemTheme
import com.sotti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sotti.roller.coasters.presentation.home.R
import com.sotti.roller.coasters.presentation.home.model.HomeNavigationBarItemState
import com.sotti.roller.coasters.presentation.home.model.HomeNavigationBarState
import com.sotti.roller.coasters.presentation.home.model.HomeState
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination

internal fun initialState(
    theme: SystemTheme,
) = HomeState(
    colorContrast = ResolvedColorContrast.StandardContrast,
    dynamicColor = ResolvedDynamicColor(enabled = false),
    navigationBarItems = navigationBarItems(),
    theme = when (theme) {
        SystemTheme.LightSystemTheme -> ResolvedTheme.LightResolvedTheme
        SystemTheme.DarkSystemTheme -> ResolvedTheme.DarkResolvedTheme
    },
)

internal fun navigationBarItems(
    selectedItem: NavigationDestination = NavigationDestination.Explore,
): HomeNavigationBarState = HomeNavigationBarState(
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
) = HomeNavigationBarItemState(
    labelResId = R.string.navigation_bar_item_label_explore,
    icon = when (selectedItem) {
        NavigationDestination.Explore -> Icons.Explore.filled
        else -> Icons.Explore.outlined
    },
    destination = NavigationDestination.Explore,
)

private fun search() = HomeNavigationBarItemState(
    labelResId = R.string.navigation_bar_item_label_search,
    icon = Icons.Search.outlined,
    destination = NavigationDestination.Search,
)

private fun favourites(
    selectedItem: NavigationDestination,
) = HomeNavigationBarItemState(
    labelResId = R.string.navigation_bar_item_label_favourites,
    icon = when (selectedItem) {
        NavigationDestination.Favourites -> Icons.Star.filled
        else -> Icons.Star.outlined
    },
    destination = NavigationDestination.Favourites,
)

private fun aboutMe(
    selectedItem: NavigationDestination,
) = HomeNavigationBarItemState(
    labelResId = R.string.navigation_bar_item_label_about_me,
    icon = when (selectedItem) {
        NavigationDestination.AboutMe -> Icons.AccountCircle.filled
        else -> Icons.AccountCircle.outlined
    },
    destination = NavigationDestination.AboutMe,
)
