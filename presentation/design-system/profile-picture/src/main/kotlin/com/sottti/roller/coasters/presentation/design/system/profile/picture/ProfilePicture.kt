package com.sottti.roller.coasters.presentation.design.system.profile.picture

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
public fun ProfilePicture(
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
internal fun ProfilePicturePreview(
    @PreviewParameter(ProfilePicturePreviewProvider::class)
    state: ProfilePicturePreviewState,
) {
    RollerCoastersPreviewTheme {
        ProfilePicture(
            modifier = state.modifier,
            image = state.image,
        )
    }
}