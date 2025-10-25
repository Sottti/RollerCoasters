package com.sotti.roller.coasters.presentation.image.loading

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sotti.roller.coasters.presentation.fixtures.fixtureContentDescription
import com.sotti.roller.coasters.presentation.fixtures.fixtureImageUrl

internal class ImageStateProvider :
    PreviewParameterProvider<ImageState> {
    override val values: Sequence<ImageState> =
        sequence {
            yield(
                ImageState(
                    contentDescription = fixtureContentDescription,
                    foreverLoading = true,
                    imageUrl = fixtureImageUrl,
                    roundedCorners = false,
                ),
            )
            roundedCornersValues.forEach { roundedCorners ->
                yield(
                    ImageState(
                        contentDescription = fixtureContentDescription,
                        foreverLoading = false,
                        imageUrl = fixtureImageUrl,
                        roundedCorners = roundedCorners,
                    )
                )
            }
        }
}

private val roundedCornersValues = listOf(false, true)
