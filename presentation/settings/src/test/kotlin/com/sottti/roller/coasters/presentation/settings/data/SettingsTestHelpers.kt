package com.sottti.roller.coasters.presentation.settings.data

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import androidx.lifecycle.viewModelScope
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
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

internal data class SettingsObservations(
    val appColorContrast: Flow<AppColorContrast> = flowOf(AppColorContrast.System),
    val appDynamicColor: Flow<AppDynamicColor> = flowOf(AppDynamicColor.Enabled),
    val appLanguage: Flow<AppLanguage> = flowOf(AppLanguage.System),
    val appMeasurementSystem: Flow<AppMeasurementSystem> = flowOf(AppMeasurementSystem.System),
    val appTheme: Flow<AppTheme> = flowOf(AppTheme.System),
)

internal fun pendingSettingsObservations() = SettingsObservations(
    appColorContrast = emptyFlow(),
    appDynamicColor = emptyFlow(),
    appLanguage = emptyFlow(),
    appMeasurementSystem = emptyFlow(),
    appTheme = emptyFlow(),
)

@OptIn(ExperimentalCoroutinesApi::class)
internal class MainDispatcherRule(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
internal fun TestScope.createViewModel(
    observations: SettingsObservations = SettingsObservations(),
    systemFeatures: SystemFeatures = mockk(),
    getAppColorContrast: GetAppColorContrast = mockk(),
    getAppLanguage: GetAppLanguage = mockk(),
    getAppMeasurementSystem: GetAppMeasurementSystem = mockk(),
    getAppTheme: GetAppTheme = mockk(),
    initialState: SettingsState? = null,
    observeAppColorContrast: ObserveAppColorContrast = mockk<ObserveAppColorContrast>().also { observer ->
        every { observer() } returns observations.appColorContrast
    },
    observeAppDynamicColor: ObserveAppDynamicColor = mockk<ObserveAppDynamicColor>().also { observer ->
        every { observer() } returns observations.appDynamicColor
    },
    observeAppLanguage: ObserveAppLanguage = mockk<ObserveAppLanguage>().also { observer ->
        every { observer() } returns observations.appLanguage
    },
    observeAppMeasurementSystem: ObserveAppMeasurementSystem = mockk<ObserveAppMeasurementSystem>().also { observer ->
        every { observer() } returns observations.appMeasurementSystem
    },
    observeAppTheme: ObserveAppTheme = mockk<ObserveAppTheme>().also { observer ->
        every { observer() } returns observations.appTheme
    },
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
).also { viewModel ->
    backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
        try {
            viewModel.state.collect()
        } finally {
            viewModel.viewModelScope.cancel()
        }
    }
    runCurrent()
}

internal suspend fun SettingsViewModel.assertHasState(expected: SettingsState) =
    state.test { assertHasState(expected) }

internal suspend fun TurbineTestContext<SettingsState>.assertHasState(expected: SettingsState) {
    var state: SettingsState
    do state = awaitItem() while (state != expected)
    assertThat(state).isEqualTo(expected)
}
