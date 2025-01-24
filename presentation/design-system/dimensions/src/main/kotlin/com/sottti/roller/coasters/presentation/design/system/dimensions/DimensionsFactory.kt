package com.sottti.roller.coasters.presentation.design.system.dimensions

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
internal fun dimensions(): Dimensions {
    val windowSizeClass = calculateWindowSizeClass(LocalContext.current as Activity)
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> compactDimensions()
        WindowWidthSizeClass.Medium -> mediumDimensions()
        WindowWidthSizeClass.Expanded -> expandedDimensions()
        else -> mediumDimensions()
    }
}