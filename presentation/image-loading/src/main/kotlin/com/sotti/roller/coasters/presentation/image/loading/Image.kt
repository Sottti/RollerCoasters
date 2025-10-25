package com.sotti.roller.coasters.presentation.image.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sotti.roller.coasters.domain.model.ImageUrl
import com.sotti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sotti.roller.coasters.presentation.design.system.shapes.shapes
import com.sotti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sotti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
public fun Image(
    url: ImageUrl,
    contentDescription: String,
    modifier: Modifier = Modifier,
    roundedCorners: Boolean = false,
    foreverLoading: Boolean = false,
) {
    val isPreview = LocalInspectionMode.current
    val model = if (isPreview) previewImageModel() else imageRequest(url)
    val painter = rememberAsyncImagePainter(model)
    val painterState by painter.state.collectAsStateWithLifecycle()
    val cornerRadius = when {
        roundedCorners -> shapes.roundedCorner.medium
        else -> RoundedCornerShape(ZeroCornerSize)
    }

    Box(modifier = modifier.clip(cornerRadius)) {
        val imageModifier = Modifier.matchParentSize()
        when {
            !foreverLoading -> Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = imageModifier,
            )

            else -> PlaceHolder(
                foreverLoading = foreverLoading,
                modifier = imageModifier,
                painterState = painterState,
            )
        }
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
private fun imageRequest(url: ImageUrl): ImageRequest {
    val context = LocalContext.current
    return remember(url, context) {
        ImageRequest
            .Builder(context)
            .data(url.value)
            .crossfade(true)
            .build()
    }
}

@Composable
@ReadOnlyComposable
private fun previewImageModel() = R.drawable.dragon_khan_hero_image

@Composable
@RollerCoastersPreview
internal fun ImagePreview(
    @PreviewParameter(ImageStateProvider::class)
    state: ImageState,
) {
    RollerCoastersTheme {
        Image(
            contentDescription = state.contentDescription,
            foreverLoading = state.foreverLoading,
            modifier = Modifier.aspectRatio(ratio = 1.75f),
            roundedCorners = state.roundedCorners,
            url = state.imageUrl,
        )
    }
}
