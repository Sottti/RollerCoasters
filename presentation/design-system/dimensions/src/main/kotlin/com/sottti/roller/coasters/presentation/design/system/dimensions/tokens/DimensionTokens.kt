package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Dimensions

@Composable
@ReadOnlyComposable
internal fun dimensions() =
    Dimensions(
        component = componentTokens(),
        cornerRadii = cornerRadiiTokens(),
        spacing = spacingTokens(),
    )
