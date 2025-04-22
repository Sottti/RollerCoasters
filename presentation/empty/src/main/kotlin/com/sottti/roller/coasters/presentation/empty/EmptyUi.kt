package com.sottti.roller.coasters.presentation.empty

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.roller.coasters.presentation.informative.InformativeUi

@Composable
public fun EmptyUi(
    modifier: Modifier = Modifier,
    illustration: IllustrationState = Illustrations.EmptyTrack.state,
    @StringRes primaryText: Int = R.string.empty_primary_text_default,
    @StringRes secondaryText: Int = R.string.empty_secondary_text_default,
) {
    InformativeUi(
        illustration = illustration,
        primaryText = primaryText,
        secondaryText = secondaryText,
        modifier = modifier,
    )
}