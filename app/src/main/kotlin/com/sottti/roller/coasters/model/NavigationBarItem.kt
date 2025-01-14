package com.sottti.roller.coasters.model

import androidx.compose.ui.graphics.vector.ImageVector

internal data class NavigationBarItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)