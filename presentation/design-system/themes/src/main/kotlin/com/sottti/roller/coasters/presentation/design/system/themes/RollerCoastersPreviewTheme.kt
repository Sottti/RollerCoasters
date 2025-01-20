package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dimensions.resolution.DimensionsLocalMockProvider

@Composable
public fun RollerCoastersPreviewTheme(
    dynamicColor: Boolean = true,
    windowWidthSizeClass: WindowWidthSizeClass,
    content: @Composable () -> Unit,
) {
    DimensionsLocalMockProvider(windowWidthSizeClass) {
        BaseTheme(
            dynamicColor = dynamicColor,
            darkTheme = isSystemInDarkTheme(),
            content = content,
        )
    }
}