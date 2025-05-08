package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.settings.model.SettingsTopBarState

@Composable
@ExperimentalMaterial3Api
internal fun TopBar(
    onBackNavigation: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    state: SettingsTopBarState,
) {
    TopAppBar(
        title = { Text.Vanilla(state.title) },
        scrollBehavior = scrollBehavior,
        navigationIcon = { navigationIcon(state, onBackNavigation) }
    )
}

@Composable
private fun navigationIcon(
    state: SettingsTopBarState,
    onBackNavigation: () -> Unit,
) {
    Icon(
        state = state.icon,
        onClick = { onBackNavigation() },
    )
}