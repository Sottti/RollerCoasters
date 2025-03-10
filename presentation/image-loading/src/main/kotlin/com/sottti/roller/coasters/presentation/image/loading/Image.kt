package com.sottti.roller.coasters.presentation.image.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.data.contentDescription
import com.sottti.roller.coasters.presentation.previews.data.imageUrl

@Composable
public fun Image(
    url: ImageUrl,
    contentDescription: String,
    modifier: Modifier = Modifier,
    foreverLoading: Boolean = false,
) {
    val isPreview = LocalInspectionMode.current
    val model = if (isPreview) previewImageModel() else imageRequest(url)
    val painter = rememberAsyncImagePainter(model)
    val painterState by painter.state.collectAsState()

    Box(modifier = modifier) {
        val matchParentSizeModifier = Modifier.matchParentSize()
        if (foreverLoading != true) Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = matchParentSizeModifier,
        )
        PlaceHolder(
            foreverLoading = foreverLoading,
            modifier = matchParentSizeModifier,
            painterState = painterState,
        )
    }
}

@Composable
private fun PlaceHolder(
    foreverLoading: Boolean,
    modifier: Modifier,
    painterState: AsyncImagePainter.State,
) {
    when {
        foreverLoading || painterState is AsyncImagePainter.State.Loading ->
            ProgressIndicator(modifier = modifier)
    }
}

@Composable
private fun imageRequest(
    url: ImageUrl,
): ImageRequest = ImageRequest
    .Builder(LocalContext.current)
    .data(url.value)
    .crossfade(true)
    .build()

@Composable
private fun previewImageModel() = R.drawable.dragon_khan_hero_image

@Composable
@PreviewLightDark
private fun ImageLoadedPreview() {
    RollerCoastersPreviewTheme {
        Image(
            url = imageUrl,
            contentDescription = contentDescription,
            modifier = Modifier.aspectRatio(1.75f),
            foreverLoading = false,
        )
    }
}

@Composable
@PreviewLightDark
private fun ImageLoadingPreview() {
    RollerCoastersPreviewTheme {
        Image(
            url = imageUrl,
            contentDescription = contentDescription,
            modifier = Modifier.aspectRatio(1.75f),
            foreverLoading = true,
        )
    }
}