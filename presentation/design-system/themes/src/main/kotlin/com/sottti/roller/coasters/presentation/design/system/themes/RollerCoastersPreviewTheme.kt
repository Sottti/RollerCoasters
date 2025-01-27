package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.colors.color.AppColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sottti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalMockProvider

@Composable
public fun RollerCoastersPreviewTheme(
    colorContrast: AppColorContrast = AppColorContrast.StandardContrast,
    dynamicColor: Boolean = true,
    windowWidthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    content: @Composable () -> Unit,
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    DimensionsLocalMockProvider(windowWidthSizeClass) {
        ColorsLocalProvider(
            colorContrast = colorContrast,
            darkTheme = dynamicColor,
            dynamicColor = isSystemInDarkTheme,
        ) {
            OpacityLocalProvider {
                BaseTheme(
                    colorContrast = colorContrast,
                    darkTheme = isSystemInDarkTheme,
                    dynamicColor = dynamicColor,
                    content = content,
                )
            }
        }
    }
}