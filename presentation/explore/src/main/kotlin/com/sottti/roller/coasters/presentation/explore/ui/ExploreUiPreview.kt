package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.explore.model.ExplorePreviewState
import com.sottti.roller.coasters.presentation.previews.RollerCoastersPreview

@Composable
@RollerCoastersPreview
internal fun ExploreUiPreview(
    @PreviewParameter(ExploreUiStateProvider::class)
    state: ExplorePreviewState,
) {
    RollerCoastersPreviewTheme {
        ExploreUi(
            filters = state.filters,
            onAction = state.onAction,
            onListCreated = state.onListCreated,
            onNavigateToRollerCoaster = {},
            onNavigateToSettings = {},
            paddingValues = PaddingValues(),
            rollerCoasters = state.rollerCoasters.collectAsLazyPagingItems(),
        )
    }
}