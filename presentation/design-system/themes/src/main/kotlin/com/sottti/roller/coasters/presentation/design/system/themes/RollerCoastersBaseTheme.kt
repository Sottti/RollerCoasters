package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.typography

@Composable
internal fun BaseTheme(
    colors: ColorScheme,
    content: @Composable () -> Unit,
) {
    OpacityLocalProvider {
        ColorsLocalProvider(colors = colors) {
            MaterialTheme(
                colorScheme = colors,
                shapes = MaterialTheme.shapes,
                typography = typography,
                content = content,
            )
        }
    }
}
