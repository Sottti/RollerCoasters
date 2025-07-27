package com.sottti.roller.coasters.presentation.image.loading

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.fixtureContentDescription
import com.sottti.roller.coasters.presentation.fixtures.fixtureImageUrl

internal class ImageStateProvider :
    PreviewParameterProvider<ImageState> {
    override val values: Sequence<ImageState> =
        buildList {
            add(
                ImageState(
                    contentDescription = fixtureContentDescription,
                    foreverLoading = true,
                    imageUrl = fixtureImageUrl,
                    roundedCorners = false,
                ),
            )
            roundedCornersValues.forEach { roundedCorners ->
                add(
                    ImageState(
                        contentDescription = fixtureContentDescription,
                        foreverLoading = false,
                        imageUrl = fixtureImageUrl,
                        roundedCorners = roundedCorners,
                    )
                )
            }
        }.asSequence()
}

private val roundedCornersValues = listOf(false, true)
