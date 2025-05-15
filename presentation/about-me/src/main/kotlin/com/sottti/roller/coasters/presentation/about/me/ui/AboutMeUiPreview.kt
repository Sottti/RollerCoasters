package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
@LightDarkThemePreview
@OptIn(ExperimentalMaterial3Api::class)
internal fun AboutMeUiPreview(
    @PreviewParameter(AboutMeUiViewStateProvider::class)
    state: AboutMePreviewState,
) {
    RollerCoastersPreviewTheme {
        AboutMeUi(
            onAction = state.onAction,
            onListCreated = state.onListCreated,
            onNavigateToSettings = state.onNavigateToSettings,
            state = state.state,
        )
    }
}