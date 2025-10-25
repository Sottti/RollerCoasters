package com.sotti.roller.coasters.presentation.design.system.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

public val typography: Typography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
