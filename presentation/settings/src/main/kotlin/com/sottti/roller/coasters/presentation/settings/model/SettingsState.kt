package com.sottti.roller.coasters.presentation.settings.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class SettingsState(
    val appColorContrast: AppColorContrastState,
    val appLanguage: AppLanguageState,
    val appMeasurementSystem: AppMeasurementSystemState,
    val appTheme: AppThemeState,
    val dynamicColor: DynamicColorState?,
    val topBar: SettingsTopBarState,
)
