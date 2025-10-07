package com.sottti.roller.coasters.presentation.design.system.colors.opacity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

public val opacities: Opacities
    @Composable
    @ReadOnlyComposable
    get() = LocalOpacities.current
