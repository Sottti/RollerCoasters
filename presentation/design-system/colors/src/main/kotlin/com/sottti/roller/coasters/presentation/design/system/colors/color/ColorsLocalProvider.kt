package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
public fun ColorsLocalProvider(
    colorContrast: AppColorContrast,
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors(
            dynamicColor = dynamicColor,
            darkTheme = darkTheme,
            colorContrast = colorContrast,
        )
    ) {
        content()
    }
}