package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.fixtureImageUrl
import com.sottti.roller.coasters.presentation.fixtures.fixtureParkName
import com.sottti.roller.coasters.presentation.fixtures.fixtureRollerCoasterName
import com.sottti.roller.coasters.presentation.fixtures.fixtureStatDetail
import com.sottti.roller.coasters.presentation.fixtures.fixtureState

internal class RollerCoasterCardLargePreviewProvider :
    PreviewParameterProvider<RollerCoasterCardLargeState> {
    override val values: Sequence<RollerCoasterCardLargeState> =
        buildList {
            foreverLoadingValues.forEach { foreverLoading ->
                add(
                    RollerCoasterCardLargeState(
                        foreverLoading = foreverLoading,
                        imageUrl = fixtureImageUrl,
                        onClick = {},
                        parkName = fixtureParkName,
                        rollerCoasterName = fixtureRollerCoasterName,
                        stat = fixtureState,
                        statDetail = fixtureStatDetail,
                    ),
                )
            }

        }.asSequence()
}

private val foreverLoadingValues = listOf(true, false)
