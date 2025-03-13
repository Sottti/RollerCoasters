package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class RollerCoasterCardPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(false, true)
}