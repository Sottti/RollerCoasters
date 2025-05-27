package com.sottti.roller.coasters.presentation.informative

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.sottti.roller.coasters.presentation.fixtures.FixturesR

internal class InformativeUiStateProvider : PreviewParameterProvider<InformativeState> {
    override val values: Sequence<InformativeState> =
        sequenceOf(
            informativeWithButton,
            informativeWithoutButton,
        )
}

private val informativeWithButton = InformativeState(
    illustration = Illustrations.BrokenTrack.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
    buttonText = FixturesR.fixture_button_text,
)

private val informativeWithoutButton = InformativeState(
    illustration = Illustrations.BrokenTrack.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
    buttonText = null,
)