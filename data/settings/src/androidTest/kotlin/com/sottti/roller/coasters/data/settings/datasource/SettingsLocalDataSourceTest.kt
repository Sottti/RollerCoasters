package com.sottti.roller.coasters.data.settings.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.appColorContrastKey
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.appMeasurementSystemKey
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.appThemeKey
import com.sottti.roller.coasters.data.settings.managers.LocaleManager
import com.sottti.roller.coasters.data.settings.managers.MeasurementSystemManager
import com.sottti.roller.coasters.data.settings.managers.SystemColorContrastManager
import com.sottti.roller.coasters.data.settings.managers.ThemeManager
import com.sottti.roller.coasters.data.settings.mapper.toLocaleList
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.DarkAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.LightAppTheme
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme.SystemAppTheme
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkStatic
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
internal class SettingsLocalDataSourceTest {

    private lateinit var activityLifecycleEmitter: ActivityLifecycleEmitter
    private lateinit var context: Context
    private lateinit var dataSource: SettingsLocalDataSource
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var localeManager: LocaleManager

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        dataStore = context.dataStore
        activityLifecycleEmitter = mockk {
            every {
                activityCreatedFlow
            } returns MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }
        }
        localeManager = mockk()
        runTest { dataStore.edit { it.clear() } }
    }

    @After
    fun tearDown() {
        runTest { dataStore.edit { it.clear() } }
    }

    @Test
    fun testSetAppDynamicColorTrue() = runTest {
        val features = mockk<Features> { every { systemDynamicColorAvailable() } returns true }
        dataSource = createDataSource(features = features)
        dataSource.setAppDynamicColor(AppDynamicColor.Enabled)
        assertThat(dataSource.observeAppDynamicColor().first()).isEqualTo(AppDynamicColor.Enabled)
    }

    @Test
    fun testSetAppDynamicColorFalse() = runTest {
        val features = mockk<Features> { every { systemDynamicColorAvailable() } returns true }
        dataSource = createDataSource(features = features)
        dataSource.setAppDynamicColor(AppDynamicColor.Disabled)
        assertThat(dataSource.observeAppDynamicColor().first()).isEqualTo(AppDynamicColor.Disabled)
    }

    @Test
    fun testAppDynamicColorDefaultWhenMissing() = runTest {
        val features = mockk<Features> { every { systemDynamicColorAvailable() } returns true }
        dataSource = createDataSource(features = features)
        assertThat(dataSource.observeAppDynamicColor().first()).isEqualTo(AppDynamicColor.Enabled)
    }

    @Test
    fun testAppDynamicColorWhenFeatureUnavailable() = runTest {
        val features = mockk<Features> { every { systemDynamicColorAvailable() } returns false }
        dataSource = createDataSource(features = features)
        assertThat(dataSource.observeAppDynamicColor().first()).isEqualTo(AppDynamicColor.Disabled)
    }

    @Test
    fun testSetAppThemeDark() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkAppTheme) } just runs }
        dataSource = createDataSource(themeManager = themeManager)
        dataSource.setAppTheme(DarkAppTheme)
        assertThat(dataSource.observeAppTheme().first()).isEqualTo(DarkAppTheme)
    }

    @Test
    fun testSetAppThemeLight() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(LightAppTheme) } just runs }
        dataSource = createDataSource(themeManager = themeManager)
        dataSource.setAppTheme(LightAppTheme)
        assertThat(dataSource.observeAppTheme().first()).isEqualTo(LightAppTheme)
    }

    @Test
    fun testSetAppThemeSystem() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(SystemAppTheme) } just runs }
        dataSource = createDataSource(themeManager = themeManager)
        dataSource.setAppTheme(SystemAppTheme)
        assertThat(dataSource.observeAppTheme().first()).isEqualTo(SystemAppTheme)
    }

    @Test
    fun testThemeManagerCalledOnSetDark() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkAppTheme) } just runs }
        dataSource = createDataSource(themeManager = themeManager)
        dataSource.setAppTheme(DarkAppTheme)
        verify { themeManager.setTheme(DarkAppTheme) }
    }

    @Test
    fun testThemeDefaultWhenMissing() = runTest {
        val features = mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        dataSource = createDataSource(features = features)
        assertThat(dataSource.getAppTheme()).isEqualTo(SystemAppTheme)
    }

    @Test
    fun testThemeFallbackForInvalidValue() = runTest {
        val features = mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        dataSource = createDataSource(features = features)
        dataStore.edit { preferences -> preferences[appThemeKey] = "invalid_key" }
        assertThat(dataSource.getAppTheme()).isEqualTo(SystemAppTheme)
    }

    @Test
    fun testThemeWhenFeatureUnavailable() = runTest {
        val features = mockk<Features> { every { lightDarkSystemThemingAvailable() } returns false }
        dataSource = createDataSource(features = features)
        assertThat(dataSource.getAppTheme()).isEqualTo(LightAppTheme)
    }

    @Test
    fun testApplyStoredThemeWithStoredAppValue() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkAppTheme) } just runs }
        dataSource = createDataSource(themeManager = themeManager)
        dataSource.setAppTheme(DarkAppTheme)
        dataSource.applyStoredAppTheme()
        verify { themeManager.setTheme(DarkAppTheme) }
    }

    @Test
    fun testApplyStoredAppThemeWithDefault() = runTest {
        val features = mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        val themeManager = mockk<ThemeManager> { every { setTheme(SystemAppTheme) } just runs }
        dataSource = createDataSource(features = features, themeManager = themeManager)
        dataStore.edit { it.clear() }
        dataSource.applyStoredAppTheme()
        verify { themeManager.setTheme(SystemAppTheme) }
    }

    @Test
    fun testSetAppColorContrastHigh() = runTest {
        dataSource = createDataSource()
        dataSource.setAppColorContrast(HighContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(HighContrast)
    }

    @Test
    fun testSetAppColorContrastMedium() = runTest {
        dataSource = createDataSource()
        dataSource.setAppColorContrast(MediumContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(MediumContrast)
    }

    @Test
    fun testSetAppColorContrastStandard() = runTest {
        dataSource = createDataSource()
        dataSource.setAppColorContrast(StandardContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(StandardContrast)
    }

    @Test
    fun testSetAppColorContrastSystem() = runTest {
        dataSource = createDataSource()
        dataSource.setAppColorContrast(SystemContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastDefaultWhenMissing() = runTest {
        val features = mockk<Features> { every { systemColorContrastAvailable() } returns true }
        dataSource = createDataSource(features = features)
        assertThat(dataSource.getAppColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastFallbackForInvalidValue() = runTest {
        val features = mockk<Features> { every { systemColorContrastAvailable() } returns true }
        dataSource = createDataSource(features = features)
        dataStore.edit { preferences -> preferences[appColorContrastKey] = "invalid_key" }
        assertThat(dataSource.getAppColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastWhenFeatureUnavailable() = runTest {
        val features = mockk<Features> { every { systemColorContrastAvailable() } returns false }
        dataSource = createDataSource(features = features)
        assertThat(dataSource.getAppColorContrast()).isEqualTo(StandardContrast)
    }

    @Test
    fun testGetSystemColorContrast() = runTest {
        val systemColorContrastManager = mockk<SystemColorContrastManager> {
            every { systemColorContrast } returns SystemColorContrast.HighContrast
        }
        dataSource = createDataSource(systemColorContrastManager = systemColorContrastManager)
        assertThat(dataSource.getSystemColorContrast()).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testGetSystemColorContrastManagerCall() = runTest {
        val systemColorContrastManager = mockk<SystemColorContrastManager> {
            every { systemColorContrast } returns SystemColorContrast.HighContrast
        }
        dataSource = createDataSource(systemColorContrastManager = systemColorContrastManager)
        dataSource.getSystemColorContrast()
        verify { systemColorContrastManager.systemColorContrast }
    }

    @Test
    fun testSetAppLanguageCallsLocaleManager() = runTest {
        val appLanguage = AppLanguage.EnglishGb
        val localeList = appLanguage.toLocaleList()
        val localeManager = mockk<LocaleManager> { every { setLocaleList(localeList) } just runs }
        dataSource = createDataSource(localeManager = localeManager)
        dataSource.setAppLanguage(appLanguage)
        verify { localeManager.setLocaleList(localeList) }
    }

    @Test
    fun testGetAppLanguageReturnsMappedLocale() = runTest {
        val localeManager = mockk<LocaleManager> { every { appLocale } returns Locale.UK }
        dataSource = createDataSource(localeManager = localeManager)
        assertThat(dataSource.getAppLanguage()).isEqualTo(AppLanguage.EnglishGb)
    }

    @Test
    fun testObserveAppLanguageEmitsInitialValue() = runTest {
        val localeManager = mockk<LocaleManager> { every { appLocale } returns Locale("gl", "ES") }
        dataSource = createDataSource(localeManager = localeManager)
        val initialValue = dataSource.observeAppLanguage().first()
        assertThat(initialValue).isEqualTo(AppLanguage.Galician)
    }

    @Test
    fun testObserveAppLanguageEmitsDistinctValues() = runTest {
        val localeManager = mockk<LocaleManager> {
            every { appLocale } returnsMany listOf(Locale.UK, Locale.UK, Locale("es", "ES"))
        }
        val activityFlow = MutableSharedFlow<Unit>(replay = 0)
        val activityLifecycleEmitter = mockk<ActivityLifecycleEmitter> {
            every { activityCreatedFlow } returns activityFlow
        }
        dataSource = createDataSource(
            activityLifecycleEmitter = activityLifecycleEmitter,
            localeManager = localeManager,
        )

        val emittedValues = mutableListOf<AppLanguage>()
        val job = launch {
            dataSource.observeAppLanguage()
                .take(2)
                .toList(emittedValues)
        }

        activityFlow.emit(Unit) // EnglishGb
        advanceUntilIdle()

        activityFlow.emit(Unit) // EnglishGb (duplicate, filtered out by distinctUntilChanged)
        advanceUntilIdle()

        activityFlow.emit(Unit) // SpanishSpain
        advanceUntilIdle()

        // Wait for the flow to collect the second distinct item
        job.join()

        assertThat(emittedValues)
            .containsExactly(AppLanguage.EnglishGb, AppLanguage.SpanishSpain)
            .inOrder()
    }

    @Test
    fun testSetAppMeasurementSystemMetric() = runTest {
        dataSource = createDataSource()
        dataSource.setAppMeasurementSystem(Metric)
        assertThat(dataSource.observeAppMeasurementSystem().first()).isEqualTo(Metric)
    }

    @Test
    fun testSetAppMeasurementSystemImperialUk() = runTest {
        dataSource = createDataSource()
        dataSource.setAppMeasurementSystem(ImperialUk)
        assertThat(dataSource.observeAppMeasurementSystem().first()).isEqualTo(ImperialUk)
    }

    @Test
    fun testSetAppMeasurementSystemSystem() = runTest {
        dataSource = createDataSource()
        dataSource.setAppMeasurementSystem(System)
        assertThat(dataSource.observeAppMeasurementSystem().first()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemDefaultWhenMissing() = runTest {
        dataSource = createDataSource()
        assertThat(dataSource.getAppMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemFallbackForInvalidValue() = runTest {
        dataSource = createDataSource()
        dataStore.edit { preferences -> preferences[appMeasurementSystemKey] = "invalid_key" }
        assertThat(dataSource.getAppMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testGetSystemMeasurementSystem() = runTest {
        val measurementSystemManager = mockk<MeasurementSystemManager> {
            every { systemMeasurementSystem } returns SystemMeasurementSystem.ImperialUs
        }
        dataSource = createDataSource(measurementSystemManager = measurementSystemManager)
        assertThat(dataSource.getSystemMeasurementSystem())
            .isEqualTo(SystemMeasurementSystem.ImperialUs)
    }

    @Test
    fun testGetSystemMeasurementSystemManagerCall() = runTest {
        val measurementSystemManager = mockk<MeasurementSystemManager> {
            every { systemMeasurementSystem } returns SystemMeasurementSystem.ImperialUs
        }
        dataSource = createDataSource(measurementSystemManager = measurementSystemManager)
        dataSource.getSystemMeasurementSystem()
        verify { measurementSystemManager.systemMeasurementSystem }
    }

    @Test
    fun testObserveSystemLocaleEmitsInitialValue() = runTest {
        val activityFlow = MutableSharedFlow<Unit>(replay = 0)
        val activityLifecycleEmitter = mockk<ActivityLifecycleEmitter> {
            every { activityCreatedFlow } returns activityFlow
        }
        mockkStatic(Locale::class)
        every { Locale.getDefault() } returns Locale.FRANCE

        dataSource = createDataSource(activityLifecycleEmitter = activityLifecycleEmitter)

        val initialValue = dataSource.observeSystemLocale().first()
        assertThat(initialValue).isEqualTo(Locale.FRANCE)

        unmockkStatic(Locale::class)
    }

    @Test
    fun testObserveSystemLocaleEmitsDistinctValues() = runTest {
        val activityFlow = MutableSharedFlow<Unit>(replay = 0)
        val activityLifecycleEmitter = mockk<ActivityLifecycleEmitter> {
            every { activityCreatedFlow } returns activityFlow
        }

        mockkStatic(Locale::class)
        every { Locale.getDefault() } returnsMany listOf(
            Locale.FRANCE,
            Locale.FRANCE,
            Locale.FRANCE,
            Locale.GERMANY,
        )

        dataSource = createDataSource(activityLifecycleEmitter = activityLifecycleEmitter)

        val emittedLocales = mutableListOf<Locale>()
        val job = launch {
            dataSource.observeSystemLocale()
                .take(2)
                .collect { emittedLocales.add(it) }
        }

        advanceUntilIdle()
        activityFlow.emit(Unit)
        advanceUntilIdle()
        activityFlow.emit(Unit)
        advanceUntilIdle()
        activityFlow.emit(Unit)
        advanceUntilIdle()

        job.join()

        assertThat(emittedLocales).containsExactly(Locale.FRANCE, Locale.GERMANY).inOrder()

        unmockkStatic(Locale::class)
    }

    private fun createDataSource(
        activityLifecycleEmitter: ActivityLifecycleEmitter = this.activityLifecycleEmitter,
        localeManager: LocaleManager = this.localeManager,
        measurementSystemManager: MeasurementSystemManager = mockk(),
        systemColorContrastManager: SystemColorContrastManager = mockk(),
        themeManager: ThemeManager = mockk(),
        features: Features = mockk(),
    ) = SettingsLocalDataSource(
        activityLifecycleEmitter = activityLifecycleEmitter,
        dataStore = dataStore,
        localeManager = localeManager,
        measurementSystemManager = measurementSystemManager,
        systemColorContrastManager = systemColorContrastManager,
        themeManager = themeManager,
        features = features,
    )
}