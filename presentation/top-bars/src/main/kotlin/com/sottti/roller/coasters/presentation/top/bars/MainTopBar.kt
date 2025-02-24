package com.sottti.roller.coasters.presentation.top.bars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import co.cuvva.presentation.design.system.icons.data.Icons.Settings
import co.cuvva.presentation.design.system.icons.ui.Icon
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination

@Composable
@OptIn(ExperimentalMaterial3Api::class)
public fun MainTopBar(
    navController: NavHostController,
) {
    TopAppBar(
        title = {},
        actions = { Icon(navController) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}

@Composable
private fun Icon(navController: NavHostController) {
    Icon(
        state = Settings.Outlined,
        onClick = { navController.navigate(NavigationDestination.Settings.route) },
    )
}