package com.sotti.roller.coasters.presentation.settings.data

import com.sotti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sotti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sotti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrast
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppLanguage
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystem
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateAppTheme
import com.sotti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sotti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sotti.roller.coasters.presentation.settings.model.SettingsState

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
