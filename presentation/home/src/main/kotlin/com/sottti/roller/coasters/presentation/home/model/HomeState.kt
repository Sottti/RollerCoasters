package com.sottti.roller.coasters.presentation.home.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

@Immutable
internal data class NavigationBarState(
    val items: HomeNavigationBarItems,
)

@Immutable
internal data class HomeNavigationBarItems(
    val items: List<HomeNavigationBarItem>,
    val selectedItem: NavigationDestination,
)

@Immutable
internal data class HomeNavigationBarItem(
    @StringRes val labelResId: Int,
    val destination: NavigationDestination,
    val icon: IconState,
)