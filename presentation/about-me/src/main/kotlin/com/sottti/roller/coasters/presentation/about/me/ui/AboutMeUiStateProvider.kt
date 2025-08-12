package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.about.me.data.initialState
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState

internal class AboutMeUiStateProvider : PreviewParameterProvider<AboutMePreviewState> {
    override val values: Sequence<AboutMePreviewState> = sequenceOf(
        AboutMePreviewState(
            onAction = {},
            onListCreated = { listState, scrollBehavior -> },
            onNavigateToSettings = {},
            padding = PaddingValues(),
            state = initialState,
        ),
    )
}
