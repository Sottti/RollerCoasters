package com.sottti.roller.coasters.presentation.design.system.dimensions.resolution

import androidx.compose.runtime.staticCompositionLocalOf
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Dimensions

internal val LocalDimensions = staticCompositionLocalOf<Dimensions> {
    error("No Dimensions provided. Make sure to wrap your composables in a custom theme.")
}