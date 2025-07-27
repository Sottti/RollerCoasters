package com.sottti.roller.coasters.presentation.design.system.colors.opacity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
public fun OpacityLocalProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalOpacities provides opacities()
    ) {
        content()
    }
}
