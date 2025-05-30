package com.sottti.roller.coasters.presentation.settings.data


import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.GetAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.SetAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveAppDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.SetAppDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.language.GetAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.SetAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.SetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.theme.GetAppTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.ObserveAppTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.SetAppTheme
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import io.mockk.mockk

internal fun createViewModel(
    features: Features = mockk(),
    getAppColorContrast: GetAppColorContrast = mockk(),
    getAppLanguage: GetAppLanguage = mockk(),
    getAppMeasurementSystem: GetAppMeasurementSystem = mockk(),
    getAppTheme: GetAppTheme = mockk(),
    initialState: SettingsState? = null,
    observeAppColorContrast: ObserveAppColorContrast = mockk(),
    observeAppDynamicColor: ObserveAppDynamicColor = mockk(),
    observeAppLanguage: ObserveAppLanguage = mockk(),
    observeAppMeasurementSystem: ObserveAppMeasurementSystem = mockk(),
    observeAppTheme: ObserveAppTheme = mockk(),
    setAppColorContrast: SetAppColorContrast = mockk(),
    setAppDynamicColor: SetAppDynamicColor = mockk(),
    setAppLanguage: SetAppLanguage = mockk(),
    setAppMeasurementSystem: SetAppMeasurementSystem = mockk(),
    setAppTheme: SetAppTheme = mockk(),
): SettingsViewModel = SettingsViewModel(
    observeAppColorContrast = observeAppColorContrast,
    observeAppDynamicColor = observeAppDynamicColor,
    observeAppLanguage = observeAppLanguage,
    observeAppMeasurementSystem = observeAppMeasurementSystem,
    observeAppTheme = observeAppTheme,
    features = features,
    getAppColorContrast = getAppColorContrast,
    getAppLanguage = getAppLanguage,
    getAppMeasurementSystem = getAppMeasurementSystem,
    getAppTheme = getAppTheme,
    setAppColorContrast = setAppColorContrast,
    setAppDynamicColor = setAppDynamicColor,
    setAppLanguage = setAppLanguage,
    setAppMeasurementSystem = setAppMeasurementSystem,
    setAppTheme = setAppTheme,
    initialState = initialState,
)

internal suspend fun SettingsViewModel.assertHasState(expected: SettingsState) =
    state.test { assertHasState(expected) }

internal suspend fun TurbineTestContext<SettingsState>.assertHasState(expected: SettingsState) {
    var state: SettingsState
    do state = awaitItem() while (state != expected)
    assertThat(state).isEqualTo(expected)
}