package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.utils.device.sdk.isAtLeastSdk31

@Composable
public fun colors(
    colorContrast: ColorContrastTheme,
    darkTheme: Boolean,
    dynamicColor: Boolean,
): ColorScheme = when {
    dynamicColor && isAtLeastSdk31() -> {
        val context = LocalContext.current
        when {
            darkTheme -> dynamicDarkColorScheme(context)
            else -> dynamicLightColorScheme(context)
        }
    }

    darkTheme -> when (colorContrast) {
        ColorContrastTheme.HighContrast -> ColorSchemes.Dark.highContrast
        ColorContrastTheme.MediumContrast -> ColorSchemes.Dark.mediumContrast
        ColorContrastTheme.StandardContrast -> ColorSchemes.Dark.standardContrast
    }

    else -> when (colorContrast) {
        ColorContrastTheme.HighContrast -> ColorSchemes.Light.highContrast
        ColorContrastTheme.MediumContrast -> ColorSchemes.Light.mediumContrast
        ColorContrastTheme.StandardContrast -> ColorSchemes.Light.standardContrast
    }
}