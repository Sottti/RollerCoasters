package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class SettingsState(
    val colorContrast: ColorContrastState,
    val dynamicColor: DynamicColorState?,
    val theme: ThemeState,
    val topBar: TopBarState,
)