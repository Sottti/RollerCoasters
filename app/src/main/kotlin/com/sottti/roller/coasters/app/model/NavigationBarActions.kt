package com.sottti.roller.coasters.app.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class NavigationBarActions(
    val onDestinationSelected: (NavigationBarDestination) -> Unit,
)