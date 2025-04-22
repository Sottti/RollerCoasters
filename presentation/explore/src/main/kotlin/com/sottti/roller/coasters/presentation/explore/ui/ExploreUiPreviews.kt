package com.sottti.roller.coasters.presentation.explore.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.roller.coasters.presentation.design.system.themes.RollerCoastersPreviewTheme
import com.sottti.roller.coasters.presentation.explore.model.ExplorePreviewState
import com.sottti.roller.coasters.presentation.previews.LightDarkThemePreview

@Composable
@LightDarkThemePreview
internal fun StandardPreview(
    @PreviewParameter(ExploreUiViewStateProvider::class)
    state: ExplorePreviewState,
) {
    RollerCoastersPreviewTheme {
        ExploreUi(
            filters = state.filters,
            navigator = state.navigator,
            onAction = state.onAction,
            onListStateCreated = state.onListStateCreated,
            rollerCoasters = state.rollerCoasters.collectAsLazyPagingItems(),
        )
    }
}