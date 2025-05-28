package com.sottti.roller.coasters.presentation.settings.model

internal data class SettingsPreviewState(
    val onAction: (SettingsAction) -> Unit,
    val onBackNavigation: () -> Unit,
    val state: SettingsState,
)