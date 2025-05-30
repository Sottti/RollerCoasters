package com.sottti.roller.coasters.presentation.settings.data

import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.GetAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.SetAppColorContrast
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveAppDynamicColor
import com.sottti.roller.coasters.domain.settings.usecase.language.GetAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.SetAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.SetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.theme.GetAppTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.ObserveAppTheme
import com.sottti.roller.coasters.domain.settings.usecase.theme.SetAppTheme
import com.sottti.roller.coasters.presentation.settings.data.mapper.toDomain
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
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.data.reducer.updateAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.AppColorContrastPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.ConfirmColorContrastPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.DismissAppColorContrastNotAvailableMessage
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.DismissAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppColorContrastActions.LaunchAppColorContrastPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.AppLanguagePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.ConfirmAppLanguagePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.DismissAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppLanguageActions.LaunchAppLanguagePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.AppMeasurementSystemPickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.ConfirmAppMeasurementSystemPickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.DismissAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppMeasurementSystemActions.LaunchAppMeasurementSystemPicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.AppThemePickerSelectionChange
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.ConfirmAppThemePickerSelection
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.DismissAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.AppThemeActions.LaunchAppThemePicker
import com.sottti.roller.coasters.presentation.settings.model.SettingsAction.LoadUi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SettingsViewModelTest {

    @Test
    fun `initial loading state with dynamic color available`() = runTest {
        val features = mockk<Features>()
        every { features.systemDynamicColorAvailable() } returns true
        val viewModel = createViewModel(features = features)
        viewModel.assertHasState(loadingState(dynamicColorAvailable = true))
    }


    @Test
    fun `initial loading state with dynamic color unavailable`() = runTest {
        val features = mockk<Features>()
        every { features.systemDynamicColorAvailable() } returns false
        val viewModel = createViewModel(features = features)
        viewModel.assertHasState(loadingState(dynamicColorAvailable = false))
    }


    @Test
    fun `initial loaded state with dynamic color available`() = runTest {
        val features = mockk<Features>()
        val dynamicColorAvailable = true
        val observeAppColorContrast = mockk<ObserveAppColorContrast>()
        val observeAppDynamicColor = mockk<ObserveAppDynamicColor>()
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeAppMeasurementSystem = mockk<ObserveAppMeasurementSystem>()
        val observeAppTheme = mockk<ObserveAppTheme>()
        every { features.systemDynamicColorAvailable() } returns dynamicColorAvailable
        every { observeAppColorContrast() } returns flowOf(AppColorContrast.System)
        every { observeAppDynamicColor() } returns flowOf(AppDynamicColor.Enabled)
        every { observeAppLanguage() } returns flowOf(AppLanguage.System)
        every { observeAppMeasurementSystem() } returns flowOf(System)
        every { observeAppTheme() } returns flowOf(AppTheme.System)
        val viewModel = createViewModel(
            features = features,
            initialState = loadingState(dynamicColorAvailable),
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeAppDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
        )
        viewModel.onAction(LoadUi)
        val expectedState = loadedState(dynamicColorAvailable)
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `initial loaded state with dynamic color unavailable`() = runTest {
        val features = mockk<Features>()
        val dynamicColorAvailable = false
        val observeAppColorContrast = mockk<ObserveAppColorContrast>()
        val observeAppDynamicColor = mockk<ObserveAppDynamicColor>()
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeAppMeasurementSystem = mockk<ObserveAppMeasurementSystem>()
        val observeAppTheme = mockk<ObserveAppTheme>()
        every { features.systemDynamicColorAvailable() } returns dynamicColorAvailable
        every { observeAppColorContrast() } returns flowOf(AppColorContrast.System)
        every { observeAppDynamicColor() } returns flowOf(AppDynamicColor.Enabled)
        every { observeAppLanguage() } returns flowOf(AppLanguage.System)
        every { observeAppMeasurementSystem() } returns flowOf(System)
        every { observeAppTheme() } returns flowOf(AppTheme.System)
        val viewModel = createViewModel(
            features = features,
            initialState = loadingState(dynamicColorAvailable),
            observeAppColorContrast = observeAppColorContrast,
            observeAppDynamicColor = observeAppDynamicColor,
            observeAppLanguage = observeAppLanguage,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            observeAppTheme = observeAppTheme,
        )
        viewModel.onAction(LoadUi)
        val expectedState = loadedState(dynamicColorAvailable)
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `shows app theme picker`() = runTest {
        val getAppTheme = mockk<GetAppTheme>()
        val features = mockk<Features>()
        val appTheme = AppTheme.System
        val lightDarkSystemThemingAvailable = true
        coEvery { getAppTheme() } returns appTheme
        coEvery { features.lightDarkSystemThemingAvailable() } returns lightDarkSystemThemingAvailable
        val initialState = loadedState()
        val viewModel = createViewModel(
            features = features,
            getAppTheme = getAppTheme,
            initialState = initialState,
        )
        viewModel.onAction(LaunchAppThemePicker)
        val expectedState = initialState.showAppThemePicker(
            lightDarkAppThemingAvailable = lightDarkSystemThemingAvailable,
            selectedAppTheme = appTheme.toPresentationModel(selected = true),
        )
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `shows app color contrast picker when dynamic color enabled`() = runTest {
        val features = mockk<Features>()
        val colorContrastAvailable = true
        val dynamicColorAvailable = true
        val dynamicColorState = AppDynamicColor.Enabled
        val appColorContrast = AppColorContrast.System
        val getAppColorContrast = mockk<GetAppColorContrast>()
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
        val expectedState = initialState.showAppColorContrastPicker(
            selectedAppColorContrast = appColorContrast,
            appColorContrastAvailable = colorContrastAvailable,
        )
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `shows app color contrast picker when dynamic color disabled`() = runTest {
        val getAppColorContrast = mockk<GetAppColorContrast>()
        val features = mockk<Features>()
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
        val expectedState = initialState.showAppColorContrastPicker(
            selectedAppColorContrast = appColorContrast,
            appColorContrastAvailable = colorContrastAvailable,
        )
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `shows app language picker`() = runTest {
        val getAppLanguage = mockk<GetAppLanguage>()
        val appLanguage = AppLanguage.System
        coEvery { getAppLanguage() } returns appLanguage
        val initialState = loadedState()
        val viewModel = createViewModel(
            getAppLanguage = getAppLanguage,
            initialState = initialState,
        )
        viewModel.onAction(LaunchAppLanguagePicker)
        val expectedState = initialState.showAppLanguagePicker(appLanguage)
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `shows app measurement system picker`() = runTest {
        val getAppMeasurementSystem = mockk<GetAppMeasurementSystem>()
        val appMeasurementSystem = System
        coEvery { getAppMeasurementSystem() } returns appMeasurementSystem
        val initialState = loadedState()
        val viewModel = createViewModel(
            getAppMeasurementSystem = getAppMeasurementSystem,
            initialState = initialState,
        )
        viewModel.onAction(LaunchAppMeasurementSystemPicker)
        val expectedState = initialState.showAppMeasurementSystemPicker(appMeasurementSystem)
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `hides app theme picker`() = runTest {
        val initialState = loadedState().showAppThemePicker(
            lightDarkAppThemingAvailable = true,
            selectedAppTheme = AppTheme.System.toPresentationModel(selected = true),
        )
        val viewModel = createViewModel(initialState = initialState)
        viewModel.onAction(DismissAppThemePicker)
        val expectedState = initialState.hideAppThemePicker()
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `hides app color contrast picker`() = runTest {
        val colorContrastAvailable = true
        val appColorContrast = AppColorContrast.System
        val initialState = loadedState(
            dynamicColorState = AppDynamicColor.Disabled,
        ).showAppColorContrastPicker(
            selectedAppColorContrast = appColorContrast,
            appColorContrastAvailable = colorContrastAvailable,
        )
        val viewModel = createViewModel(initialState = initialState)
        viewModel.onAction(DismissAppColorContrastPicker)
        val expectedState = initialState.hideAppColorContrastPicker()
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `hides app color contrast not available dialog`() = runTest {
        val colorContrastAvailable = true
        val appColorContrast = AppColorContrast.System
        val initialState = loadedState(
            dynamicColorState = AppDynamicColor.Enabled,
        ).showAppColorContrastPicker(
            selectedAppColorContrast = appColorContrast,
            appColorContrastAvailable = colorContrastAvailable,
        )
        val viewModel = createViewModel(initialState = initialState)
        viewModel.onAction(DismissAppColorContrastNotAvailableMessage)
        val expectedState = initialState.hideAppColorContrastNotAvailableMessage()
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `hides app language picker`() = runTest {
        val initialState = loadedState().showAppLanguagePicker(
            selectedAppLanguage = AppLanguage.System,
        )
        val viewModel = createViewModel(initialState = initialState)
        viewModel.onAction(DismissAppLanguagePicker)
        val expectedState = initialState.hideAppLanguagePicker()
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `hides app measurement system picker`() = runTest {
        val initialState = loadedState().showAppMeasurementSystemPicker(
            selectedAppMeasurementSystem = System,
        )
        val viewModel = createViewModel(initialState = initialState)
        viewModel.onAction(DismissAppMeasurementSystemPicker)
        val expectedState = initialState.hideAppMeasurementSystemPicker()
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `app theme picker selection change`() = runTest {
        val features = mockk<Features>()
        val lightDarkSystemThemingAvailable = true
        val initialSelectedAppTheme = AppTheme.System.toPresentationModel(selected = true)
        val newSelectedTheme = AppTheme.LightAppTheme.toPresentationModel(selected = true)
        val initialState = loadedState().showAppThemePicker(
            lightDarkAppThemingAvailable = lightDarkSystemThemingAvailable,
            selectedAppTheme = initialSelectedAppTheme,
        )
        val expectedState = initialState.updateAppThemePicker(
            lightDarkAppThemingAvailable = lightDarkSystemThemingAvailable,
            selectedAppTheme = newSelectedTheme,
        )
        coEvery { features.lightDarkSystemThemingAvailable() } returns lightDarkSystemThemingAvailable
        val viewModel = createViewModel(
            initialState = initialState,
            features = features,
        )
        viewModel.onAction(AppThemePickerSelectionChange(newSelectedTheme))
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `app color contrast picker selection change`() = runTest {
        val features = mockk<Features>()
        val appColorContrastAvailable = true
        val initialSelectedColorContrast = AppColorContrast.System
        val newSelectedColorContrast =
            AppColorContrast.HighContrast.toPresentationModel(selected = true)
        val initialState = loadedState().showAppColorContrastPicker(
            selectedAppColorContrast = initialSelectedColorContrast,
            appColorContrastAvailable = appColorContrastAvailable,
        )
        val expectedState = initialState.updateAppColorContrastPicker(
            appColorContrastAvailable = appColorContrastAvailable,
            selectedAppColorContrast = newSelectedColorContrast,
        )
        coEvery { features.systemColorContrastAvailable() } returns appColorContrastAvailable
        val viewModel = createViewModel(
            features = features,
            initialState = initialState,
        )
        viewModel.onAction(AppColorContrastPickerSelectionChange(newSelectedColorContrast))
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `app language picker selection change`() = runTest {
        val initialSelectedAppLanguage = AppLanguage.System
        val newSelectedLanguage = AppLanguage.EnglishGb.toPresentationModel(selected = true)
        val initialState = loadedState().showAppLanguagePicker(
            selectedAppLanguage = initialSelectedAppLanguage,
        )
        val expectedState = initialState.updateAppLanguagePicker(
            selectedAppLanguage = newSelectedLanguage,
        )
        val viewModel = createViewModel(initialState = initialState)
        viewModel.onAction(AppLanguagePickerSelectionChange(newSelectedLanguage))
        viewModel.assertHasState(expectedState)
    }


    @Test
    fun `app measurement system picker selection change`() = runTest {
        val initialSelectedMeasurementSystem = System
        val newSelectedMeasurementSystem = ImperialUk.toPresentationModel(selected = true)
        val initialState = loadedState().showAppMeasurementSystemPicker(
            selectedAppMeasurementSystem = initialSelectedMeasurementSystem,
        )
        val expectedState = initialState.updateAppMeasurementSystemPicker(
            selectedAppMeasurementSystem = newSelectedMeasurementSystem,
        )
        val viewModel = createViewModel(initialState = initialState)
        viewModel.onAction(AppMeasurementSystemPickerSelectionChange(newSelectedMeasurementSystem))
        viewModel.assertHasState(expectedState)
    }

    @Test
    fun `app theme picker selection confirmation`() = runTest {
        val setAppTheme = mockk<SetAppTheme>()
        val lightDarkSystemThemingAvailable = true
        val selectedAppTheme = AppTheme.System.toPresentationModel(selected = true)
        val initialState = loadedState().showAppThemePicker(
            lightDarkAppThemingAvailable = lightDarkSystemThemingAvailable,
            selectedAppTheme = selectedAppTheme,
        )
        val expectedState = initialState.hideAppThemePicker()
        coEvery { setAppTheme(selectedAppTheme.toDomain()) } returns Unit
        val viewModel = createViewModel(
            initialState = initialState,
            setAppTheme = setAppTheme,
        )
        viewModel.onAction(ConfirmAppThemePickerSelection(selectedAppTheme))
        viewModel.assertHasState(expectedState)
    }

    @Test
    fun `app language picker selection confirmation`() = runTest {
        val setAppLanguage = mockk<SetAppLanguage>()
        val selectedAppLanguage = AppLanguage.System.toPresentationModel(selected = true)
        val initialState = loadedState().showAppLanguagePicker(
            selectedAppLanguage = AppLanguage.System,
        )
        val expectedState = initialState.hideAppLanguagePicker()
        coEvery { setAppLanguage(selectedAppLanguage.toDomain()) } returns Unit
        val viewModel = createViewModel(
            initialState = initialState,
            setAppLanguage = setAppLanguage,
        )
        viewModel.onAction(ConfirmAppLanguagePickerSelection(selectedAppLanguage))
        viewModel.assertHasState(expectedState)
    }

    @Test
    fun `app measurement system picker selection confirmation`() = runTest {
        val setAppMeasurementSystem = mockk<SetAppMeasurementSystem>()
        val selectedAppMeasurementSystem = System.toPresentationModel(selected = true)
        val initialState = loadedState().showAppMeasurementSystemPicker(
            selectedAppMeasurementSystem = System,
        )
        val expectedState = initialState.hideAppMeasurementSystemPicker()
        coEvery { setAppMeasurementSystem(selectedAppMeasurementSystem.toDomain()) } returns Unit
        val viewModel = createViewModel(
            initialState = initialState,
            setAppMeasurementSystem = setAppMeasurementSystem,
        )
        viewModel.onAction(ConfirmAppMeasurementSystemPickerSelection(selectedAppMeasurementSystem))
        viewModel.assertHasState(expectedState)
    }

    @Test
    fun `app color contrast picker selection confirmation`() = runTest {
        val setAppColorContrast = mockk<SetAppColorContrast>()
        val selectedAppColorContrast = AppColorContrast.System.toPresentationModel(selected = true)
        val initialState = loadedState().showAppColorContrastPicker(
            selectedAppColorContrast = AppColorContrast.System,
            appColorContrastAvailable = true,
        )
        val expectedState = initialState.hideAppColorContrastPicker()
        coEvery { setAppColorContrast(selectedAppColorContrast.toDomain()) } returns Unit
        val viewModel = createViewModel(
            initialState = initialState,
            setAppColorContrast = setAppColorContrast,
        )
        viewModel.onAction(ConfirmColorContrastPickerSelection(selectedAppColorContrast))
        viewModel.assertHasState(expectedState)
    }
}