package com.sottti.roller.coasters.presentation.design.system.themes

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sottti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sottti.roller.coasters.presentation.design.system.shapes.ShapesLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.TypographyLocalProvider
import com.sottti.roller.coasters.presentation.design.system.typography.typography

@Composable
public fun RollerCoastersTheme(
    colorContrast: ColorContrast = ColorContrast.Standard,
    themeVariant: RollerCoastersThemeVariant = RollerCoastersThemeVariant.Default,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    when (themeVariant) {
        RollerCoastersThemeVariant.Default ->
            DefaultThemeVariant(
                colorContrast = colorContrast,
                content = content,
                useDarkTheme = useDarkTheme,
                useDynamicColor = useDynamicColor,
            )
    }
}

@Composable
private fun DefaultThemeVariant(
    colorContrast: ColorContrast,
    useDarkTheme: Boolean,
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    ColorsLocalProvider(
        colorContrast = colorContrast,
        useDarkTheme = useDarkTheme,
        useDynamicColor = useDynamicColor,
    ) {
        TypographyLocalProvider {
            OpacityLocalProvider {
                DimensionsLocalProvider {
                    ShapesLocalProvider {
                        UpdateSystemBars(useDarkTheme)
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

@Composable
private fun UpdateSystemBars(
    useDarkTheme: Boolean,
) {
    val view = LocalView.current
    val context = view.context

    if (!view.isInEditMode && context is Activity) {
        SideEffect {
            val window = context.window
            val isLightTheme = !useDarkTheme
            WindowCompat
                .getInsetsController(window, view)
                .apply {
                    isAppearanceLightStatusBars = isLightTheme
                    isAppearanceLightNavigationBars = isLightTheme
                }
        }
    }
}
