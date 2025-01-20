package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.colors.schemes.colorScheme
import com.sottti.roller.coasters.presentation.design.system.typography.typography

@Composable
internal fun BaseTheme(
    dynamicColor: Boolean,
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorScheme(dynamicColor = dynamicColor, darkTheme = darkTheme),
        shapes = MaterialTheme.shapes,
        typography = typography,
        content = content,
    )
}