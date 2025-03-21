package com.sottti.roller.coasters.data.settings.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.appColorContrastKey
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.measurementSystemKey
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.themeKey
import com.sottti.roller.coasters.data.settings.mappers.toLocaleList
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.Language
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem.System
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.Theme.DarkTheme
import com.sottti.roller.coasters.domain.settings.model.theme.Theme.LightTheme
import com.sottti.roller.coasters.domain.settings.model.theme.Theme.SystemTheme
import com.sottti.roller.coasters.utils.device.managers.LocaleManager
import com.sottti.roller.coasters.utils.device.managers.MeasurementSystemManager
import com.sottti.roller.coasters.utils.device.managers.SystemColorContrastManager
import com.sottti.roller.coasters.utils.device.managers.ThemeManager
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
        context = ApplicationProvider.getApplicationContext<Context>()
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
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        dataSource.setAppDynamicColor(AppDynamicColor(true))
        assertThat(dataSource.observeAppDynamicColor().first().enabled).isTrue()
    }

    @Test
    fun testSetAppDynamicColorFalse() = runTest {
        val features =
            mockk<Features> { every { systemDynamicColorAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        dataSource.setAppDynamicColor(AppDynamicColor(false))
        assertThat(dataSource.observeAppDynamicColor().first().enabled).isFalse()
    }

    @Test
    fun testAppDynamicColorDefaultWhenMissing() = runTest {
        val features =
            mockk<Features> { every { systemDynamicColorAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        assertThat(dataSource.observeAppDynamicColor().first().enabled).isTrue()
    }

    @Test
    fun testAppDynamicColorWhenFeatureUnavailable() = runTest {
        val features =
            mockk<Features> { every { systemDynamicColorAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        assertThat(dataSource.observeAppDynamicColor().first().enabled).isFalse()
    }

    @Test
    fun testSetThemeDark() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(DarkTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(DarkTheme)
    }

    @Test
    fun testSetThemeLight() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(LightTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(LightTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(LightTheme)
    }

    @Test
    fun testSetThemeSystem() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(SystemTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(SystemTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeManagerCalledOnSetDark() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(DarkTheme)
        verify { themeManager.setTheme(DarkTheme) }
    }

    @Test
    fun testThemeDefaultWhenMissing() = runTest {
        val features =
            mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeFallbackForInvalidValue() = runTest {
        val features =
            mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        dataStore.edit { preferences -> preferences[themeKey] = "invalid_key" }
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeWhenFeatureUnavailable() = runTest {
        val features =
            mockk<Features> { every { lightDarkSystemThemingAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        assertThat(dataSource.getTheme()).isEqualTo(LightTheme)
    }

    @Test
    fun testApplyStoredThemeWithStoredValue() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(DarkTheme)
        dataSource.applyStoredTheme()
        verify { themeManager.setTheme(DarkTheme) }
    }

    @Test
    fun testApplyStoredThemeWithDefault() = runTest {
        val features =
            mockk<Features> { every { lightDarkSystemThemingAvailable() } returns true }
        val themeManager = mockk<ThemeManager> { every { setTheme(SystemTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), themeManager, features)
        dataStore.edit { it.clear() }
        dataSource.applyStoredTheme()
        verify { themeManager.setTheme(SystemTheme) }
    }

    @Test
    fun testSetAppColorContrastHigh() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        dataSource.setAppColorContrast(HighContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(HighContrast)
    }

    @Test
    fun testSetAppColorContrastMedium() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        dataSource.setAppColorContrast(MediumContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(MediumContrast)
    }

    @Test
    fun testSetAppColorContrastStandard() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        dataSource.setAppColorContrast(StandardContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(StandardContrast)
    }

    @Test
    fun testSetAppColorContrastSystem() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        dataSource.setAppColorContrast(SystemContrast)
        assertThat(dataSource.observeAppColorContrast().first()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastDefaultWhenMissing() = runTest {
        val features =
            mockk<Features> { every { systemColorContrastAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        assertThat(dataSource.getAppColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastFallbackForInvalidValue() = runTest {
        val features =
            mockk<Features> { every { systemColorContrastAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        dataStore.edit { preferences -> preferences[appColorContrastKey] = "invalid_key" }
        assertThat(dataSource.getAppColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testAppColorContrastWhenFeatureUnavailable() = runTest {
        val features =
            mockk<Features> { every { systemColorContrastAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), features)
        assertThat(dataSource.getAppColorContrast()).isEqualTo(StandardContrast)
    }

    @Test
    fun testGetSystemColorContrast() = runTest {
        val systemColorContrastManager = mockk<SystemColorContrastManager> {
            every { systemColorContrast } returns SystemColorContrast.HighContrast
        }
        dataSource = SettingsLocalDataSource(
            dataStore = dataStore,
            localeManager = mockk(),
            measurementSystemManager = mockk(),
            systemColorContrastManager = systemColorContrastManager,
            themeManager = mockk(),
            features = mockk()
        )
        assertThat(dataSource.getSystemColorContrast()).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testGetSystemColorContrastManagerCall() = runTest {
        val systemColorContrastManager = mockk<SystemColorContrastManager> {
            every { systemColorContrast } returns SystemColorContrast.HighContrast
        }
        dataSource = SettingsLocalDataSource(
            dataStore = dataStore,
            localeManager = mockk(),
            measurementSystemManager = mockk(),
            systemColorContrastManager = systemColorContrastManager,
            themeManager = mockk(),
            features = mockk()
        )
        dataSource.getSystemColorContrast()
        verify { systemColorContrastManager.systemColorContrast }
    }

    @Test
    fun testSetLanguage() = runTest {
        val language = Language.EnglishGbLanguage
        val localeList = language.toLocaleList()
        val localeManager = mockk<LocaleManager> { every { setLocaleList(localeList) } just runs }
        dataSource =
            SettingsLocalDataSource(dataStore, localeManager, mockk(), mockk(), mockk(), mockk())
        dataSource.setLanguage(language)
        verify { localeManager.setLocaleList(localeList) }
    }

    @Test
    fun testGetLanguage() = runTest {
        val localeManager = mockk<LocaleManager> { every { appLocale } returns Locale.UK }
        dataSource =
            SettingsLocalDataSource(dataStore, localeManager, mockk(), mockk(), mockk(), mockk())
        assertThat(dataSource.getLanguage()).isEqualTo(Language.EnglishGbLanguage)
    }

    @Test
    fun testGetLanguageManagerCall() = runTest {
        val localeManager = mockk<LocaleManager> { every { appLocale } returns Locale.UK }
        dataSource =
            SettingsLocalDataSource(dataStore, localeManager, mockk(), mockk(), mockk(), mockk())
        dataSource.getLanguage()
        verify { localeManager.appLocale }
    }

    @Test
    fun testSetMeasurementSystemMetric() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        dataSource.setMeasurementSystem(Metric)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(Metric)
    }

    @Test
    fun testSetMeasurementSystemImperialUk() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        dataSource.setMeasurementSystem(ImperialUk)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(ImperialUk)
    }

    @Test
    fun testSetMeasurementSystemSystem() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        dataSource.setMeasurementSystem(System)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemDefaultWhenMissing() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemFallbackForInvalidValue() = runTest {
        dataSource = SettingsLocalDataSource(dataStore, mockk(), mockk(), mockk(), mockk(), mockk())
        dataStore.edit { preferences -> preferences[measurementSystemKey] = "invalid_key" }
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testGetSystemMeasurementSystem() = runTest {
        val measurementSystemManager = mockk<MeasurementSystemManager> {
            every { measurementSystem } returns SystemMeasurementSystem.ImperialUs
        }
        dataSource = SettingsLocalDataSource(
            dataStore = dataStore,
            localeManager = mockk(),
            measurementSystemManager = measurementSystemManager,
            systemColorContrastManager = mockk(),
            themeManager = mockk(),
            features = mockk()
        )
        assertThat(
            dataSource.getSystemMeasurementSystem(),
        ).isEqualTo(SystemMeasurementSystem.ImperialUs)
    }

    @Test
    fun testGetSystemMeasurementSystemManagerCall() = runTest {
        val measurementSystemManager = mockk<MeasurementSystemManager> {
            every { measurementSystem } returns SystemMeasurementSystem.ImperialUs
        }
        dataSource = SettingsLocalDataSource(
            dataStore = dataStore,
            localeManager = mockk(),
            measurementSystemManager = measurementSystemManager,
            systemColorContrastManager = mockk(),
            themeManager = mockk(),
            features = mockk()
        )
        dataSource.getSystemMeasurementSystem()
        verify { measurementSystemManager.measurementSystem }
    }
}