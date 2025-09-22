package com.sottti.roller.coasters.presentation.design.system.shapes

import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalShapes = staticCompositionLocalOf<Shapes> {
    error("No shapes provided. Make sure to wrap your composables in a custom theme.")
}
