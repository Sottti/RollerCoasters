package com.sottti.roller.coasters.presentation.settings.data

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
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppColorContrastState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppMeasurementSystemState
import com.sottti.roller.coasters.presentation.settings.model.SelectedAppThemeState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

internal fun createViewModel(
    systemFeatures: SystemFeatures? = null,
    getAppColorContrast: GetAppColorContrast? = null,
    getAppLanguage: GetAppLanguage? = null,
    getAppMeasurementSystem: GetAppMeasurementSystem? = null,
    getAppTheme: GetAppTheme? = null,
    initialState: SettingsState? = null,
    observeAppColorContrast: ObserveAppColorContrast? = null,
    observeAppDynamicColor: ObserveAppDynamicColor? = null,
    observeAppLanguage: ObserveAppLanguage? = null,
    observeAppMeasurementSystem: ObserveAppMeasurementSystem? = null,
    observeAppTheme: ObserveAppTheme? = null,
    setAppColorContrast: SetAppColorContrast = mockk(relaxed = true),
    setAppDynamicColor: SetAppDynamicColor = mockk(relaxed = true),
    setAppLanguage: SetAppLanguage = mockk(relaxed = true),
    setAppMeasurementSystem: SetAppMeasurementSystem = mockk(relaxed = true),
    setAppTheme: SetAppTheme = mockk(relaxed = true),
): SettingsViewModel {
    val appColorContrast = initialState.defaultAppColorContrast()
    val appDynamicColor = initialState.defaultAppDynamicColor()
    val appLanguage = initialState.defaultAppLanguage()
    val appMeasurementSystem = initialState.defaultAppMeasurementSystem()
    val appTheme = initialState.defaultAppTheme()

    return SettingsViewModel(
        observeAppColorContrast = observeAppColorContrast ?: mockObserveAppColorContrast(appColorContrast),
        observeAppDynamicColor = observeAppDynamicColor ?: mockObserveAppDynamicColor(appDynamicColor),
        observeAppLanguage = observeAppLanguage ?: mockObserveAppLanguage(appLanguage),
        observeAppMeasurementSystem =
            observeAppMeasurementSystem ?: mockObserveAppMeasurementSystem(appMeasurementSystem),
        observeAppTheme = observeAppTheme ?: mockObserveAppTheme(appTheme),
        systemFeatures = systemFeatures ?: mockSystemFeatures(),
        getAppColorContrast = getAppColorContrast ?: mockGetAppColorContrast(appColorContrast),
        getAppLanguage = getAppLanguage ?: mockGetAppLanguage(appLanguage),
        getAppMeasurementSystem =
            getAppMeasurementSystem ?: mockGetAppMeasurementSystem(appMeasurementSystem),
        getAppTheme = getAppTheme ?: mockGetAppTheme(appTheme),
        setAppColorContrast = setAppColorContrast,
        setAppDynamicColor = setAppDynamicColor,
        setAppLanguage = setAppLanguage,
        setAppMeasurementSystem = setAppMeasurementSystem,
        setAppTheme = setAppTheme,
        testInitialState = initialState,
    )
}

private fun mockSystemFeatures(): SystemFeatures =
    mockk {
        every { systemColorContrastAvailable() } returns true
        every { systemDynamicColorAvailable() } returns true
        every { lightDarkSystemThemingAvailable() } returns true
        every { measurementSystemAvailable() } returns true
        every { setPersistentNightModeAvailable() } returns true
    }

private fun mockGetAppColorContrast(
    appColorContrast: AppColorContrast,
): GetAppColorContrast =
    mockk<GetAppColorContrast>().also { useCase ->
        coEvery { useCase() } returns appColorContrast
    }

private fun mockGetAppLanguage(
    appLanguage: AppLanguage,
): GetAppLanguage =
    mockk<GetAppLanguage>().also { useCase ->
        coEvery { useCase() } returns appLanguage
    }

private fun mockGetAppMeasurementSystem(
    appMeasurementSystem: AppMeasurementSystem,
): GetAppMeasurementSystem =
    mockk<GetAppMeasurementSystem>().also { useCase ->
        coEvery { useCase() } returns appMeasurementSystem
    }

private fun mockGetAppTheme(
    appTheme: AppTheme,
): GetAppTheme =
    mockk<GetAppTheme>().also { useCase ->
        coEvery { useCase() } returns appTheme
    }

private fun mockObserveAppColorContrast(
    appColorContrast: AppColorContrast,
): ObserveAppColorContrast =
    mockk<ObserveAppColorContrast>().also { useCase ->
        every { useCase() } returns flowOf(appColorContrast)
    }

private fun mockObserveAppDynamicColor(
    appDynamicColor: AppDynamicColor,
): ObserveAppDynamicColor =
    mockk<ObserveAppDynamicColor>().also { useCase ->
        every { useCase() } returns flowOf(appDynamicColor)
    }

private fun mockObserveAppLanguage(
    appLanguage: AppLanguage,
): ObserveAppLanguage =
    mockk<ObserveAppLanguage>().also { useCase ->
        every { useCase() } returns flowOf(appLanguage)
    }

private fun mockObserveAppMeasurementSystem(
    appMeasurementSystem: AppMeasurementSystem,
): ObserveAppMeasurementSystem =
    mockk<ObserveAppMeasurementSystem>().also { useCase ->
        every { useCase() } returns flowOf(appMeasurementSystem)
    }

private fun mockObserveAppTheme(
    appTheme: AppTheme,
): ObserveAppTheme =
    mockk<ObserveAppTheme>().also { useCase ->
        every { useCase() } returns flowOf(appTheme)
    }

internal suspend fun SettingsViewModel.assertHasState(expected: SettingsState) =
    if (state.value == expected) {
        assertThat(state.value).isEqualTo(expected)
    } else {
        withActiveStateCollection {
            awaitState(expected)
        }
    }

internal suspend fun SettingsViewModel.assertHasStateAfterAction(
    expected: SettingsState,
    action: SettingsViewModel.() -> Unit,
) = withActiveStateCollection {
    action(this@assertHasStateAfterAction)
    awaitState(expected)
}

private fun SettingsState?.defaultAppColorContrast(): AppColorContrast =
    when (val selected = this?.appColorContrast?.listItem?.selectedAppColorContrast) {
        is SelectedAppColorContrastState.Loaded -> selected.appColorContrast.toDomain()
        else -> AppColorContrast.System
    }

private fun SettingsState?.defaultAppDynamicColor(): AppDynamicColor =
    when (val checkedState = this?.dynamicColor?.checkedState) {
        is DynamicColorCheckedState.Loaded ->
            if (checkedState.checked) AppDynamicColor.Enabled else AppDynamicColor.Disabled
        else -> AppDynamicColor.Enabled
    }

private fun SettingsState?.defaultAppLanguage(): AppLanguage =
    when (val selected = this?.appLanguage?.listItem?.selectedAppLanguage) {
        is AppSelectedLanguageState.Loaded -> selected.appLanguage.toDomain()
        else -> AppLanguage.System
    }

private fun SettingsState?.defaultAppMeasurementSystem(): AppMeasurementSystem =
    when (val selected = this?.appMeasurementSystem?.listItem?.selectedAppMeasurementSystem) {
        is SelectedAppMeasurementSystemState.Loaded -> selected.appMeasurementSystem.toDomain()
        else -> AppMeasurementSystem.System
    }

private fun SettingsState?.defaultAppTheme(): AppTheme =
    when (val selected = this?.appTheme?.listItem?.selectedAppTheme) {
        is SelectedAppThemeState.Loaded -> selected.appTheme.toDomain()
        else -> AppTheme.System
    }

private suspend fun SettingsViewModel.awaitState(expected: SettingsState) {
    repeat(100) {
        if (state.value == expected) {
            assertThat(state.value).isEqualTo(expected)
            return
        }
        yield()
    }

    assertThat(state.value).isEqualTo(expected)
}

private suspend fun SettingsViewModel.withActiveStateCollection(
    block: suspend () -> Unit,
) = coroutineScope {
    val collection = launch(start = CoroutineStart.UNDISPATCHED) {
        state.collect { }
    }

    try {
        repeat(10) { yield() }
        block()
    } finally {
        collection.cancel()
    }
}
