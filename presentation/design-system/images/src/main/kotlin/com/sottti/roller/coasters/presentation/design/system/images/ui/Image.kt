package com.sottti.roller.coasters.presentation.design.system.images.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.images.model.ImageState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreviewSingleLocale
import androidx.compose.foundation.Image as MaterialImage

@Composable
public fun Image(
    state: ImageState,
    modifier: Modifier = Modifier,
    roundedCorners: Boolean = true,
) {
    MaterialImage(
        painter = painterResource(id = state.resId),
        contentDescription = stringResource(state.descriptionResId),
        modifier = when {
            roundedCorners -> modifier.clip(MaterialTheme.shapes.medium)
            else -> modifier
        },
    )
}

@Composable
@RollerCoastersPreviewSingleLocale
internal fun ImagePreview(
    @PreviewParameter(ImageStateProvider::class)
    state: ImagePreviewState,
) {
    RollerCoastersPreviewTheme {
        Image(
            modifier = state.modifier,
            roundedCorners = state.roundedCorners,
            state = state.state,
        )
    }
}