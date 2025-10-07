package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.ComponentDimensions

internal object DesignComponentTokens {
    @Composable
    @ReadOnlyComposable
    internal fun compact() =
        ComponentDimensions(
            progressIndicator = ProgressIndicatorTokens.compat(),
        )

    @Composable
    @ReadOnlyComposable
    internal fun medium() =
        ComponentDimensions(
            progressIndicator = ProgressIndicatorTokens.medium(),
        )

    @Composable
    @ReadOnlyComposable
    internal fun expanded() =
        ComponentDimensions(
            progressIndicator = ProgressIndicatorTokens.expanded(),
        )
}
