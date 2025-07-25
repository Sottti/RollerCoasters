package com.sottti.roller.coasters.presentation.design.system.illustrations.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sottti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import androidx.compose.foundation.Image as MaterialImage

@Composable
public fun Illustration(
    state: IllustrationState,
    modifier: Modifier = Modifier,
) {
    MaterialImage(
        painter = painterResource(id = state.resId),
        contentDescription = stringResource(state.descriptionResId),
        modifier = modifier,
    )
}