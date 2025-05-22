package com.sottti.roller.coasters.presentation.design.system.hero.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.roller.coasters.presentation.design.system.images.model.ImageState
import com.roller.coasters.presentation.design.system.images.ui.Image
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview


@Composable
public fun HeroImage(
    modifier: Modifier,
    image: ImageState,
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
    ) {
        Image(
            modifier = Modifier
                .padding(dimensions.padding.small)
                .fillMaxSize(),
            state = image,
        )
    }
}

@Composable
@LightDarkThemePreview
internal fun HeroImagePreview(
    @PreviewParameter(ProfilePicturePreviewProvider::class)
    state: HeroImageState,
) {
    RollerCoastersPreviewTheme {
        HeroImage(
            modifier = state.modifier,
            image = state.image,
        )
    }
}