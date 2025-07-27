package com.sottti.roller.coasters.presentation.settings.data

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrast
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppLanguage
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystem
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppTheme
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState

internal fun loadingState(
    dynamicColorAvailable: Boolean,
) = initialState(dynamicColorAvailable = dynamicColorAvailable)

internal fun loadedState(
    dynamicColorAvailable: Boolean = true,
    dynamicColorState: AppDynamicColor = AppDynamicColor.Enabled,
) =
    initialState(dynamicColorAvailable)
        .updateAppColorContrast(AppColorContrast.System)
        .updateAppLanguage(AppLanguage.System)
        .updateAppMeasurementSystem(AppMeasurementSystem.System)
        .updateAppTheme(AppTheme.System)
        .updateDynamicColor(dynamicColorState)

internal fun loadedStateWithDynamicColorLoading(): SettingsState {
    val baseState = loadedState()
    return baseState.copy(
        dynamicColor = baseState.dynamicColor?.copy(
            checkedState = DynamicColorCheckedState.Loading,
        ),
    )
}

internal fun loadedStateWithNullDynamicColor(): SettingsState =
    loadedState().copy(dynamicColor = null)
