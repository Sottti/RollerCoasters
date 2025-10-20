package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.presentation.design.system.colors.mapper.darkColorScheme
import com.sottti.roller.coasters.presentation.design.system.colors.mapper.dynamicColorScheme
import com.sottti.roller.coasters.presentation.design.system.colors.mapper.lightColorScheme

@Composable
@ReadOnlyComposable
public fun colors(
    colorContrast: ResolvedColorContrast,
    dynamicColor: ResolvedDynamicColor,
    isSystemInDarkTheme: Boolean,
): ColorScheme = when {
    dynamicColor.enabled -> dynamicColorScheme(isSystemInDarkTheme)
    isSystemInDarkTheme -> colorContrast.darkColorScheme()
    else -> colorContrast.lightColorScheme()
}
