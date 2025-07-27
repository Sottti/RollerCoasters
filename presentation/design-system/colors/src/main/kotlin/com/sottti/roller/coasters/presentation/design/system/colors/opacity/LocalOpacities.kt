package com.sottti.roller.coasters.presentation.design.system.colors.opacity

import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalOpacities = staticCompositionLocalOf<Opacities> {
    error("No opacities provided. Make sure to wrap your composables in a custom theme.")
}
