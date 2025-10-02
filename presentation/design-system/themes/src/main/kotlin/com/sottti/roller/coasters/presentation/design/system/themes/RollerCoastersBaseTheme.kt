package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sottti.roller.coasters.presentation.design.system.shapes.ShapesLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.TypographyLocalProvider

@Composable
internal fun RollerCoastersBaseTheme(
    colors: ColorScheme,
    content: @Composable () -> Unit,
) {
    OpacityLocalProvider {
        ShapesLocalProvider {
            TypographyLocalProvider {
                ColorsLocalProvider(colors = colors) {
                    MaterialTheme(
                        colorScheme = colors,
                        content = content,
                    )
                }
            }
        }
    }
}
