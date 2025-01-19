package com.sottti.roller.coasters.app.model

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.app.navigation.NavigationBarDestination

@Immutable
internal data class NavigationBarItems(
    val items: List<NavigationBarItem>,
    val selectedItem: NavigationBarDestination,
)