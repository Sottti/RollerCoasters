package com.sottti.roller.coasters.presentation.settings.data

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
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {
    private lateinit var viewModel: SettingsViewModel

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

    @Before
    fun setup() {
        coEvery { getAppColorContrast() } returns AppColorContrast.StandardContrast
        coEvery { getAppLanguage() } returns AppLanguage.System
        coEvery { getAppMeasurementSystem() } returns AppMeasurementSystem.Metric
        coEvery { getAppTheme() } returns AppTheme.System

        every { features.lightDarkSystemThemingAvailable() } returns true
        every { features.systemColorContrastAvailable() } returns true
        every { features.systemDynamicColorAvailable() } returns true

        every { observeAppColorContrast() } returns MutableStateFlow(AppColorContrast.StandardContrast)
        every { observeAppLanguage() } returns MutableStateFlow(AppLanguage.System)
        every { observeAppMeasurementSystem() } returns MutableStateFlow(AppMeasurementSystem.Metric)
        every { observeAppTheme() } returns MutableStateFlow(AppTheme.System)
        every { observeAppDynamicColor() } returns MutableStateFlow(AppDynamicColor.Disabled)

        viewModel = SettingsViewModel(
            features = features,
            getAppColorContrast = getAppColorContrast,
            getAppLanguage = getAppLanguage,
            getAppMeasurementSystem = getAppMeasurementSystem,
            getAppTheme = getAppTheme,
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeAppDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
            setAppColorContrast = setAppColorContrast,
            setAppDynamicColor = setAppDynamicColor,
            setAppLanguage = setAppLanguage,
            setAppMeasurementSystem = setAppMeasurementSystem,
            setAppTheme = setAppTheme,
        )
    }

    @Test
    fun `initial state reflects default settings for all options`() {
        assertThat(viewModel.state.value).isEqualTo(initialState(true))
    }

    @Test
    fun `state transitions from loading to loaded after initialization`() = runTest {
        assertThat(viewModel.state.value).isEqualTo(
            initialState(true)
                .updateAppColorContrast(AppColorContrast.StandardContrast)
                .updateAppLanguage(AppLanguage.System)
                .updateAppMeasurementSystem(AppMeasurementSystem.Metric)
                .updateAppTheme(AppTheme.System)
                .updateDynamicColor(AppDynamicColor.Disabled)
        )
    }


}