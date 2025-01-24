package com.sottti.roller.coasters.presentation.design.system.dimensions

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

@Composable
internal fun mockDimensions(
    windowWidthSizeClass: WindowWidthSizeClass,
): Dimensions {
    return when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact -> compactDimensions()
        WindowWidthSizeClass.Medium -> mediumDimensions()
        WindowWidthSizeClass.Expanded -> expandedDimensions()
        else -> mediumDimensions()
    }
}