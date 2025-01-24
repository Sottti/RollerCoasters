package com.sottti.roller.coasters.presentation.home.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.presentation.home.navigation.HomeNavigationBarDestination

@Immutable
internal data class NavigationBarState(
    val items: HomeNavigationBarItems,
    val topBar: TopBarState,
)

@Immutable
internal data class HomeNavigationBarItems(
    val items: List<HomeNavigationBarItem>,
    val selectedItem: HomeNavigationBarDestination,
)

@Immutable
internal data class HomeNavigationBarItem(
    @StringRes val labelResId: Int,
    val destination: HomeNavigationBarDestination,
    val icon: IconState,
)

@Immutable
internal data class TopBarState(
    val settingsIcon: IconState,
)