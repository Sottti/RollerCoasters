package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.ProgressIndicatorDimensions

internal object ProgressIndicatorTokens {
    @Composable
    internal fun compat(): ProgressIndicatorDimensions =
        ProgressIndicatorDimensions(
            small = 24.dp,
            medium = 36.dp,
            large = 48.dp,
        )

    @Composable
    internal fun medium(): ProgressIndicatorDimensions =
        ProgressIndicatorDimensions(
            small = 24.dp,
            medium = 36.dp,
            large = 48.dp,
        )


    @Composable
    internal fun expanded(): ProgressIndicatorDimensions =
        ProgressIndicatorDimensions(
            small = 24.dp,
            medium = 36.dp,
            large = 48.dp,
        )
}
