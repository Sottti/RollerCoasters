package com.sottti.roller.coasters.presentation.design.system.dimensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
internal fun compactDimensions() =
    Dimensions(
        small = 2.dp,
        medium = 4.dp,
        large = 8.dp
    )

@Composable
internal fun mediumDimensions() =
    Dimensions(
        small = 4.dp,
        medium = 8.dp,
        large = 16.dp
    )

@Composable
internal fun expandedDimensions() =
    Dimensions(
        small = 6.dp,
        medium = 12.dp,
        large = 24.dp
    )