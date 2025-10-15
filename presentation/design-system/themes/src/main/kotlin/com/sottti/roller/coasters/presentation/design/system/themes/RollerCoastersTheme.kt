package com.sottti.roller.coasters.presentation.design.system.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sottti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.shapes.ShapesLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.TypographyLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.typography

@Composable
public fun RollerCoastersTheme(
    colorContrast: ResolvedColorContrast = StandardContrast,
    dynamicColor: ResolvedDynamicColor = ResolvedDynamicColor(true),
    content: @Composable () -> Unit,
) {
    ColorsLocalProvider(colorContrast = colorContrast, dynamicColor = dynamicColor) {
        TypographyLocalProvider {
            OpacityLocalProvider {
                DimensionsLocalProvider {
                    ShapesLocalProvider {
                        MaterialTheme(
                            colorScheme = colors,
                            content = content,
                            typography = typography,
                        )
                    }
                }
            }
        }
    }
}
