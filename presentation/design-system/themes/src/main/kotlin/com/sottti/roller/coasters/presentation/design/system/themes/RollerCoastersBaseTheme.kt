package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.colors.color.AppColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.typography.typography

@Composable
internal fun BaseTheme(
    colorContrast: AppColorContrast,
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colors(
            colorContrast = colorContrast,
            dynamicColor = dynamicColor,
            darkTheme = darkTheme,
        ),
        shapes = MaterialTheme.shapes,
        typography = typography,
        content = content,
    )
}