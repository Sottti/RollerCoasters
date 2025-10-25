package com.sotti.roller.coasters.presentation.settings.ui

import com.sotti.roller.coasters.presentation.settings.model.SettingsPreviewState
import com.sotti.roller.coasters.presentation.settings.model.SettingsState

internal fun previewState(
    state: SettingsState,
): SettingsPreviewState =
    SettingsPreviewState(
        onAction = {},
        onBackNavigation = {},
        state = state,
    )
