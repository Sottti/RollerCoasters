package com.sottti.roller.coasters.presentation.design.system.colors.mapper

import androidx.compose.material3.ColorScheme
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.color.ColorSchemes


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
