package com.sottti.roller.coasters.data.settings.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.colorContrastKey
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.measurementSystemKey
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.themeKey
import com.sottti.roller.coasters.data.settings.mappers.toLocaleList
import com.sottti.roller.coasters.domain.settings.model.ColorContrast.HighContrast
import com.sottti.roller.coasters.domain.settings.model.ColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.settings.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.settings.model.Language
import com.sottti.roller.coasters.domain.settings.model.MeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.MeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.MeasurementSystem.System
import com.sottti.roller.coasters.domain.settings.model.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.Theme.DarkTheme
import com.sottti.roller.coasters.domain.settings.model.Theme.LightTheme
import com.sottti.roller.coasters.domain.settings.model.Theme.SystemTheme
import com.sottti.roller.coasters.utils.device.managers.ColorContrastManager
import com.sottti.roller.coasters.utils.device.managers.LocaleManager
import com.sottti.roller.coasters.utils.device.managers.MeasurementSystemManager
import com.sottti.roller.coasters.utils.device.managers.ThemeManager
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
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
    fun testSetDynamicColorTrue() = runTest {
        val sdkFeatures = mockk<SdkFeatures> { every { dynamicColorAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        dataSource.setDynamicColor(true)
        assertThat(dataSource.observeDynamicColor().first()).isTrue()
    }

    @Test
    fun testSetDynamicColorFalse() = runTest {
        val sdkFeatures = mockk<SdkFeatures> { every { dynamicColorAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        dataSource.setDynamicColor(false)
        assertThat(dataSource.observeDynamicColor().first()).isFalse()
    }

    @Test
    fun testDynamicColorDefaultWhenMissing() = runTest {
        val sdkFeatures = mockk<SdkFeatures> { every { dynamicColorAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        assertThat(dataSource.observeDynamicColor().first()).isTrue()
    }

    @Test
    fun testDynamicColorWhenFeatureUnavailable() = runTest {
        val sdkFeatures = mockk<SdkFeatures> { every { dynamicColorAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        assertThat(dataSource.observeDynamicColor().first()).isFalse()
    }

    @Test
    fun testSetThemeDark() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(DarkTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(DarkTheme)
    }

    @Test
    fun testSetThemeLight() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(LightTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(LightTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(LightTheme)
    }

    @Test
    fun testSetThemeSystem() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(SystemTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(SystemTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeManagerCalledOnSetDark() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(DarkTheme)
        verify { themeManager.setTheme(DarkTheme) }
    }

    @Test
    fun testThemeDefaultWhenMissing() = runTest {
        val sdkFeatures =
            mockk<SdkFeatures> { every { lightDarkSystemThemingAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeFallbackForInvalidValue() = runTest {
        val sdkFeatures =
            mockk<SdkFeatures> { every { lightDarkSystemThemingAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        dataStore.edit { preferences -> preferences[themeKey] = "invalid_key" }
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeWhenFeatureUnavailable() = runTest {
        val sdkFeatures =
            mockk<SdkFeatures> { every { lightDarkSystemThemingAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        assertThat(dataSource.getTheme()).isEqualTo(LightTheme)
    }

    @Test
    fun testApplyStoredThemeWithStoredValue() = runTest {
        val themeManager = mockk<ThemeManager> { every { setTheme(DarkTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), themeManager, mockk())
        dataSource.setTheme(DarkTheme)
        dataSource.applyStoredTheme()
        verify { themeManager.setTheme(DarkTheme) }
    }

    @Test
    fun testApplyStoredThemeWithDefault() = runTest {
        val sdkFeatures =
            mockk<SdkFeatures> { every { lightDarkSystemThemingAvailable() } returns true }
        val themeManager = mockk<ThemeManager> { every { setTheme(SystemTheme) } just runs }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), themeManager, sdkFeatures)
        dataStore.edit { it.clear() }
        dataSource.applyStoredTheme()
        verify { themeManager.setTheme(SystemTheme) }
    }

    @Test
    fun testSetColorContrastHigh() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        dataSource.setColorContrast(HighContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(HighContrast)
    }

    @Test
    fun testSetColorContrastMedium() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        dataSource.setColorContrast(MediumContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(MediumContrast)
    }

    @Test
    fun testSetColorContrastStandard() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        dataSource.setColorContrast(StandardContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(StandardContrast)
    }

    @Test
    fun testSetColorContrastSystem() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        dataSource.setColorContrast(SystemContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(SystemContrast)
    }

    @Test
    fun testColorContrastDefaultWhenMissing() = runTest {
        val sdkFeatures = mockk<SdkFeatures> { every { colorContrastAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        assertThat(dataSource.getColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testColorContrastFallbackForInvalidValue() = runTest {
        val sdkFeatures = mockk<SdkFeatures> { every { colorContrastAvailable() } returns true }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        dataStore.edit { preferences -> preferences[colorContrastKey] = "invalid_key" }
        assertThat(dataSource.getColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testColorContrastWhenFeatureUnavailable() = runTest {
        val sdkFeatures = mockk<SdkFeatures> { every { colorContrastAvailable() } returns false }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), sdkFeatures)
        assertThat(dataSource.getColorContrast()).isEqualTo(StandardContrast)
    }

    @Test
    fun testGetSystemColorContrast() = runTest {
        val colorContrastManager = mockk<ColorContrastManager> {
            every { colorContrast } returns SystemColorContrast.HighContrast
        }
        dataSource = SettingsLocalDataSource(
            colorContrastManager,
            dataStore,
            mockk(),
            mockk(),
            mockk(),
            mockk()
        )
        assertThat(dataSource.getSystemColorContrast()).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testGetSystemColorContrastManagerCall() = runTest {
        val colorContrastManager = mockk<ColorContrastManager> {
            every { colorContrast } returns SystemColorContrast.HighContrast
        }
        dataSource = SettingsLocalDataSource(
            colorContrastManager,
            dataStore,
            mockk(),
            mockk(),
            mockk(),
            mockk()
        )
        dataSource.getSystemColorContrast()
        verify { colorContrastManager.colorContrast }
    }

    @Test
    fun testSetLanguage() = runTest {
        val language = Language.EnglishGbLanguage
        val localeList = language.toLocaleList()
        val localeManager = mockk<LocaleManager> { every { setLocaleList(localeList) } just runs }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, localeManager, mockk(), mockk(), mockk())
        dataSource.setLanguage(language)
        verify { localeManager.setLocaleList(localeList) }
    }

    @Test
    fun testGetLanguage() = runTest {
        val localeManager = mockk<LocaleManager> { every { appLocale } returns Locale.UK }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, localeManager, mockk(), mockk(), mockk())
        assertThat(dataSource.getLanguage()).isEqualTo(Language.EnglishGbLanguage)
    }

    @Test
    fun testGetLanguageManagerCall() = runTest {
        val localeManager = mockk<LocaleManager> { every { appLocale } returns Locale.UK }
        dataSource =
            SettingsLocalDataSource(mockk(), dataStore, localeManager, mockk(), mockk(), mockk())
        dataSource.getLanguage()
        verify { localeManager.appLocale }
    }

    @Test
    fun testSetMeasurementSystemMetric() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        dataSource.setMeasurementSystem(Metric)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(Metric)
    }

    @Test
    fun testSetMeasurementSystemImperialUk() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        dataSource.setMeasurementSystem(ImperialUk)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(ImperialUk)
    }

    @Test
    fun testSetMeasurementSystemSystem() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        dataSource.setMeasurementSystem(System)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemDefaultWhenMissing() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemFallbackForInvalidValue() = runTest {
        dataSource = SettingsLocalDataSource(mockk(), dataStore, mockk(), mockk(), mockk(), mockk())
        dataStore.edit { preferences -> preferences[measurementSystemKey] = "invalid_key" }
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testGetSystemMeasurementSystem() = runTest {
        val measurementSystemManager = mockk<MeasurementSystemManager> {
            every { measurementSystem } returns SystemMeasurementSystem.ImperialUs
        }
        dataSource = SettingsLocalDataSource(
            mockk(),
            dataStore,
            mockk(),
            measurementSystemManager,
            mockk(),
            mockk()
        )
        assertThat(dataSource.getSystemMeasurementSystem()).isEqualTo(SystemMeasurementSystem.ImperialUs)
    }

    @Test
    fun testGetSystemMeasurementSystemManagerCall() = runTest {
        val measurementSystemManager = mockk<MeasurementSystemManager> {
            every { measurementSystem } returns SystemMeasurementSystem.ImperialUs
        }
        dataSource = SettingsLocalDataSource(
            mockk(),
            dataStore,
            mockk(),
            measurementSystemManager,
            mockk(),
            mockk()
        )
        dataSource.getSystemMeasurementSystem()
        verify { measurementSystemManager.measurementSystem }
    }
}