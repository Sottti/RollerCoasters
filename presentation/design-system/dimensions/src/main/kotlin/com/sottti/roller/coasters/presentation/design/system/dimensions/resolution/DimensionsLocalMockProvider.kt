package com.sottti.roller.coasters.presentation.design.system.dimensions.resolution

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sottti.roller.coasters.presentation.design.system.dimensions.data.mockDimensions

@Composable
public fun DimensionsLocalMockProvider(
    windowWidthSizeClass: WindowWidthSizeClass,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalDimensions provides mockDimensions(windowWidthSizeClass),
    ) {
        content()
    }
}