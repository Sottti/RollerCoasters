package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.roller.coasters.presentation.design.system.images.data.Images
import com.sottti.roller.coasters.presentation.about.me.data.initialState
import com.sottti.roller.coasters.presentation.about.me.model.AboutMePreviewState
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeState
import com.sottti.roller.coasters.presentation.about.me.model.ProfileImageState

@OptIn(ExperimentalMaterial3Api::class)
internal class AboutMeUiViewStateProvider : PreviewParameterProvider<AboutMePreviewState> {
    override val values: Sequence<AboutMePreviewState> = sequenceOf(
        AboutMePreviewState(
            onAction = {},
            onListCreated = { listState, scrollBehavior -> },
            onNavigateToSettings = {},
            state = initialState,
        )
    )
}