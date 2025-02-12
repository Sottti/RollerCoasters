package com.sottti.roller.coasters.presentation.image.loading

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sottti.roller.coasters.domain.model.ImageUrl

@Composable
public fun Image(
    url: ImageUrl,
    contentDescription: String,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url.value)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
    )
}