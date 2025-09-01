package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.icons.ui.icon.Icon
import com.sottti.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.settings.model.SettingsTopBarState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopBar(
    onBackNavigation: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    state: SettingsTopBarState,
) {
    TopAppBar(
        title = { Text.Vanilla(state.title) },
        scrollBehavior = scrollBehavior,
        navigationIcon = { NavigationIcon(state, onBackNavigation) },
    )
}

@Composable
private fun NavigationIcon(
    state: SettingsTopBarState,
    onBackNavigation: () -> Unit,
) {
    Icon(
        iconState = state.icon,
        onClick = onBackNavigation,
    )
}
