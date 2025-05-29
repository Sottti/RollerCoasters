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
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.model.DynamicColorCheckedState
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.LaunchAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.LaunchAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.LaunchAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.LaunchAppThemePicker
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {
    private lateinit var viewModel: SettingsViewModel

    private val features = mockk<Features>(relaxed = true)
    private val getAppTheme = mockk<GetAppTheme>()
    private val observeAppTheme = mockk<ObserveAppTheme>()
    private val setAppTheme = mockk<SetAppTheme>(relaxed = true)
    private val getAppLanguage = mockk<GetAppLanguage>()
    private val observeAppLanguage = mockk<ObserveAppLanguage>()
    private val setAppLanguage = mockk<SetAppLanguage>(relaxed = true)
    private val getAppMeasurementSystem = mockk<GetAppMeasurementSystem>()
    private val observeAppMeasurementSystem = mockk<ObserveAppMeasurementSystem>()
    private val setAppMeasurementSystem = mockk<SetAppMeasurementSystem>(relaxed = true)
    private val getAppColorContrast = mockk<GetAppColorContrast>()
    private val observeAppColorContrast = mockk<ObserveAppColorContrast>()
    private val setAppColorContrast = mockk<SetAppColorContrast>(relaxed = true)
    private val observeDynamicColor = mockk<ObserveAppDynamicColor>()
    private val setDynamicColor = mockk<SetAppDynamicColor>(relaxed = true)

    @Before
    fun setup() {
        every { features.lightDarkSystemThemingAvailable() } returns true
        every { features.systemDynamicColorAvailable() } returns true
        every { features.systemColorContrastAvailable() } returns true
        every { observeAppTheme() } returns MutableStateFlow(AppTheme.System)
        every { observeAppLanguage() } returns MutableStateFlow(AppLanguage.System)
        every { observeAppMeasurementSystem() } returns MutableStateFlow(AppMeasurementSystem.Metric)
        every { observeAppColorContrast() } returns MutableStateFlow(AppColorContrast.StandardContrast)
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
    fun `initial state contains correct default values`() {
        val state = viewModel.state.value
        assertThat(state.appTheme.listItem.selectedAppTheme)
            .isEqualTo(AppTheme.System.toPresentationModel(true))
        assertThat(state.appLanguage.listItem.selectedAppLanguage)
            .isEqualTo(AppLanguage.System.toPresentationModel(true))
        assertThat(state.appMeasurementSystem.listItem.selectedAppMeasurementSystem)
            .isEqualTo(AppMeasurementSystem.Metric.toPresentationModel(true))
        assertThat(state.appColorContrast.listItem.selectedAppColorContrast)
            .isEqualTo(AppColorContrast.StandardContrast.toPresentationModel(true))
        assertThat(state.dynamicColor?.checkedState).isEqualTo(DynamicColorCheckedState.Loading)
    }

    @Test
    fun `launchAppThemePicker updates picker`() {
        viewModel.onAction(LaunchAppThemePicker)
        val state = viewModel.state.value
        assertThat(state.appTheme.picker).isNotNull()
        assertThat(state.appTheme.picker?.appThemes?.any { it.selected }).isTrue()
    }

    @Test
    fun `launchAppLanguagePicker updates picker`() {
        viewModel.onAction(LaunchAppLanguagePicker)
        val state = viewModel.state.value
        assertThat(state.appLanguage.picker).isNotNull()
        assertThat(state.appLanguage.picker?.appLanguages?.any { it.selected }).isTrue()
    }

    @Test
    fun `launchAppMeasurementSystemPicker updates picker`() {
        viewModel.onAction(LaunchAppMeasurementSystemPicker)
        val state = viewModel.state.value
        assertThat(state.appMeasurementSystem.picker).isNotNull()
        assertThat(state.appMeasurementSystem.picker?.appMeasurementSystems?.any { it.selected }).isTrue()
    }

    @Test
    fun `launchAppColorContrastPicker shows not available message if dynamic color is enabled`() {
        every { observeDynamicColor() } returns MutableStateFlow(AppDynamicColor.Enabled)
        viewModel = copyWithUpdatedDynamicColorFlow()
        viewModel.onAction(LaunchAppColorContrastPicker)
        val state = viewModel.state.value
        assertThat(state.appColorContrast.notAvailableMessage).isNotNull()
    }

    private fun copyWithUpdatedDynamicColorFlow(): SettingsViewModel {
        return SettingsViewModel(
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
}