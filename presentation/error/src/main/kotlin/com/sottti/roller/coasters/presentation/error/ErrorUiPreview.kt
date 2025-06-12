package com.sottti.roller.coasters.presentation.error

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
@RollerCoastersPreview
internal fun ErrorUiPreview(
    @PreviewParameter(ErrorUiStateProvider::class)
    viewState: ErrorState?,
) {
    RollerCoastersPreviewTheme {
        when (viewState) {
            null -> ErrorUi(
                ErrorButton(
                    text = R.string.error_button_text_default,
                    onClick = {},
                ),
                modifier = Modifier.background(colors.background),
            )

            else -> ErrorUi(
                modifier = Modifier.background(colors.background),
                illustration = viewState.illustration,
                primaryText = viewState.primaryText,
                secondaryText = viewState.secondaryText,
                button = ErrorButton(
                    text = viewState.buttonText,
                    onClick = {},
                ),
            )
        }
    }
}