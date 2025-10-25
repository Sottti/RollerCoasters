package com.sottti.roller.coasters.presentation.image.loading

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.fixtureContentDescription
import com.sottti.roller.coasters.presentation.fixtures.fixtureImageUrl

internal class ImageStateProvider :
    PreviewParameterProvider<ImageState> {
    override val values: Sequence<ImageState> =
        sequence {
            yield(imageState(loading = true, roundedCorners = false))
            roundedCornersValues.forEach { roundedCorners ->
                yield(imageState(loading = false, roundedCorners = roundedCorners))
            }
        }
}

private val roundedCornersValues = listOf(false, true)

private fun imageState(
    loading: Boolean,
    roundedCorners: Boolean,
) = ImageState(
    contentDescription = fixtureContentDescription,
    foreverLoading = loading,
    imageUrl = fixtureImageUrl,
    roundedCorners = roundedCorners,
)
