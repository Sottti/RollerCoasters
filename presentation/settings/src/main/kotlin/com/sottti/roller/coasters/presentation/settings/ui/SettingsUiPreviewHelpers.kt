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

internal fun <T> List<T>.atLeast(index: Int): T = getOrElse(index) { last() }