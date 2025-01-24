package com.sottti.roller.coasters.navigation.bar.model

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.navigation.bar.navigation.NavigationBarDestination

@Immutable
internal data class NavigationBarItems(
    val items: List<NavigationBarItem>,
    val selectedItem: NavigationBarDestination,
)