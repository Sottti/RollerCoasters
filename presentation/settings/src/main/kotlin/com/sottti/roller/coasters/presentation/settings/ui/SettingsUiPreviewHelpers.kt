package com.sottti.roller.coasters.presentation.settings.ui

import com.sottti.roller.coasters.presentation.settings.model.SettingsPreviewState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

internal fun previewState(
    state: SettingsState,
): SettingsPreviewState =
    SettingsPreviewState(
        onAction = {},
        onBackNavigation = {},
        state = state,
    )