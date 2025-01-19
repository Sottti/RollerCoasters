package com.sottti.roller.coasters.presentation.design.system.dimensions.resolution

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sottti.roller.coasters.presentation.design.system.dimensions.data.dimensions

@Composable
fun DimensionsLocalProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalDimensions provides dimensions()) {
        content()
    }
}