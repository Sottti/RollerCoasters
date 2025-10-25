package com.sotti.roller.coasters.presentation.design.system.themes

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sotti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sotti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast.StandardContrast
import com.sotti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sotti.roller.coasters.domain.settings.model.theme.ResolvedTheme
import com.sotti.roller.coasters.domain.settings.model.theme.ResolvedTheme.DarkResolvedTheme
import com.sotti.roller.coasters.domain.settings.model.theme.ResolvedTheme.LightResolvedTheme
import com.sotti.roller.coasters.presentation.design.system.colors.color.ColorsLocalProvider
import com.sotti.roller.coasters.presentation.design.system.colors.color.colors
import com.sotti.roller.coasters.presentation.design.system.colors.opacity.OpacityLocalProvider
import com.sotti.roller.coasters.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sotti.roller.coasters.presentation.design.system.shapes.ShapesLocalProvider
import com.sotti.roller.coasters.presentation.design.system.typography.TypographyLocalProvider
import com.sotti.roller.coasters.presentation.design.system.typography.typography

@Composable
public fun RollerCoastersTheme(
    colorContrast: ResolvedColorContrast = StandardContrast,
    dynamicColor: ResolvedDynamicColor = ResolvedDynamicColor(true),
    theme: ResolvedTheme = if (isSystemInDarkTheme()) DarkResolvedTheme else LightResolvedTheme,
    themeVariant: RollerCoastersThemeVariant = RollerCoastersThemeVariant.Default,
    content: @Composable () -> Unit,
) {
    when (themeVariant) {
        RollerCoastersThemeVariant.Default ->
            DefaultThemeVariant(
                colorContrast = colorContrast,
                dynamicColor = dynamicColor,
                theme = theme,
                content = content
            )
    }
}

@Composable
private fun DefaultThemeVariant(
    colorContrast: ResolvedColorContrast,
    dynamicColor: ResolvedDynamicColor,
    theme: ResolvedTheme,
    content: @Composable () -> Unit,
) {
    ColorsLocalProvider(
        colorContrast = colorContrast,
        dynamicColor = dynamicColor,
        theme = theme,
    ) {
        TypographyLocalProvider {
            OpacityLocalProvider {
                DimensionsLocalProvider {
                    ShapesLocalProvider {
                        UpdateSystemBars(theme)
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
    theme: ResolvedTheme,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val isLightTheme = theme == LightResolvedTheme
            WindowCompat
                .getInsetsController(window, view)
                .apply {
                    isAppearanceLightStatusBars = isLightTheme
                    isAppearanceLightNavigationBars = isLightTheme
                }
        }
    }
}
