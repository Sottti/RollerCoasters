package com.sottti.roller.coasters.presentation.image.loading

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.fixtureContentDescription
import com.sottti.roller.coasters.presentation.fixtures.fixtureImageUrl

internal class ImageViewStateProvider :
    PreviewParameterProvider<ImageViewState> {
    override val values: Sequence<ImageViewState> =
        buildList {
            add(
                ImageViewState(
                    contentDescription = fixtureContentDescription,
                    foreverLoading = true,
                    imageUrl = fixtureImageUrl,
                    roundedCorners = false,
                )
            )
            roundedCornersValues.forEach { roundedCorners ->
                add(
                    ImageViewState(
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