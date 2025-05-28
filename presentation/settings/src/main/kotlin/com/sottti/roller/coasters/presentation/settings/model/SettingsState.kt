package com.sottti.roller.coasters.presentation.settings.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.roller.coasters.presentation.design.system.icons.model.IconState

@Immutable
internal data class SettingsState(
    val appColorContrast: AppColorContrastState,
    val appLanguage: AppLanguageState,
    val appMeasurementSystem: AppMeasurementSystemState,
    val appTheme: AppThemeState,
    val dynamicColor: DynamicColorState?,
    val topBar: SettingsTopBarState,
)