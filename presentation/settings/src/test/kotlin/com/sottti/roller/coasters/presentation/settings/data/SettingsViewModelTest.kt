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
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.LaunchAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.LaunchAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.LaunchAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.LaunchAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.DynamicColorCheckedChange
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

    private val features = mockk<Features>(relaxed = true)
    private val getAppColorContrast = mockk<GetAppColorContrast>()
    private val getAppLanguage = mockk<GetAppLanguage>()
    private val getAppMeasurementSystem = mockk<GetAppMeasurementSystem>()
    private val getAppTheme = mockk<GetAppTheme>()
    private val observeAppColorContrast = mockk<ObserveAppColorContrast>()
    private val observeAppLanguage = mockk<ObserveAppLanguage>()
    private val observeAppMeasurementSystem = mockk<ObserveAppMeasurementSystem>()
    private val observeAppTheme = mockk<ObserveAppTheme>()
    private val observeDynamicColor = mockk<ObserveAppDynamicColor>()
    private val setAppColorContrast = mockk<SetAppColorContrast>(relaxed = true)
    private val setAppLanguage = mockk<SetAppLanguage>(relaxed = true)
    private val setAppMeasurementSystem = mockk<SetAppMeasurementSystem>(relaxed = true)
    private val setAppTheme = mockk<SetAppTheme>(relaxed = true)
    private val setDynamicColor = mockk<SetAppDynamicColor>(relaxed = true)

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
        every { observeDynamicColor() } returns MutableStateFlow(AppDynamicColor.Disabled)

        viewModel = SettingsViewModel(
            features = features,
            getAppColorContrast = getAppColorContrast,
            getAppLanguage = getAppLanguage,
            getAppMeasurementSystem = getAppMeasurementSystem,
            getAppTheme = getAppTheme,
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
            setAppColorContrast = setAppColorContrast,
            setAppDynamicColor = setDynamicColor,
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
    fun `state transitions from loading to loaded after initialization`() = runTest{
        val features = mockk<Features>()
        val getAppColorContrast = mockk<GetAppColorContrast>()
        val getAppLanguage = mockk<GetAppLanguage>()
        val getAppMeasurementSystem = mockk<GetAppMeasurementSystem>()
        val getAppTheme = mockk<GetAppTheme>()
        val observeAppColorContrast = mockk<ObserveAppColorContrast>()
        val observeAppDynamicColor = mockk<ObserveAppDynamicColor>()
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeAppMeasurementSystem = mockk<ObserveAppMeasurementSystem>()
        val observeAppTheme = mockk<ObserveAppTheme>()
        val setAppColorContrast = mockk<SetAppColorContrast>()
        val setAppDynamicColor = mockk<SetAppDynamicColor>()
        val setAppLanguage = mockk<SetAppLanguage>()
        val setAppMeasurementSystem = mockk<SetAppMeasurementSystem>()
        val setAppTheme = mockk<SetAppTheme>()

        // Explicit stubbings for feature flags
        every { features.lightDarkSystemThemingAvailable() } returns true
        every { features.systemDynamicColorAvailable() } returns true
        every { features.systemColorContrastAvailable() } returns true

        // Observe flows
        every { observeAppColorContrast() } returns MutableStateFlow(AppColorContrast.StandardContrast)
        every { observeAppDynamicColor() } returns MutableStateFlow(AppDynamicColor.Disabled)
        every { observeAppLanguage() } returns MutableStateFlow(AppLanguage.System)
        every { observeAppMeasurementSystem() } returns MutableStateFlow(AppMeasurementSystem.Metric)
        every { observeAppTheme() } returns MutableStateFlow(AppTheme.System)

        // Get use case calls
        coEvery { getAppTheme() } returns AppTheme.System
        coEvery { getAppLanguage() } returns AppLanguage.System
        coEvery { getAppMeasurementSystem() } returns AppMeasurementSystem.Metric
        coEvery { getAppColorContrast() } returns AppColorContrast.StandardContrast

        // Set use cases: if not used in this test, we don’t stub

        val viewModel = SettingsViewModel(
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

        advanceUntilIdle()


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