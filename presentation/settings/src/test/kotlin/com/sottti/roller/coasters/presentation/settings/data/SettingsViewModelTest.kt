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
import com.sottti.roller.coasters.presentation.settings.data.mapper.toPresentationModel
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.hideAppThemePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.showAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.DismissAppColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.DismissAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.LaunchAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.DismissAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.LaunchAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.DismissAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.LaunchAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.DismissAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.LaunchAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LoadUi
import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
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

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `initial loading state with dynamic color available`() = runTest {
        every { features.systemDynamicColorAvailable() } returns true

        val viewModel = createViewModel(
            features = features,
        )

        viewModel.state.test {
            assertThat(awaitItem()).isEqualTo(initialState(dynamicColorAvailable = true))
        }
    }

    @Test
    fun `initial loading state with dynamic color unavailable`() = runTest {
        every { features.systemDynamicColorAvailable() } returns false

        val viewModel = createViewModel(
            features = features,
        )

        viewModel.state.test {
            assertThat(awaitItem()).isEqualTo(initialState(dynamicColorAvailable = false))
        }
    }


    @Test
    fun `initial loaded state with dynamic color available`() = runTest {
        val dynamicColorAvailable = true
        every { features.systemDynamicColorAvailable() } returns dynamicColorAvailable
        every { observeAppColorContrast() } returns flowOf(AppColorContrast.System)
        every { observeAppDynamicColor() } returns flowOf(AppDynamicColor.Enabled)
        every { observeAppLanguage() } returns flowOf(AppLanguage.System)
        every { observeAppMeasurementSystem() } returns flowOf(AppMeasurementSystem.System)
        every { observeAppTheme() } returns flowOf(AppTheme.System)

        val viewModel = createViewModel(
            features = features,
            initialState = initialState(dynamicColorAvailable),
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeAppDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
        )

        viewModel.onAction(LoadUi)

        val expectedState = loadedState(dynamicColorAvailable)

        viewModel.state.test {
            var state: SettingsState
            do state = awaitItem() while (state != expectedState)
            assertThat(state).isEqualTo(expectedState)
        }
    }

    @Test
    fun `initial loaded state with dynamic color unavailable`() = runTest {
        val dynamicColorAvailable = false
        every { features.systemDynamicColorAvailable() } returns dynamicColorAvailable
        every { observeAppColorContrast() } returns flowOf(AppColorContrast.System)
        every { observeAppDynamicColor() } returns flowOf(AppDynamicColor.Enabled)
        every { observeAppLanguage() } returns flowOf(AppLanguage.System)
        every { observeAppMeasurementSystem() } returns flowOf(AppMeasurementSystem.System)
        every { observeAppTheme() } returns flowOf(AppTheme.System)

        val viewModel = createViewModel(
            features = features,
            initialState = initialState(dynamicColorAvailable),
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeAppDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
        )

        viewModel.onAction(LoadUi)

        val expectedState = loadedState(dynamicColorAvailable)

        viewModel.state.test {
            var state: SettingsState
            do state = awaitItem() while (state != expectedState)
            assertThat(state).isEqualTo(expectedState)
        }
    }

    @Test
    fun `shows app color contrast picker when dynamic color enabled`() = runTest {
        val colorContrastAvailable = true
        val dynamicColorAvailable = true
        val dynamicColorState = AppDynamicColor.Enabled
        val appColorContrast = AppColorContrast.System
        coEvery { getAppColorContrast() } returns appColorContrast
        every { features.systemColorContrastAvailable() } returns colorContrastAvailable

        val initialState = loadedState(
            dynamicColorAvailable = dynamicColorAvailable,
            dynamicColorState = dynamicColorState,
        )

        val viewModel = createViewModel(
            features = features,
            getAppColorContrast = getAppColorContrast,
            initialState = initialState,
        )

        viewModel.onAction(LaunchAppColorContrastPicker)

        val expectedState = initialState
            .showAppColorContrastPicker(
                selectedAppColorContrast = appColorContrast,
                appColorContrastAvailable = colorContrastAvailable,
            )

        viewModel.state.test {
            assertThat(awaitItem()).isEqualTo(expectedState)
        }
    }

    @Test
    fun `shows app color contrast picker when dynamic color disabled`() = runTest {
        val colorContrastAvailable = true
        val dynamicColorAvailable = true
        val dynamicColorState = AppDynamicColor.Disabled
        val appColorContrast = AppColorContrast.System
        coEvery { getAppColorContrast() } returns appColorContrast
        every { features.systemColorContrastAvailable() } returns colorContrastAvailable

        val initialState = loadedState(
            dynamicColorAvailable = dynamicColorAvailable,
            dynamicColorState = dynamicColorState,
        )

        val viewModel = createViewModel(
            features = features,
            getAppColorContrast = getAppColorContrast,
            initialState = initialState,
        )

        viewModel.onAction(LaunchAppColorContrastPicker)

        val expectedState = initialState
            .showAppColorContrastPicker(
                selectedAppColorContrast = appColorContrast,
                appColorContrastAvailable = colorContrastAvailable,
            )

        viewModel.state.test {
            val state = awaitItem()
            assertThat(state).isEqualTo(expectedState)
        }
    }

    @Test
    fun `shows app language picker`() = runTest {
        val appLanguage = AppLanguage.System
        coEvery { getAppLanguage() } returns appLanguage

        val initialState = loadedState()

        val viewModel = createViewModel(
            getAppLanguage = getAppLanguage,
            initialState = initialState,
        )

        viewModel.onAction(LaunchAppLanguagePicker)

        val expectedState = initialState.showAppLanguagePicker(appLanguage)

        viewModel.state.test {
            assertThat(awaitItem()).isEqualTo(expectedState)
        }
    }

    @Test
    fun `shows app measurement system picker`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.System
        coEvery { getAppMeasurementSystem() } returns appMeasurementSystem

        val initialState = loadedState()

        val viewModel = createViewModel(
            getAppMeasurementSystem = getAppMeasurementSystem,
            initialState = initialState,
        )

        viewModel.onAction(LaunchAppMeasurementSystemPicker)

        val expectedState = initialState
            .showAppMeasurementSystemPicker(appMeasurementSystem)

        viewModel.state.test {
            assertThat(awaitItem()).isEqualTo(expectedState)
        }
    }

    @Test
    fun `shows app theme picker`() = runTest {
        val appTheme = AppTheme.System
        val lightDarkSystemThemingAvailable = true
        coEvery { getAppTheme() } returns appTheme
        coEvery {
            features.lightDarkSystemThemingAvailable()
        } returns lightDarkSystemThemingAvailable

        val initialState = loadedState()

        val viewModel = createViewModel(
            features = features,
            getAppTheme = getAppTheme,
            initialState = initialState,
        )

        viewModel.onAction(LaunchAppThemePicker)

        val expectedState = initialState
            .showAppThemePicker(
                lightDarkAppThemingAvailable = lightDarkSystemThemingAvailable,
                selectedAppTheme = appTheme.toPresentationModel(selected = true),
            )

        viewModel.state.test {
            assertThat(awaitItem()).isEqualTo(expectedState)
        }
    }

    @Test
    fun `hides app color contrast picker`() = runTest {
        val colorContrastAvailable = true
        val appColorContrast = AppColorContrast.System

        val initialState = loadedState(
            dynamicColorState = AppDynamicColor.Disabled,
        )
            .showAppColorContrastPicker(
                selectedAppColorContrast = appColorContrast,
                appColorContrastAvailable = colorContrastAvailable,
            )

        val viewModel = createViewModel(
            initialState = initialState,
        )

        viewModel.onAction(DismissAppColorContrastPicker)

        val expectedState = initialState.hideAppColorContrastPicker()

        viewModel.state.test {
            val state = awaitItem()
            assertThat(state).isEqualTo(expectedState)
        }
    }

    @Test
    fun `hides app color contrast not available dialog`() = runTest {
        val colorContrastAvailable = true
        val appColorContrast = AppColorContrast.System

        val initialState = loadedState(
            dynamicColorState = AppDynamicColor.Enabled,
        )
            .showAppColorContrastPicker(
                selectedAppColorContrast = appColorContrast,
                appColorContrastAvailable = colorContrastAvailable,
            )

        val viewModel = createViewModel(
            initialState = initialState,
        )

        viewModel.onAction(DismissAppColorContrastNotAvailableMessage)

        val expectedState = initialState.hideAppColorContrastNotAvailableMessage()

        viewModel.state.test {
            val state = awaitItem()
            assertThat(state).isEqualTo(expectedState)
        }
    }

    @Test
    fun `hides app theme picker`() = runTest {
        val initialState = loadedState().showAppThemePicker(
            lightDarkAppThemingAvailable = true,
            selectedAppTheme = AppTheme.System.toPresentationModel(selected = true),
        )

        val viewModel = createViewModel(
            initialState = initialState,
        )

        viewModel.onAction(DismissAppThemePicker)

        val expectedState = initialState.hideAppThemePicker()

        viewModel.state.test {
            val state = awaitItem()
            assertThat(state).isEqualTo(expectedState)
        }
    }

    @Test
    fun `hides app language picker`() = runTest {
        val initialState = loadedState().showAppLanguagePicker(
            selectedAppLanguage = AppLanguage.System,
        )

        val viewModel = createViewModel(
            initialState = initialState,
        )

        viewModel.onAction(DismissAppLanguagePicker)

        val expectedState = initialState.hideAppLanguagePicker()

        viewModel.state.test {
            val state = awaitItem()
            assertThat(state).isEqualTo(expectedState)
        }
    }

    @Test
    fun `hides app measurement system picker`() = runTest {
        val initialState = loadedState().showAppMeasurementSystemPicker(
            selectedAppMeasurementSystem = AppMeasurementSystem.System,
        )

        val viewModel = createViewModel(initialState = initialState)

        viewModel.onAction(DismissAppMeasurementSystemPicker)

        val expectedState = initialState.hideAppMeasurementSystemPicker()

        viewModel.state.test {
            val state = awaitItem()
            assertThat(state).isEqualTo(expectedState)
        }
    }
}

private fun createViewModel(
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