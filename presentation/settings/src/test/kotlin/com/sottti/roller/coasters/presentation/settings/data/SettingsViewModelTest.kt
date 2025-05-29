package com.sottti.roller.coasters.presentation.settings.data

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.features.Features
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
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrast
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppLanguage
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystem
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppTheme
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateDynamicColor
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SettingsViewModelTest {
    private val features = mockk<Features>()
    private val getAppColorContrast = mockk<GetAppColorContrast>()
    private val getAppLanguage = mockk<GetAppLanguage>()
    private val getAppMeasurementSystem = mockk<GetAppMeasurementSystem>()
    private val getAppTheme = mockk<GetAppTheme>()
    private val observeAppColorContrast = mockk<ObserveAppColorContrast>()
    private val observeAppDynamicColor = mockk<ObserveAppDynamicColor>()
    private val observeAppLanguage = mockk<ObserveAppLanguage>()
    private val observeAppMeasurementSystem = mockk<ObserveAppMeasurementSystem>()
    private val observeAppTheme = mockk<ObserveAppTheme>()
    private val setAppColorContrast = mockk<SetAppColorContrast>()
    private val setAppDynamicColor = mockk<SetAppDynamicColor>()
    private val setAppLanguage = mockk<SetAppLanguage>()
    private val setAppMeasurementSystem = mockk<SetAppMeasurementSystem>()
    private val setAppTheme = mockk<SetAppTheme>()

    @Test
    fun `initial loading state with dynamic color available`() = runTest {
        every { features.systemDynamicColorAvailable() } returns true
        every { observeAppTheme() } returns emptyFlow()
        every { observeAppColorContrast() } returns emptyFlow()
        every { observeAppLanguage() } returns emptyFlow()
        every { observeAppMeasurementSystem() } returns emptyFlow()
        every { observeAppDynamicColor() } returns emptyFlow()

        val viewModel = SettingsViewModel(
            features = features,
            getAppColorContrast = mockk(),
            getAppLanguage = mockk(),
            getAppMeasurementSystem = mockk(),
            getAppTheme = mockk(),
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeAppDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
            setAppColorContrast = mockk(),
            setAppDynamicColor = mockk(),
            setAppLanguage = mockk(),
            setAppMeasurementSystem = mockk(),
            setAppTheme = mockk(),
        )

        viewModel.state.test {
            assertThat(awaitItem()).isEqualTo(initialState(dynamicColorAvailable = true))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `initial loading state with dynamic color unavailable`() = runTest {
        every { features.systemDynamicColorAvailable() } returns false
        every { observeAppTheme() } returns emptyFlow()
        every { observeAppColorContrast() } returns emptyFlow()
        every { observeAppLanguage() } returns emptyFlow()
        every { observeAppMeasurementSystem() } returns emptyFlow()
        every { observeAppDynamicColor() } returns emptyFlow()

        val viewModel = SettingsViewModel(
            features = features,
            getAppColorContrast = mockk(),
            getAppLanguage = mockk(),
            getAppMeasurementSystem = mockk(),
            getAppTheme = mockk(),
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeAppDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
            setAppColorContrast = mockk(),
            setAppDynamicColor = mockk(),
            setAppLanguage = mockk(),
            setAppMeasurementSystem = mockk(),
            setAppTheme = mockk(),
        )

        viewModel.state.test {
            assertThat(awaitItem()).isEqualTo(initialState(dynamicColorAvailable = false))
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `initial loaded state with dynamic color available`() = runTest {
        every { features.systemDynamicColorAvailable() } returns true
        every { observeAppColorContrast() } returns flowOf(AppColorContrast.System)
        every { observeAppDynamicColor() } returns flowOf(AppDynamicColor.Enabled)
        every { observeAppLanguage() } returns flowOf(AppLanguage.System)
        every { observeAppMeasurementSystem() } returns flowOf(AppMeasurementSystem.System)
        every { observeAppTheme() } returns flowOf(AppTheme.System)

        val viewModel = SettingsViewModel(
            features = features,
            getAppColorContrast = mockk(),
            getAppLanguage = mockk(),
            getAppMeasurementSystem = mockk(),
            getAppTheme = mockk(),
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeAppDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
            setAppColorContrast = mockk(),
            setAppDynamicColor = mockk(),
            setAppLanguage = mockk(),
            setAppMeasurementSystem = mockk(),
            setAppTheme = mockk(),
        )

        val expectedState = initialState(dynamicColorAvailable = true)
            .updateAppColorContrast(AppColorContrast.System)
            .updateAppLanguage(AppLanguage.System)
            .updateAppMeasurementSystem(AppMeasurementSystem.System)
            .updateAppTheme(AppTheme.System)
            .updateDynamicColor(AppDynamicColor.Enabled)

        viewModel.state.test {
            var state: SettingsState
            do state = awaitItem() while (state != expectedState)
            assertThat(state).isEqualTo(expectedState)
        }
    }
}