package com.sottti.roller.coasters.model

internal data class NavigationBarActions(
    val onDestinationSelected: (NavigationBarDestination) -> Unit,
)