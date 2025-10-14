package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sottti.roller.coasters.presentation.design.system.shapes.ShapesLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.TypographyLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.typography

@Composable
internal fun RollerCoastersBaseTheme(
    colorScheme: ColorScheme,
    content: @Composable () -> Unit,
) {
    OpacityLocalProvider {
        ShapesLocalProvider {
            TypographyLocalProvider {
                ColorsLocalProvider(colorScheme = colorScheme) {
                    MaterialTheme(
                        colorScheme = colorScheme,
                        content = content,
                        typography = typography,
                    )
                }
            }
        }
    }
}
