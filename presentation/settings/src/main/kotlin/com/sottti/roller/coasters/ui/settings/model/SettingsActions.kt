package com.sottti.roller.coasters.ui.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class SettingsActions(
    val onDynamicColorCheckedChange: (Boolean) -> Unit,
)