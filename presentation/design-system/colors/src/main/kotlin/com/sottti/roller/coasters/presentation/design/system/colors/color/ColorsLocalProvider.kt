package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.domain.settings.model.theme.ResolvedTheme

@Composable
public fun ColorsLocalProvider(
    colorContrast: ResolvedColorContrast,
    dynamicColor: ResolvedDynamicColor,
    theme: ResolvedTheme,
    content: @Composable () -> Unit,
) {
    val colors = colors(
        colorContrast = colorContrast,
        dynamicColor = dynamicColor,
        theme = theme,
    )

    CompositionLocalProvider(LocalColors provides colors) {
        content()
    }
}
