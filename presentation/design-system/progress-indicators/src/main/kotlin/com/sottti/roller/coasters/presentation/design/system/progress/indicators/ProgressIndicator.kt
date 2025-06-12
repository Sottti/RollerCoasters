package com.sottti.roller.coasters.presentation.design.system.progress.indicators

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview


@Composable
public fun ProgressIndicator(
    modifier: Modifier = Modifier,
    size: ProgressIndicatorSize = ProgressIndicatorSize.Medium,
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            strokeWidth = strokeWidth(size),
            modifier = Modifier
                .align(Alignment.Center)
                .size(progressIndicatorSize(size))
        )
    }
}

@Composable
private fun strokeWidth(
    size: ProgressIndicatorSize,
): Dp = when (size) {
    ProgressIndicatorSize.Small -> ProgressIndicatorDefaults.CircularStrokeWidth * 0.75f
    ProgressIndicatorSize.Medium -> ProgressIndicatorDefaults.CircularStrokeWidth
    ProgressIndicatorSize.Large -> ProgressIndicatorDefaults.CircularStrokeWidth
}

@Composable
private fun progressIndicatorSize(size: ProgressIndicatorSize): Dp =
    when (size) {
        ProgressIndicatorSize.Small -> dimensions.components.progressIndicator.small
        ProgressIndicatorSize.Medium -> dimensions.components.progressIndicator.medium
        ProgressIndicatorSize.Large -> dimensions.components.progressIndicator.large
    }

@Composable
@RollerCoastersPreview
internal fun ProgressIndicatorPreview(
    @PreviewParameter(ProgressIndicatorPreviewProvider::class)
    modifier: Modifier,
) {
    RollerCoastersPreviewTheme {
        ProgressIndicator(modifier)
    }
}