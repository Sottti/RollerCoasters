package com.sottti.roller.coasters.presentation.empty

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
@LightDarkThemePreview
internal fun EmptyUiPreview(
    @PreviewParameter(EmptyUiStateProvider::class)
    viewState: EmptyState?,
) {
    RollerCoastersPreviewTheme {
        when (viewState) {
            null -> EmptyUi(modifier = Modifier.background(colors.background))

            else -> EmptyUi(
                modifier = Modifier.background(colors.background),
                illustration = viewState.illustration,
                primaryText = viewState.primaryText,
                secondaryText = viewState.secondaryText,
            )
        }
    }
}