package com.sottti.roller.coasters.presentation.design.system.typography

import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalTypography = staticCompositionLocalOf<Typography> {
    error("No typography provided. Make sure to wrap your composables in a custom theme.")
}
