package com.sottti.roller.coasters.presentation.design.system.hero.image

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sottti.roller.coasters.presentation.design.system.images.data.Images

internal class ProfilePicturePreviewProvider :
    PreviewParameterProvider<HeroImageState> {
    override val values = sequenceOf(
        profilePicture2024(),
    )
}

private fun profilePicture2024() =
    HeroImageState(
        modifier = Modifier
            .width(192.dp)
            .aspectRatio(1f),
        image = Images.ProfilePicture2024.state,
    )
