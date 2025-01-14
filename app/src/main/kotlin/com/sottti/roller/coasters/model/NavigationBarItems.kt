package com.sottti.roller.coasters.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class NavigationBarItems(
    val items: List<NavigationBarItem>,
    val selectedItem: NavigationBarDestination,
)