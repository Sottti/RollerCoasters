package com.sottti.roller.coasters.navigation.bar.data

import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.navigation.bar.model.NavigationBarItems

@Immutable
internal data class NavigationBarState(
    val items: NavigationBarItems,
    val topBar: TopBarState,
)

@Immutable
internal data class TopBarState(
    val settingsIcon: IconState,
)