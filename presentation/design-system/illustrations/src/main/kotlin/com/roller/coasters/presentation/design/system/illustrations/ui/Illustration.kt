package com.roller.coasters.presentation.design.system.illustrations.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState

@Composable
public fun Illustration(
    state: IllustrationState,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = state.resId),
        contentDescription = stringResource(state.descriptionResId),
        modifier = modifier,
    )
}