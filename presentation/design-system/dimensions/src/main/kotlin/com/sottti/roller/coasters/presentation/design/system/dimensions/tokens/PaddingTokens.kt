package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.Padding

internal object PaddingTokens {
    @Composable
    internal fun compact(): Padding =
        Padding(
            zero = 0.dp,
            extraSmall = 2.dp,
            small = 4.dp,
            smallMedium = 8.dp,
            medium = 16.dp,
            mediumLarge = 24.dp,
            large = 32.dp,
            extraLarge = 64.dp,
        )

    @Composable
    internal fun medium(): Padding =
        Padding(
            zero = 0.dp,
            extraSmall = 2.dp,
            small = 4.dp,
            smallMedium = 8.dp,
            medium = 16.dp,
            mediumLarge = 24.dp,
            large = 32.dp,
            extraLarge = 64.dp,
        )

    @Composable
    internal fun expanded(): Padding =
        Padding(
            zero = 0.dp,
            extraSmall = 2.dp,
            small = 4.dp,
            smallMedium = 8.dp,
            medium = 16.dp,
            mediumLarge = 24.dp,
            large = 32.dp,
            extraLarge = 64.dp,
        )
}