package com.sottti.roller.coasters.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import com.sottti.roller.coasters.presentation.design.system.dimensions.model.ComponentDimensions

internal object DesignComponentTokens {
    @Composable
    internal fun compact() =
        ComponentDimensions(
            progressIndicator = ProgressIndicatorTokens.compat(),
        )

    @Composable
    internal fun medium() =
        ComponentDimensions(
            progressIndicator = ProgressIndicatorTokens.medium(),
        )

    @Composable
    internal fun expanded() =
        ComponentDimensions(
            progressIndicator = ProgressIndicatorTokens.expanded(),
        )
}
