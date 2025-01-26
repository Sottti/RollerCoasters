package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.utils.device.ColorContrast
import com.sottti.roller.coasters.utils.device.isAtLeastSdk31

@Composable
public fun colors(
    colorContrast: ColorContrast,
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
        ColorContrast.LowContrast -> ColorSchemes.Dark.standardContrast
        ColorContrast.StandardContrast -> ColorSchemes.Dark.standardContrast
        ColorContrast.MediumContrast -> ColorSchemes.Dark.mediumContrast
        ColorContrast.HighContrast -> ColorSchemes.Dark.highContrast
    }

    else -> when (colorContrast) {
        ColorContrast.LowContrast -> ColorSchemes.Light.standardContrast
        ColorContrast.StandardContrast -> ColorSchemes.Light.standardContrast
        ColorContrast.MediumContrast -> ColorSchemes.Light.mediumContrast
        ColorContrast.HighContrast -> ColorSchemes.Light.highContrast
    }
}