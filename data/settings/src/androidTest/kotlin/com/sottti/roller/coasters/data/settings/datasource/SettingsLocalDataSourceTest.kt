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
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
internal class SettingsLocalDataSourceTest {

    private lateinit var context: Context
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var dataSource: SettingsLocalDataSource

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        dataStore = context.dataStore
        runTest { dataStore.edit { it.clear() } }
    }

    @After
    fun tearDown() {
        runTest { dataStore.edit { it.clear() } }
    }

    @Test
    fun testSetAppDynamicColorTrue() = runTest {
        val features =
            mockk<Features> { every { systemDynamicColorAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        dataSource.setAppDynamicColor(AppDynamicColor.Enabled)
        assertThat(dataSource.observeAppDynamicColor().first() == AppDynamicColor.Enabled).isTrue()
    }

    @Test
    fun testSetAppDynamicColorFalse() = runTest {
        val features =
            mockk<Features> { every { systemDynamicColorAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        dataSource.setAppDynamicColor(AppDynamicColor.Disabled)
        assertThat(dataSource.observeAppDynamicColor().first() == AppDynamicColor.Enabled).isFalse()
    }

    @Test
    fun testAppDynamicColorDefaultWhenMissing() = runTest {
        val features =
            mockk<Features> { every { systemDynamicColorAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        assertThat(dataSource.observeAppDynamicColor().first() == AppDynamicColor.Enabled).isTrue()
    }

    @Test
    fun testAppDynamicColorWhenFeatureUnavailable() = runTest {
        val features =
            mockk<Features> { every { systemDynamicColorAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        assertThat(dataSource.observeAppDynamicColor().first() == AppDynamicColor.Enabled).isFalse()
    }

    @Test
    fun testSetAppThemeDark() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkAppTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = themeManager,
                features = mockk(),
            )
        dataSource.setAppTheme(DarkAppTheme)
        assertThat(dataSource.observeAppTheme().first()).isEqualTo(DarkAppTheme)
    }

    @Test
    fun testSetAppThemeLight() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(LightAppTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = themeManager,
                features = mockk(),
            )
        dataSource.setAppTheme(LightAppTheme)
        assertThat(dataSource.observeAppTheme().first()).isEqualTo(LightAppTheme)
    }

    @Test
    fun testSetAppThemeSystem() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(SystemAppTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = themeManager,
                features = mockk(),
            )
        dataSource.setAppTheme(SystemAppTheme)
        assertThat(dataSource.observeAppTheme().first()).isEqualTo(SystemAppTheme)
    }

    @Test
    fun testThemeManagerCalledOnSetDark() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkAppTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = themeManager,
                features = mockk(),
            )
        dataSource.setAppTheme(DarkAppTheme)
        verify { themeManager.setTheme(DarkAppTheme) }
    }

    @Test
    fun testThemeDefaultWhenMissing() = runTest {
        val features =
            mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        assertThat(dataSource.getAppTheme()).isEqualTo(SystemAppTheme)
    }

    @Test
    fun testThemeFallbackForInvalidValue() = runTest {
        val features =
            mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        dataStore.edit { preferences -> preferences[appThemeKey] = "invalid_key" }
        assertThat(dataSource.getAppTheme()).isEqualTo(SystemAppTheme)
    }

    @Test
    fun testThemeWhenFeatureUnavailable() = runTest {
        val features =
            mockk<Features> { every { lightDarkSystemThemingAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        assertThat(dataSource.getAppTheme()).isEqualTo(LightAppTheme)
    }

    @Test
    fun testApplyStoredThemeWithStoredAppValue() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkAppTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = themeManager,
                features = mockk(),
            )
        dataSource.setAppTheme(DarkAppTheme)
        dataSource.applyStoredAppTheme()
        verify { themeManager.setTheme(DarkAppTheme) }
    }

    @Test
    fun testApplyStoredAppThemeWithDefault() = runTest {
        val features =
            mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        val themeManager = mockk<ThemeManager> { every { setTheme(SystemAppTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = themeManager,
                features = features,
            )
        dataStore.edit { it.clear() }
        dataSource.applyStoredAppTheme()
        verify { themeManager.setTheme(SystemAppTheme) }
    }

    @Test
    fun testSetAppColorContrastHigh() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.setAppColorContrast(HighContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(HighContrast)
    }

    @Test
    fun testSetAppColorContrastMedium() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.setAppColorContrast(MediumContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(MediumContrast)
    }

    @Test
    fun testSetAppColorContrastStandard() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.setAppColorContrast(StandardContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(StandardContrast)
    }

    @Test
    fun testSetAppColorContrastSystem() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.setAppColorContrast(SystemContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastDefaultWhenMissing() = runTest {
        val features =
            mockk<Features> { every { systemColorContrastAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        assertThat(dataSource.getAppColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastFallbackForInvalidValue() = runTest {
        val features =
            mockk<Features> { every { systemColorContrastAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        dataStore.edit { preferences -> preferences[appColorContrastKey] = "invalid_key" }
        assertThat(dataSource.getAppColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastWhenFeatureUnavailable() = runTest {
        val features =
            mockk<Features> { every { systemColorContrastAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = features,
            )
        assertThat(dataSource.getAppColorContrast()).isEqualTo(StandardContrast)
    }

    @Test
    fun testGetSystemColorContrast() = runTest {
        val systemColorContrastManager = mockk<SystemColorContrastManager> {
            every { systemColorContrast } returns SystemColorContrast.HighContrast
        }
        dataSource = SettingsLocalDataSource(
            activityLifecycleEmitter = mockk(),
            dataStore = dataStore,
            localeManager = mockk(),
            measurementSystemManager = mockk(),
            systemColorContrastManager = systemColorContrastManager,
            themeManager = mockk(),
            features = mockk(),
        )
        assertThat(dataSource.getSystemColorContrast()).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testGetSystemColorContrastManagerCall() = runTest {
        val systemColorContrastManager = mockk<SystemColorContrastManager> {
            every { systemColorContrast } returns SystemColorContrast.HighContrast
        }
        dataSource = SettingsLocalDataSource(
            activityLifecycleEmitter = mockk(),
            dataStore = dataStore,
            localeManager = mockk(),
            measurementSystemManager = mockk(),
            systemColorContrastManager = systemColorContrastManager,
            themeManager = mockk(),
            features = mockk(),
        )
        dataSource.getSystemColorContrast()
        verify { systemColorContrastManager.systemColorContrast }
    }

    @Test
    fun testSetAppLanguage() = runTest {
        val appLanguage = AppLanguage.EnglishGb
        val localeList = appLanguage.toLocaleList()
        val localeManager = mockk<LocaleManager> { every { setLocaleList(localeList) } just runs }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = localeManager,
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.setAppLanguage(appLanguage)
        verify { localeManager.setLocaleList(localeList) }
    }

    @Test
    fun testGetAppLanguage() = runTest {
        val localeManager = mockk<LocaleManager> { every { appLocale } returns Locale.UK }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = localeManager,
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        assertThat(dataSource.getAppLanguage()).isEqualTo(AppLanguage.EnglishGb)
    }

    @Test
    fun testGetAppLanguageManagerCall() = runTest {
        val localeManager = mockk<LocaleManager> { every { appLocale } returns Locale.UK }
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = localeManager,
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.getAppLanguage()
        verify { localeManager.appLocale }
    }

    @Test
    fun testSetAppMeasurementSystemMetric() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.setAppMeasurementSystem(Metric)
        assertThat(dataSource.observeAppMeasurementSystem().first()).isEqualTo(Metric)
    }

    @Test
    fun testSetAppMeasurementSystemImperialUk() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.setAppMeasurementSystem(ImperialUk)
        assertThat(dataSource.observeAppMeasurementSystem().first()).isEqualTo(ImperialUk)
    }

    @Test
    fun testSetAppMeasurementSystemSystem() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataSource.setAppMeasurementSystem(System)
        assertThat(dataSource.observeAppMeasurementSystem().first()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemDefaultWhenMissing() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        assertThat(dataSource.getAppMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemFallbackForInvalidValue() = runTest {
        dataSource =
            SettingsLocalDataSource(
                activityLifecycleEmitter = mockk(),
                dataStore = dataStore,
                localeManager = mockk(),
                measurementSystemManager = mockk(),
                systemColorContrastManager = mockk(),
                themeManager = mockk(),
                features = mockk(),
            )
        dataStore.edit { preferences -> preferences[appMeasurementSystemKey] = "invalid_key" }
        assertThat(dataSource.getAppMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testGetSystemMeasurementSystem() = runTest {
        val measurementSystemManager = mockk<MeasurementSystemManager> {
            every { systemMeasurementSystem } returns SystemMeasurementSystem.ImperialUs
        }
        dataSource = SettingsLocalDataSource(
            activityLifecycleEmitter = mockk(),
            dataStore = dataStore,
            localeManager = mockk(),
            measurementSystemManager = measurementSystemManager,
            systemColorContrastManager = mockk(),
            themeManager = mockk(),
            features = mockk(),
        )
        assertThat(
            dataSource.getSystemMeasurementSystem(),
        ).isEqualTo(SystemMeasurementSystem.ImperialUs)
    }

    @Test
    fun testGetSystemMeasurementSystemManagerCall() = runTest {
        val measurementSystemManager = mockk<MeasurementSystemManager> {
            every { systemMeasurementSystem } returns SystemMeasurementSystem.ImperialUs
        }
        dataSource = SettingsLocalDataSource(
            activityLifecycleEmitter = mockk(),
            dataStore = dataStore,
            localeManager = mockk(),
            measurementSystemManager = measurementSystemManager,
            systemColorContrastManager = mockk(),
            themeManager = mockk(),
            features = mockk()
        )
        dataSource.getSystemMeasurementSystem()
        verify { measurementSystemManager.systemMeasurementSystem }
    }
}