package com.sottti.roller.coasters.app.data

import androidx.compose.runtime.Immutable
import co.cuvva.presentation.design.system.icons.model.IconState
import com.sottti.roller.coasters.app.model.NavigationBarItems

@Immutable
internal data class MainActivityState(
    val navigationBarItems: NavigationBarItems,
    val topBar: TopBarState,
)

@Immutable
internal data class TopBarState(
    val settingsIcon: IconState,
)