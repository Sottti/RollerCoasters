package com.sottti.roller.coasters.presentation.top.bars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import co.cuvva.presentation.design.system.icons.data.Icons.Settings
import co.cuvva.presentation.design.system.icons.ui.Icon

@Composable
@OptIn(ExperimentalMaterial3Api::class)
public fun MainTopBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigateToSettings: () -> Unit,
) {
    val colors = when (scrollBehavior) {
        null -> TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        else -> TopAppBarDefaults.topAppBarColors()
    }

    TopAppBar(
        actions = { Icon(onNavigateToSettings) },
        colors = colors,
        scrollBehavior = scrollBehavior,
        title = {},
    )
}

@Composable
private fun Icon(onNavigateToSettings: () -> Unit) {
    Icon(
        state = Settings.Outlined,
        onClick = { onNavigateToSettings.invoke() },
    )
}