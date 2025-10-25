package com.sotti.roller.coasters.presentation.design.system.error

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sotti.roller.coasters.presentation.design.system.colors.color.colors
import com.sotti.roller.coasters.presentation.design.system.illustrations.data.Illustrations
import com.sotti.roller.coasters.presentation.design.system.illustrations.model.IllustrationState
import com.sotti.roller.coasters.presentation.design.system.informative.InformativeButton
import com.sotti.roller.coasters.presentation.design.system.informative.InformativeUi
import com.sotti.roller.coasters.presentation.design.system.themes.RollerCoastersTheme
import com.sotti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
public fun ErrorUi(
    modifier: Modifier = Modifier,
    button: ErrorButton? = null,
    illustration: IllustrationState = Illustrations.ExpeditionToEverest.state,
    @StringRes primaryText: Int = R.string.error_primary_text_default,
    @StringRes secondaryText: Int = R.string.error_secondary_text_default,
) {
    InformativeUi(
        illustration = illustration,
        primaryText = primaryText,
        secondaryText = secondaryText,
        modifier = modifier,
        button = button?.let {
            InformativeButton(
                text = button.text,
                onClick = button.onClick,
            )
        },
    )
}

@Composable
@RollerCoastersPreview
internal fun ErrorUiPreview(
    @PreviewParameter(ErrorUiStateProvider::class)
    state: ErrorState?,
) {
    RollerCoastersTheme {
        when (state) {
            null -> ErrorUi(modifier = Modifier.background(colors.background))
            else -> ErrorUi(
                modifier = Modifier.background(colors.background),
                illustration = state.illustration,
                primaryText = state.primaryText,
                secondaryText = state.secondaryText,
                button = ErrorButton(
                    text = state.buttonText,
                    onClick = {},
                ),
            )
        }
    }
}
