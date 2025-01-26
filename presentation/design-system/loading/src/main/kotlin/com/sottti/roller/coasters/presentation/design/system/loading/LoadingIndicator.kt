package com.sottti.roller.coasters.presentation.design.system.loading

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions


@Composable
public fun LoadingIndicator(
    size: LoadingIndicatorSize = LoadingIndicatorSize.Medium
) {
    CircularProgressIndicator(modifier = Modifier.size(loadingIndicatorSize(size)))
}

@Composable
private fun loadingIndicatorSize(size: LoadingIndicatorSize): Dp =
    when (size) {
        LoadingIndicatorSize.Small -> dimensions.components.progressIndicator.small
        LoadingIndicatorSize.Medium -> dimensions.components.progressIndicator.medium
        LoadingIndicatorSize.Large -> dimensions.components.progressIndicator.large
    }