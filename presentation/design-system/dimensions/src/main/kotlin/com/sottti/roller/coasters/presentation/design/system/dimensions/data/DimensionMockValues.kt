package com.sottti.roller.coasters.presentation.design.system.dimensions.data

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Dimensions

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