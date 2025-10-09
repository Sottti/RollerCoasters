package com.sottti.roller.coasters.presentation.home.model

import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

internal sealed interface HomeActions {
    data class DestinationSelected(val destination: NavigationDestination) : HomeActions
}
