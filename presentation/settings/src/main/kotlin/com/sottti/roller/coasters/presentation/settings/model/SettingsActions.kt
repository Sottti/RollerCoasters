package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class SettingsActions(
    val onDynamicColorCheckedChange: (Boolean) -> Unit,
)