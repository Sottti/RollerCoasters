package com.sottti.roller.coasters.app.model

internal data class NavigationBarActions(
    val onDestinationSelected: (NavigationBarDestination) -> Unit,
)