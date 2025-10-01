package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class SettingsPreviewState(
    val onAction: (SettingsAction) -> Unit,
    val onBackNavigation: () -> Unit,
    val state: SettingsState,
)
