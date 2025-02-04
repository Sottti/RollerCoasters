package com.sottti.roller.coasters.presentation.design.system.colors.color

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures

@Composable
public fun colors(
    colorContrast: AppColorContrast,
    darkTheme: Boolean,
    dynamicColor: Boolean,
    sdkFeatures: SdkFeatures?,
): ColorScheme = when {
    sdkFeatures != null &&
            sdkFeatures.dynamicColorAvailable() &&
            dynamicColor -> dynamicColorScheme(darkTheme)

    darkTheme -> darkColorScheme(colorContrast)
    else -> lightColorScheme(colorContrast)
}

@Composable
@RequiresApi(Build.VERSION_CODES.S)
private fun dynamicColorScheme(darkTheme: Boolean): ColorScheme =
    when {
        darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        else -> dynamicLightColorScheme(LocalContext.current)
    }

@Composable
private fun darkColorScheme(colorContrast: AppColorContrast): ColorScheme =
    when (colorContrast) {
        AppColorContrast.HighContrast -> ColorSchemes.Dark.highContrast
        AppColorContrast.MediumContrast -> ColorSchemes.Dark.mediumContrast
        AppColorContrast.StandardContrast -> ColorSchemes.Dark.standardContrast
    }

@Composable
private fun lightColorScheme(colorContrast: AppColorContrast): ColorScheme =
    when (colorContrast) {
        AppColorContrast.HighContrast -> ColorSchemes.Light.highContrast
        AppColorContrast.MediumContrast -> ColorSchemes.Light.mediumContrast
        AppColorContrast.StandardContrast -> ColorSchemes.Light.standardContrast
    }