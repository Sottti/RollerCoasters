package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.ui.Icon
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.TopBarState

@Composable
@ExperimentalMaterial3Api
internal fun TopBar(
    onBackNavigation: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    state: TopBarState,
) {
    TopAppBar(
        title = {},
        scrollBehavior = scrollBehavior,
        navigationIcon = { navigationIcon(state, onBackNavigation) }
    )
}

@Composable
private fun navigationIcon(
    state: TopBarState,
    onBackNavigation: () -> Unit,
) {
    Icon(
        state = state.icon,
        onClick = { onBackNavigation() },
    )
}