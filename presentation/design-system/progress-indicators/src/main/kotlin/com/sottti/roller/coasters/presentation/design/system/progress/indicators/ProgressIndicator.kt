package com.sottti.roller.coasters.presentation.design.system.progress.indicators

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme


@Composable
public fun ProgressIndicator(
    modifier: Modifier = Modifier,
    size: ProgressIndicatorSize = ProgressIndicatorSize.Medium,
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(progressIndicatorSize(size))
        )
    }
}

@Composable
private fun progressIndicatorSize(size: ProgressIndicatorSize): Dp =
    when (size) {
        ProgressIndicatorSize.Small -> dimensions.components.progressIndicator.small
        ProgressIndicatorSize.Medium -> dimensions.components.progressIndicator.medium
        ProgressIndicatorSize.Large -> dimensions.components.progressIndicator.large
    }

@Composable
@PreviewLightDark
private fun ProgressIndicatorPreview() {
    RollerCoastersPreviewTheme {
        ProgressIndicator()
    }
}

@Composable
@PreviewLightDark
private fun ProgressIndicatorWithPaddingPreview() {
    RollerCoastersPreviewTheme {
        ProgressIndicator(
            modifier = Modifier
                .width(360.dp)
                .height(640.dp)
        )
    }
}