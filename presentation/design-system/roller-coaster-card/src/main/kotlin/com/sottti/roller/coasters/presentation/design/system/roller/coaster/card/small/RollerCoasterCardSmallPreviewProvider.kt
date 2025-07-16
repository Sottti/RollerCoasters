package com.sottti.roller.coasters.presentation.design.system.roller.coaster.card.small

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.fixtureImageUrl
import com.sottti.roller.coasters.presentation.fixtures.fixtureParkName
import com.sottti.roller.coasters.presentation.fixtures.fixtureRollerCoasterName

internal class RollerCoasterCardSmallPreviewProvider :
    PreviewParameterProvider<RollerCoasterCardSmallState> {
    override val values: Sequence<RollerCoasterCardSmallState> =
        buildList {
            foreverLoadingValues.forEach { foreverLoading ->
                imageUrlValues.forEach { imageUrl ->
                    add(
                        RollerCoasterCardSmallState(
                            foreverLoading = foreverLoading,
                            imageUrl = imageUrl,
                            onClick = {},
                            parkName = fixtureParkName,
                            rollerCoasterName = fixtureRollerCoasterName,
                        ),
                    )
                }
            }
        }.asSequence()
}

private val foreverLoadingValues = listOf(true, false)
private val imageUrlValues = listOf(fixtureImageUrl, null)