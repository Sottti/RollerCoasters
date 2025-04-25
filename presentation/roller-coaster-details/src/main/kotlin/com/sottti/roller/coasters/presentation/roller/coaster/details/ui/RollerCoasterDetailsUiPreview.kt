package com.sottti.roller.coasters.presentation.roller.coaster.details.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsViewState

@Composable
@LightDarkThemePreview
internal fun RollerCoasterDetailsUiPreview(
    @PreviewParameter(RollerCoasterDetailsUiViewStateProvider::class)
    state: RollerCoasterDetailsViewState,
) {
    RollerCoastersPreviewTheme {
        RollerCoasterDetailsUi(
            onBackNavigation = {},
            state = state,
        )
    }
}