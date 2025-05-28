package com.sottti.roller.coasters.presentation.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.roller.coasters.presentation.settings.data.initialState
import com.sottti.roller.coasters.presentation.settings.model.SettingsPreviewState

@OptIn(ExperimentalMaterial3Api::class)
internal class SettingsUiStateProvider : PreviewParameterProvider<SettingsPreviewState> {
    override val values: Sequence<SettingsPreviewState> = sequenceOf(
        SettingsPreviewState(
            onAction = {},
            onBackNavigation = {},
            state = initialState(true),
        )
    )
}