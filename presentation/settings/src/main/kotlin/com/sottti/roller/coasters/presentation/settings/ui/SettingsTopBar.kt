package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import co.cuvva.presentation.design.system.icons.ui.Icon
import com.sottti.roller.coasters.presentation.settings.model.TopBarState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopBar(
    navController: NavHostController,
    state: TopBarState,
) {
    TopAppBar(
        title = { Text(text = stringResource(state.title)) },
        navigationIcon = {
            Icon(
                state = state.icon,
                onClick = { navController.popBackStack() },
            )
        }
    )
}