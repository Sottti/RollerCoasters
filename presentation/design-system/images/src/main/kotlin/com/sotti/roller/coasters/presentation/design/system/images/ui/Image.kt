package com.sotti.roller.coasters.presentation.design.system.images.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sotti.roller.coasters.presentation.design.system.images.model.ImageState
import com.sotti.roller.coasters.presentation.design.system.shapes.shapes
import com.sotti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sotti.roller.coasters.presentation.previews.RollerCoastersPreviewSingleLocale
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
            roundedCorners -> modifier.clip(shapes.roundedCorner.medium)
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
    RollerCoastersTheme {
        Image(
            modifier = state.modifier,
            roundedCorners = state.roundedCorners,
            state = state.state,
        )
    }
}
