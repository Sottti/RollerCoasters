package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.sottti.roller.coasters.utils.device.isAtLeastSdk31

@Composable
public fun colors(
    dynamicColor: Boolean,
    darkTheme: Boolean,
): ColorScheme = when {
    dynamicColor && isAtLeastSdk31() -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> darkScheme
    else -> lightScheme
}