package com.sotti.roller.coasters.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination

@Immutable
internal sealed interface HomeActions {
    @Immutable
    data class DestinationSelected(
        val destination: NavigationDestination,
    ) : HomeActions

    @Immutable
    data object NoOp : HomeActions
}
