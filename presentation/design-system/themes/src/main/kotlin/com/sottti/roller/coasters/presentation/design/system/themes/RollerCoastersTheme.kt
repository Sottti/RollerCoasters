package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.colors.schemes.colorScheme
import com.sottti.roller.coasters.presentation.design.system.dimensions.resolution.DimensionsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.typography

@Composable
fun RollerCoastersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {

    DimensionsLocalProvider {
        MaterialTheme(
            colorScheme = colorScheme(dynamicColor = dynamicColor, darkTheme = darkTheme),
            shapes = MaterialTheme.shapes,
            typography = typography,
            content = content,
        )
    }
}