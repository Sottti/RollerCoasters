package com.sottti.roller.coasters.presentation.design.system.dimensions

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Dimensions
import com.sottti.roller.coasters.presentation.design.system.dimensions.tokens.compactDimensions
import com.sottti.roller.coasters.presentation.design.system.dimensions.tokens.expandedDimensions
import com.sottti.roller.coasters.presentation.design.system.dimensions.tokens.mediumDimensions

@Composable
@ReadOnlyComposable
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
