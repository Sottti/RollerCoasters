package com.sottti.roller.coasters.presentation.app.shell.model

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

@Immutable
internal sealed interface AppShellActions {
    @Immutable
    data class DestinationSelected(
        val destination: NavigationDestination,
    ) : AppShellActions

    @Immutable
    data object NoOp : AppShellActions
}
