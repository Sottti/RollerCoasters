package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dimensions.resolution.DimensionsLocalProvider

@Composable
public fun RollerCoastersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    DimensionsLocalProvider {
        BaseTheme(
            dynamicColor = dynamicColor,
            darkTheme = darkTheme,
            content = content,
        )
    }
}