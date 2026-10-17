package com.sottti.roller.coasters.presentation.design.system.images.network

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sottti.roller.coasters.domain.model.ImageUrl
import com.sottti.roller.coasters.presentation.design.system.images.R
import com.sottti.roller.coasters.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.roller.coasters.presentation.design.system.shapes.shapes
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewNoLocale

@Composable
public fun NetworkImage(
    url: ImageUrl,
    contentDescription: String,
    modifier: Modifier = Modifier,
    roundedCorners: Boolean = false,
    foreverLoading: Boolean = false,
) {
    val isPreview = LocalInspectionMode.current
    val cornerRadius = when {
        roundedCorners -> shapes.roundedCorner.medium
        else -> RoundedCornerShape(ZeroCornerSize)
    }

    Box(modifier = modifier.clip(cornerRadius)) {
        val imageModifier = Modifier.matchParentSize()

        when {
            foreverLoading ->
                ProgressIndicator(modifier = imageModifier)

            isPreview ->
                Image(
                    painter = painterResource(R.drawable.dragon_khan_hero_image),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier,
                )

            else -> AsyncNetworkImage(
                contentDescription = contentDescription,
                modifier = imageModifier,
                url = url,
            )
        }
    }
}

@Composable
private fun AsyncNetworkImage(
    url: ImageUrl,
    contentDescription: String,
    modifier: Modifier,
) {
    val painter = rememberNetworkImagePainter(url)
    val painterState by painter.state.collectAsStateWithLifecycle()

    when {
        painterState is AsyncImagePainter.State.Loading ->
            ProgressIndicator(modifier = modifier)

        painterState is AsyncImagePainter.State.Success ->
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = modifier,
            )

        else -> Box(modifier = modifier)
    }
}

@Composable
private fun rememberNetworkImagePainter(url: ImageUrl): AsyncImagePainter =
    rememberAsyncImagePainter(imageRequest(url))

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
@RollerCoastersPreviewNoLocale
internal fun NetworkImagePreview(
    @PreviewParameter(NetworkImageStateProvider::class)
    state: NetworkImageState,
) {
    RollerCoastersTheme {
        NetworkImage(
            contentDescription = state.contentDescription,
            foreverLoading = state.foreverLoading,
            modifier = Modifier.aspectRatio(ratio = 1.75f),
            roundedCorners = state.roundedCorners,
            url = state.imageUrl,
        )
    }
}
