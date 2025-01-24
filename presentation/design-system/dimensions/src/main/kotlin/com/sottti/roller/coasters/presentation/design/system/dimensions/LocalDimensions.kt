package com.sottti.roller.coasters.presentation.design.system.dimensions

import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalDimensions = staticCompositionLocalOf<Dimensions> {
    error("No dimensions provided. Make sure to wrap your composables in a custom theme.")
}