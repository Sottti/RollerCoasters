package com.sottti.roller.coasters.presentation.design.system.dimensions.data

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Dimensions

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
internal fun dimensions(): Dimensions {
    val windowSizeClass = calculateWindowSizeClass(LocalContext.current as Activity)
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> compactDimensions()
        WindowWidthSizeClass.Medium -> mediumDimensions()
        WindowWidthSizeClass.Expanded -> expandedDimensions()
        else -> mediumDimensions()
    }
}

@Composable
private fun compactDimensions() =
    Dimensions(
        small = 2.dp,
        medium = 4.dp,
        large = 8.dp
    )

@Composable
private fun mediumDimensions() =
    Dimensions(
        small = 4.dp,
        medium = 8.dp,
        large = 16.dp
    )

@Composable
private fun expandedDimensions() =
    Dimensions(
        small = 6.dp,
        medium = 12.dp,
        large = 24.dp
    )