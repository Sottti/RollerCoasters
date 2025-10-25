package com.sottti.roller.coasters.presentation.design.system.hero.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.images.local.Image
import com.sottti.roller.coasters.presentation.design.system.images.local.model.ImageState
import com.sottti.roller.coasters.presentation.design.system.shapes.shapes
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview


@Composable
public fun HeroImage(
    modifier: Modifier,
    image: ImageState,
) {
    Card(
        modifier = modifier,
        shape = shapes.roundedCorner.large,
    ) {
        Image(
            modifier = Modifier
                .padding(dimensions.spacing.small)
                .fillMaxSize(),
            state = image,
        )
    }
}

@Composable
@RollerCoastersPreview
internal fun HeroImagePreview(
    @PreviewParameter(ProfilePictureStateProvider::class)
    state: HeroImageState,
) {
    RollerCoastersTheme {
        HeroImage(
            modifier = state.modifier,
            image = state.image,
        )
    }
}
