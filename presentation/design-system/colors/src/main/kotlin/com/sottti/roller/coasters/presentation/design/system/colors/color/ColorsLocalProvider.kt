package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor

@Composable
public fun ColorsLocalProvider(
    colorContrast: ResolvedColorContrast,
    dynamicColor: ResolvedDynamicColor,
    isSystemInDarkTheme : Boolean,
    content: @Composable () -> Unit,
) {
    val colors = colors(
        colorContrast = colorContrast,
        darkTheme = isSystemInDarkTheme,
        dynamicColor = dynamicColor,
    )

    CompositionLocalProvider(LocalColors provides colors) {
        content()
    }
}
