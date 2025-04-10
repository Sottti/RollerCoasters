package com.sottti.roller.coasters.presentation.informative

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.sottti.roller.coasters.presentation.fixtures.FixturesR

internal class InformativeUiViewStateProvider : PreviewParameterProvider<InformativeViewState> {
    override val values: Sequence<InformativeViewState> =
        sequenceOf(
            informativeError,
        )
}

private val informativeError = InformativeViewState(
    illustration = Illustrations.BrokenTrack.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
    buttonText = FixturesR.fixture_button_text,
)