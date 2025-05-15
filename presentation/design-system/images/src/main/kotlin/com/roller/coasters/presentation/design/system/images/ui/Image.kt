package com.roller.coasters.presentation.design.system.images.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.roller.coasters.presentation.design.system.images.model.ImageState
import androidx.compose.foundation.Image as MaterialImage

@Composable
public fun Image(
    state: ImageState,
    modifier: Modifier = Modifier,
) {
    MaterialImage(
        painter = painterResource(id = state.resId),
        contentDescription = stringResource(state.descriptionResId),
        modifier = modifier.clip(MaterialTheme.shapes.medium),
    )
}