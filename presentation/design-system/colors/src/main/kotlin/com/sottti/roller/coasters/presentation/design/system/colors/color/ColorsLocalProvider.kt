package com.sottti.roller.coasters.presentation.design.system.colors.color

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
public fun ColorsLocalProvider(
    colors: ColorScheme,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalColors provides colors) {
        content()
    }
}
