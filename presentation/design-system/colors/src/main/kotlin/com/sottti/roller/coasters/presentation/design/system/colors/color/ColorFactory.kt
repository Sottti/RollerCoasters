package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.ResolvedDynamicColor
import com.sottti.roller.coasters.presentation.design.system.colors.mappers.darkColorScheme
import com.sottti.roller.coasters.presentation.design.system.colors.mappers.lightColorScheme

@Composable
public fun colors(
    colorContrast: ResolvedColorContrast,
    darkTheme: Boolean,
    dynamicColor: ResolvedDynamicColor,
): ColorScheme = when {
    dynamicColor.enabled -> dynamicColorScheme(darkTheme)
    darkTheme -> colorContrast.darkColorScheme()
    else -> colorContrast.lightColorScheme()
}

@Composable
private fun dynamicColorScheme(darkTheme: Boolean): ColorScheme =
    when {
        darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        else -> dynamicLightColorScheme(LocalContext.current)
    }