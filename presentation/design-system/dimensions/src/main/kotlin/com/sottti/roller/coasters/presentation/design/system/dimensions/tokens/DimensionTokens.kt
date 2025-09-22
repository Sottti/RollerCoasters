package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Dimensions

@Composable
internal fun compactDimensions() =
    Dimensions(
        component = DesignComponentTokens.compact(),
        cornerRadii = CornerRadiiTokens.compact(),
        spacing = SpacingTokens.compact(),
    )

@Composable
internal fun mediumDimensions() =
    Dimensions(
        component = DesignComponentTokens.medium(),
        cornerRadii = CornerRadiiTokens.medium(),
        spacing = SpacingTokens.medium(),
    )

@Composable
internal fun expandedDimensions() =
    Dimensions(
        component = DesignComponentTokens.expanded(),
        cornerRadii = CornerRadiiTokens.expanded(),
        spacing = SpacingTokens.expanded(),
    )
