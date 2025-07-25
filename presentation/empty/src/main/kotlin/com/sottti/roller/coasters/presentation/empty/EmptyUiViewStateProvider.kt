package com.sottti.roller.coasters.presentation.empty

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.sottti.roller.coasters.presentation.fixtures.FixturesR

internal class EmptyUiStateProvider : PreviewParameterProvider<EmptyState?> {
    override val values: Sequence<EmptyState?> =
        sequenceOf(
            emptyDefault,
            emptyAlternative,
        )
}

private val emptyDefault = null
private val emptyAlternative = EmptyState(
    illustration = Illustrations.DragonKhanAndShambhala.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
)