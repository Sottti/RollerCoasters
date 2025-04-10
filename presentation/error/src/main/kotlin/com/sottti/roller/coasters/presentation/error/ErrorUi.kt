package com.sottti.roller.coasters.presentation.error

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.roller.coasters.presentation.informative.InformativeUi

@Composable
public fun ErrorUi(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    illustration: IllustrationState = Illustrations.BrokenTrack.state,
    @StringRes primaryText: Int = R.string.primary_text_default,
    @StringRes secondaryText: Int = R.string.secondary_text_default,
    @StringRes buttonText: Int = R.string.button_text_default,
) {
    InformativeUi(
        illustration = illustration,
        primaryText = primaryText,
        secondaryText = secondaryText,
        buttonText = buttonText,
        onButtonClick = onButtonClick,
        modifier = modifier,
    )
}