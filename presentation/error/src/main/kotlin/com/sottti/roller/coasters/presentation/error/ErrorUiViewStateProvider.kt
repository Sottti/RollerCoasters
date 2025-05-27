package com.sottti.roller.coasters.presentation.error

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.sottti.roller.coasters.presentation.fixtures.FixturesR

internal class ErrorUiStateProvider : PreviewParameterProvider<ErrorState?> {
    override val values: Sequence<ErrorState?> =
        sequenceOf(
            defaultError,
            alternativeError,
        )
}

private val defaultError = null
private val alternativeError = ErrorState(
    illustration = Illustrations.BrokenTrack.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
    buttonText = FixturesR.fixture_button_text,
)