package com.sottti.roller.coasters.presentation.design.system.dimensions

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

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