package com.sottti.roller.coasters.presentation.design.system.colors.color

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.roller.coasters.presentation.design.system.colors.mapper.darkColorScheme
import com.sottti.roller.coasters.presentation.design.system.colors.mapper.dynamicColorScheme
import com.sottti.roller.coasters.presentation.design.system.colors.mapper.lightColorScheme

@Composable
@ReadOnlyComposable
public fun colors(
    colorContrast: ColorContrast,
    useDarkTheme: Boolean,
    useDynamicColor: Boolean = false,
): ColorScheme = when {
    useDynamicColor && isDynamicColorAvailable() -> dynamicColorScheme(useDarkTheme)
    useDarkTheme -> colorContrast.darkColorScheme()
    else -> colorContrast.lightColorScheme()
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
private fun isDynamicColorAvailable(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
