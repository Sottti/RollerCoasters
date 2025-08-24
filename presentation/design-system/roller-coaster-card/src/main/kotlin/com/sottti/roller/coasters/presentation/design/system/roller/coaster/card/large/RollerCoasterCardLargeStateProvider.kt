package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.large

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.RollerCoasterCardStat
import com.sottti.roller.coasters.presentation.fixtures.fixtureImageUrl
import com.sottti.roller.coasters.presentation.fixtures.fixtureParkName
import com.sottti.roller.coasters.presentation.fixtures.fixtureRollerCoasterName
import com.sottti.roller.coasters.presentation.fixtures.fixtureStat
import com.sottti.roller.coasters.presentation.fixtures.fixtureStatDetail

internal class RollerCoasterCardLargeStateProvider :
    PreviewParameterProvider<RollerCoasterCardLargeState> {
    override val values: Sequence<RollerCoasterCardLargeState> =
        sequence {
            foreverLoadingValues.forEach { foreverLoading ->
                yield(
                    RollerCoasterCardLargeState(
                        foreverLoading = foreverLoading,
                        imageUrl = fixtureImageUrl,
                        onClick = {},
                        parkName = fixtureParkName,
                        rollerCoasterName = fixtureRollerCoasterName,
                        stat = stat,
                    ),
                )
            }

        }
}

private val foreverLoadingValues = listOf(true, false)
private val stat = RollerCoasterCardStat(value = fixtureStat, detail = fixtureStatDetail)