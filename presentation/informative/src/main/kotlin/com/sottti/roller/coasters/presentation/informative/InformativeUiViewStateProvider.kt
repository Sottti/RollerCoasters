package com.sottti.roller.coasters.presentation.informative

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.roller.coasters.presentation.design.system.illustrations.data.Illustrations

internal class InformativeUiViewStateProvider : PreviewParameterProvider<InformativeViewState> {
    override val values: Sequence<InformativeViewState> =
        sequenceOf(
            informativeError,
        )
}

private val informativeError = InformativeViewState(
    illustration = Illustrations.BrokenTrack.state,
    primaryText = R.string.primary_text,
    secondaryText = R.string.secondary_text,
    buttonText = R.string.button_text,
)