package com.sottti.roller.coasters.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.home.navigation.HomeNavigationBarDestination

@Immutable
internal data class HomeActions(
    val onDestinationSelected: (HomeNavigationBarDestination) -> Unit,
)