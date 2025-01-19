package com.sottti.roller.coasters.app.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import co.cuvva.presentation.design.system.icons.Icons

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    TopAppBar(
        title = {},
        actions = { SettingsAction() },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
private fun SettingsAction() {
    IconButton(onClick = { }) {
        Icon(
            painter = painterResource(Icons.Settings.outlined),
            contentDescription = stringResource(Icons.Settings.descriptionResId)
        )
    }
}