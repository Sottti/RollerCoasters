package com.sottti.roller.coasters.presentation.design.system.illustrations.ui.default

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import androidx.compose.foundation.Image as MaterialImage

@Composable
public fun Illustration(
    state: IllustrationState,
    modifier: Modifier = Modifier,
    circled: Boolean = false,
) {
    val modifier: Modifier =
        when {
            circled -> modifier
                .aspectRatio(1f)
                .clip(CircleShape)

            else -> modifier
        }
    MaterialImage(
        contentDescription = stringResource(state.descriptionResId),
        contentScale = ContentScale.Crop,
        modifier = modifier,
        painter = painterResource(id = state.resId),
    )
}

@Composable
@RollerCoastersPreview
internal fun IllustrationPreview(
    @PreviewParameter(IllustrationUiStateProvider::class)
    state: IllustrationPreviewState,
) {
    RollerCoastersTheme {
        Illustration(
            circled = state.circled,
            modifier = state.modifier,
            state = state.state,
        )
    }
}
