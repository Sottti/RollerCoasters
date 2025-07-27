package com.sottti.roller.coasters.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

@Immutable
internal data class HomeActions(
    val onDestinationSelected: (NavigationDestination) -> Unit,
)
