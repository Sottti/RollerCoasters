package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import co.cuvva.presentation.design.system.icons.ui.Icon
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.settings.model.TopBarState

@Composable
@ExperimentalMaterial3Api
internal fun TopBar(
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    state: TopBarState,
) {
    TopAppBar(
        title = { Text.Vanilla(state.title) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            Icon(
                state = state.icon,
                onClick = { navController.popBackStack() },
            )
        }
    )
}