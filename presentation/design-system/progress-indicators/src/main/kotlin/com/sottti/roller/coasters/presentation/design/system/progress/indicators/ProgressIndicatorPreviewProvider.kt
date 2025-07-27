package com.sottti.roller.coasters.presentation.design.system.progress.indicators

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

internal class ProgressIndicatorPreviewProvider : PreviewParameterProvider<Modifier> {
    override val values = sequenceOf(
        Modifier,
        Modifier
            .width(360.dp)
            .height(640.dp),
    )
}
