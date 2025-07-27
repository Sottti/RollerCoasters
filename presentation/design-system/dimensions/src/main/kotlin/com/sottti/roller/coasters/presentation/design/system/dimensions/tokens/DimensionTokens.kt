package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Dimensions

@Composable
internal fun compactDimensions() =
    Dimensions(
        components = DesignComponentTokens.compact(),
        padding = PaddingTokens.compact(),
        spacing = SpacingTokens.compact(),
    )

@Composable
internal fun mediumDimensions() =
    Dimensions(
        components = DesignComponentTokens.medium(),
        padding = PaddingTokens.medium(),
        spacing = SpacingTokens.medium(),
    )

@Composable
internal fun expandedDimensions() =
    Dimensions(
        components = DesignComponentTokens.expanded(),
        padding = PaddingTokens.expanded(),
        spacing = SpacingTokens.expanded(),
    )
