package com.sottti.roller.coasters.presentation.informative

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
@RollerCoastersPreview
internal fun InformativeUiPreview(
    @PreviewParameter(InformativeUiStateProvider::class)
    viewState: InformativeState,
) {
    RollerCoastersPreviewTheme {
        InformativeUi(
            modifier = Modifier.background(colors.background),
            illustration = viewState.illustration,
            primaryText = viewState.primaryText,
            secondaryText = viewState.secondaryText,
            button = toButton(viewState.buttonText),
        )
    }
}

@Composable
private fun toButton(@StringRes text: Int?): InformativeButton? =
    text?.let {
        InformativeButton(
            text = text,
            onClick = {},
        )
    }