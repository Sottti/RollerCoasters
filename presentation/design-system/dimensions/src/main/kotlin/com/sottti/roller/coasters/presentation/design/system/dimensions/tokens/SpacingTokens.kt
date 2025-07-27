package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Spacing

internal object SpacingTokens {
    @Composable
    internal fun compact(): Spacing =
        Spacing(
            small = 2.dp,
            medium = 4.dp,
            large = 8.dp,
        )

    @Composable
    internal fun medium(): Spacing = Spacing(
        small = 2.dp,
        medium = 4.dp,
        large = 8.dp,
    )

    @Composable
    internal fun expanded(): Spacing = Spacing(
        small = 2.dp,
        medium = 4.dp,
        large = 8.dp,
    )
}
