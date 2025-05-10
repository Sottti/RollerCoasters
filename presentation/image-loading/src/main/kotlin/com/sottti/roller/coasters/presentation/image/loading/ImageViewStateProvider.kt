package com.sottti.roller.coasters.presentation.image.loading

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.fixtures.contentDescription
import com.sottti.roller.coasters.presentation.fixtures.imageUrl

internal class ImageViewStateProvider :
    PreviewParameterProvider<ImageViewState> {
    override val values: Sequence<ImageViewState> =
        buildList {
            add(
                ImageViewState(
                    contentDescription = contentDescription,
                    foreverLoading = true,
                    imageUrl = imageUrl,
                    roundedCorners = false,
                )
            )
            roundedCornersValues.forEach { roundedCorners ->
                add(
                    ImageViewState(
                        contentDescription = contentDescription,
                        foreverLoading = false,
                        imageUrl = imageUrl,
                        roundedCorners = roundedCorners,
                    )
                )
            }
        }.asSequence()
}

private val roundedCornersValues = listOf(false, true)