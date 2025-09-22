package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.CornerRadii

internal object CornerRadiiTokens {
    @Composable
    internal fun compact(): CornerRadii =
        CornerRadii(
            extraSmall = MaterialTheme.shapes.extraSmall,
            small = MaterialTheme.shapes.small,
            medium = MaterialTheme.shapes.medium,
            large = MaterialTheme.shapes.large,
            extraLarge = MaterialTheme.shapes.extraLarge,
        )

    @Composable
    internal fun medium(): CornerRadii =
        CornerRadii(
            extraSmall = MaterialTheme.shapes.extraSmall,
            small = MaterialTheme.shapes.small,
            medium = MaterialTheme.shapes.medium,
            large = MaterialTheme.shapes.large,
            extraLarge = MaterialTheme.shapes.extraLarge,
        )

    @Composable
    internal fun expanded(): CornerRadii =
        CornerRadii(
            extraSmall = MaterialTheme.shapes.extraSmall,
            small = MaterialTheme.shapes.small,
            medium = MaterialTheme.shapes.medium,
            large = MaterialTheme.shapes.large,
            extraLarge = MaterialTheme.shapes.extraLarge,
        )
}
