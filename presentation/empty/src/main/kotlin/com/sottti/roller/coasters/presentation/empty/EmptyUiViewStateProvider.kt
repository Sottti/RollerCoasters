package com.sottti.roller.coasters.presentation.empty

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.sottti.roller.coasters.presentation.fixtures.FixturesR

internal class EmptyUiViewStateProvider : PreviewParameterProvider<EmptyViewState?> {
    override val values: Sequence<EmptyViewState?> =
        sequenceOf(
            emptyDefault,
            emptyAlternative,
        )
}

private val emptyDefault = null
private val emptyAlternative = EmptyViewState(
    illustration = Illustrations.EmptyTrack.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
)