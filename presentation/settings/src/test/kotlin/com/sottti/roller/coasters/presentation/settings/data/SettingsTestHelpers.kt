package com.sottti.roller.coasters.presentation.settings.data

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
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
import com.sottti.roller.coasters.domain.system.features.SystemFeatures
import com.sottti.roller.coasters.presentation.settings.data.mapper.toDomain
import com.sottti.roller.coasters.presentation.settings.model.AppSelectedLanguageState
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppThemeState
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

internal fun createViewModel(
    systemFeatures: SystemFeatures = mockk(),
    getAppColorContrast: GetAppColorContrast = mockk(),
    getAppLanguage: GetAppLanguage = mockk(),
    getAppMeasurementSystem: GetAppMeasurementSystem = mockk(),
    getAppTheme: GetAppTheme = mockk(),
    initialState: SettingsState? = null,
    observeAppColorContrast: ObserveAppColorContrast = defaultObserveAppColorContrast(initialState),
    observeAppDynamicColor: ObserveAppDynamicColor = defaultObserveAppDynamicColor(initialState),
    observeAppLanguage: ObserveAppLanguage = defaultObserveAppLanguage(initialState),
    observeAppMeasurementSystem: ObserveAppMeasurementSystem =
        defaultObserveAppMeasurementSystem(initialState),
    observeAppTheme: ObserveAppTheme = defaultObserveAppTheme(initialState),
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
    systemFeatures = systemFeatures,
    getAppColorContrast = getAppColorContrast,
    getAppLanguage = getAppLanguage,
    getAppMeasurementSystem = getAppMeasurementSystem,
    getAppTheme = getAppTheme,
    setAppColorContrast = setAppColorContrast,
    setAppDynamicColor = setAppDynamicColor,
    setAppLanguage = setAppLanguage,
    setAppMeasurementSystem = setAppMeasurementSystem,
    setAppTheme = setAppTheme,
    testInitialState = initialState,
)

private fun defaultObserveAppColorContrast(
    initialState: SettingsState?,
): ObserveAppColorContrast {
    val observeAppColorContrast = mockk<ObserveAppColorContrast>()
    val appColorContrastFlow =
        initialState?.let { flowOf(it.appColorContrast()) } ?: emptyFlow<AppColorContrast>()
    every { observeAppColorContrast() } returns appColorContrastFlow
    return observeAppColorContrast
}

private fun defaultObserveAppDynamicColor(
    initialState: SettingsState?,
): ObserveAppDynamicColor {
    val observeAppDynamicColor = mockk<ObserveAppDynamicColor>()
    val appDynamicColorFlow =
        initialState?.let { flowOf(it.appDynamicColor()) } ?: emptyFlow<AppDynamicColor>()
    every { observeAppDynamicColor() } returns appDynamicColorFlow
    return observeAppDynamicColor
}

private fun defaultObserveAppLanguage(
    initialState: SettingsState?,
): ObserveAppLanguage {
    val observeAppLanguage = mockk<ObserveAppLanguage>()
    val appLanguageFlow =
        initialState?.let { flowOf(it.appLanguage()) } ?: emptyFlow<AppLanguage>()
    every { observeAppLanguage() } returns appLanguageFlow
    return observeAppLanguage
}

private fun defaultObserveAppMeasurementSystem(
    initialState: SettingsState?,
): ObserveAppMeasurementSystem {
    val observeAppMeasurementSystem = mockk<ObserveAppMeasurementSystem>()
    val appMeasurementSystemFlow =
        initialState?.let {
            flowOf(it.appMeasurementSystem())
        } ?: emptyFlow<AppMeasurementSystem>()
    every { observeAppMeasurementSystem() } returns appMeasurementSystemFlow
    return observeAppMeasurementSystem
}

private fun defaultObserveAppTheme(
    initialState: SettingsState?,
): ObserveAppTheme {
    val observeAppTheme = mockk<ObserveAppTheme>()
    val appThemeFlow =
        initialState?.let { flowOf(it.appTheme()) } ?: emptyFlow<AppTheme>()
    every { observeAppTheme() } returns appThemeFlow
    return observeAppTheme
}

private fun SettingsState.appColorContrast(): AppColorContrast =
    when (val selectedAppColorContrast = appColorContrast.listItem.selectedAppColorContrast) {
        is SelectedAppColorContrastState.Loaded ->
            selectedAppColorContrast.appColorContrast.toDomain()

        SelectedAppColorContrastState.Loading -> AppColorContrast.System
    }

private fun SettingsState.appDynamicColor(): AppDynamicColor =
    when (val checkedState = dynamicColor?.checkedState) {
        is DynamicColorCheckedState.Loaded -> when {
            checkedState.checked -> AppDynamicColor.Enabled
            else -> AppDynamicColor.Disabled
        }

        DynamicColorCheckedState.Loading, null -> AppDynamicColor.Enabled
    }

private fun SettingsState.appLanguage(): AppLanguage =
    when (val selectedAppLanguage = appLanguage.listItem.selectedAppLanguage) {
        is AppSelectedLanguageState.Loaded -> selectedAppLanguage.appLanguage.toDomain()
        AppSelectedLanguageState.Loading -> AppLanguage.System
    }

private fun SettingsState.appMeasurementSystem(): AppMeasurementSystem =
    when (
        val selectedAppMeasurementSystem =
            appMeasurementSystem.listItem.selectedAppMeasurementSystem
    ) {
        is SelectedAppMeasurementSystemState.Loaded ->
            selectedAppMeasurementSystem.appMeasurementSystem.toDomain()

        SelectedAppMeasurementSystemState.Loading -> AppMeasurementSystem.System
    }

private fun SettingsState.appTheme(): AppTheme =
    when (val selectedAppTheme = appTheme.listItem.selectedAppTheme) {
        is SelectedAppThemeState.Loaded -> selectedAppTheme.appTheme.toDomain()
        SelectedAppThemeState.Loading -> AppTheme.System
    }

internal suspend fun SettingsViewModel.assertHasState(
    expected: SettingsState,
) =
    state.test {
        assertHasState(expected)
        cancelAndIgnoreRemainingEvents()
    }

internal suspend fun SettingsViewModel.assertHasState(
    expected: SettingsState,
    action: SettingsViewModel.() -> Unit,
) =
    state.test {
        awaitItem()
        delay(1)
        this@assertHasState.action()
        var current = this@assertHasState.state.value
        var attempts = 0
        while (current != expected && attempts < STATE_ASSERTION_ATTEMPTS) {
            delay(1)
            current = this@assertHasState.state.value
            attempts++
        }
        assertThat(current).isEqualTo(expected)
        cancelAndIgnoreRemainingEvents()
    }

private const val STATE_ASSERTION_ATTEMPTS = 100

internal suspend fun TurbineTestContext<SettingsState>.assertHasState(expected: SettingsState) {
    var state: SettingsState
    do state = awaitItem() while (state != expected)
    assertThat(state).isEqualTo(expected)
}
