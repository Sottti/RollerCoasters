package com.sottti.roller.coasters.presentation.design.system.colors.mapper

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorSchemes

@Composable
@ReadOnlyComposable
@RequiresApi(api = 31)
internal fun dynamicColorScheme(darkTheme: Boolean): ColorScheme =
    when {
        darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        else -> dynamicLightColorScheme(LocalContext.current)
    }


internal fun ResolvedColorContrast.darkColorScheme(): ColorScheme =
    when (this) {
        ResolvedColorContrast.HighContrast -> ColorSchemes.Dark.highContrast
        ResolvedColorContrast.MediumContrast -> ColorSchemes.Dark.mediumContrast
        ResolvedColorContrast.StandardContrast -> ColorSchemes.Dark.standardContrast
        ResolvedColorContrast.LowContrast -> ColorSchemes.Dark.standardContrast
    }

internal fun ResolvedColorContrast.lightColorScheme(): ColorScheme =
    when (this) {
        ResolvedColorContrast.HighContrast -> ColorSchemes.Light.highContrast
        ResolvedColorContrast.MediumContrast -> ColorSchemes.Light.mediumContrast
        ResolvedColorContrast.StandardContrast -> ColorSchemes.Light.standardContrast
        ResolvedColorContrast.LowContrast -> ColorSchemes.Light.standardContrast
    }
