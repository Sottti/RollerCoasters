package com.sottti.roller.coasters.presentation.search.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview
import com.sottti.roller.coasters.presentation.search.model.SearchPreviewState

@Composable
@RollerCoastersPreview
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchUiPreview(
    @PreviewParameter(SearchUiStateProvider::class) state: SearchPreviewState,
) {
    RollerCoastersPreviewTheme {
        SearchUi(
            onAction = state.onAction,
            onNavigateToRollerCoaster = state.onNavigateToRollerCoaster,
            onNavigateToSettings = state.onNavigateToSettings,
            paddingValues = state.paddingValues,
            state = state.state,
        )
    }
}
