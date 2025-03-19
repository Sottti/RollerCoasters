package com.sottti.roller.coasters.presentation.design.system.colors.color

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.domain.settings.model.colorContrast.ResolvedColorContrast
import com.sottti.roller.coasters.presentation.design.system.colors.mappers.darkColorScheme
import com.sottti.roller.coasters.presentation.design.system.colors.mappers.lightColorScheme
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures

@Composable
public fun colors(
    colorContrast: ResolvedColorContrast,
    darkTheme: Boolean,
    dynamicColor: Boolean,
    sdkFeatures: SdkFeatures?,
): ColorScheme = when {
    sdkFeatures != null &&
            sdkFeatures.dynamicColorAvailable() &&
            dynamicColor -> dynamicColorScheme(darkTheme)

    darkTheme -> colorContrast.darkColorScheme()
    else -> colorContrast.lightColorScheme()
}

@Composable
@RequiresApi(Build.VERSION_CODES.S)
private fun dynamicColorScheme(darkTheme: Boolean): ColorScheme =
    when {
        darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        else -> dynamicLightColorScheme(LocalContext.current)
    }