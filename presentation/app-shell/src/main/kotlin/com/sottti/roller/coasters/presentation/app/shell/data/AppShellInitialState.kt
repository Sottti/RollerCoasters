package com.sottti.roller.coasters.presentation.app.shell.data

import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sottti.roller.coasters.domain.settings.model.theme.SystemTheme
import com.sottti.roller.coasters.presentation.app.shell.R
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellNavigationBarItemState
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellNavigationBarState
import com.sottti.roller.coasters.presentation.app.shell.model.AppShellState
import com.sottti.roller.coasters.presentation.design.system.icons.data.Icons
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

internal fun initialState(
    theme: SystemTheme,
) = AppShellState(
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
): AppShellNavigationBarState = AppShellNavigationBarState(
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
) = AppShellNavigationBarItemState(
    labelResId = R.string.navigation_bar_item_label_explore,
    icon = when (selectedItem) {
        NavigationDestination.Explore -> Icons.Explore.filled
        else -> Icons.Explore.outlined
    },
    destination = NavigationDestination.Explore,
)

private fun search() = AppShellNavigationBarItemState(
    labelResId = R.string.navigation_bar_item_label_search,
    icon = Icons.Search.outlined,
    destination = NavigationDestination.Search,
)

private fun favourites(
    selectedItem: NavigationDestination,
) = AppShellNavigationBarItemState(
    labelResId = R.string.navigation_bar_item_label_favourites,
    icon = when (selectedItem) {
        NavigationDestination.Favourites -> Icons.Star.filled
        else -> Icons.Star.outlined
    },
    destination = NavigationDestination.Favourites,
)

private fun aboutMe(
    selectedItem: NavigationDestination,
) = AppShellNavigationBarItemState(
    labelResId = R.string.navigation_bar_item_label_about_me,
    icon = when (selectedItem) {
        NavigationDestination.AboutMe -> Icons.AccountCircle.filled
        else -> Icons.AccountCircle.outlined
    },
    destination = NavigationDestination.AboutMe,
)
