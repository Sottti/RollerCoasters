package com.sottti.roller.coasters.presentation.image.loading

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.previews.data.contentDescription
import com.sottti.roller.coasters.presentation.previews.data.imageUrl

@Composable
public fun Image(
    url: ImageUrl,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    val isPreview = LocalInspectionMode.current
    AsyncImage(
        model = if (isPreview) previewImageModel() else imageRequest(url),
        contentScale = ContentScale.FillWidth,
        contentDescription = contentDescription,
        modifier = modifier,
    )
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
private fun ImagePreview() {
    Image(
        url = imageUrl,
        contentDescription = contentDescription,
        modifier = Modifier.aspectRatio(1.5f)
    )
}