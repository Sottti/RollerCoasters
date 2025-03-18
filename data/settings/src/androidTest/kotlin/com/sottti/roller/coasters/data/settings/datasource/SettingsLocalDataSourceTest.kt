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
import com.sottti.roller.coasters.domain.model.ColorContrast.HighContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.domain.model.MeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.model.MeasurementSystem.Metric
import com.sottti.roller.coasters.domain.model.MeasurementSystem.System
import com.sottti.roller.coasters.domain.model.SystemColorContrast
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.model.Theme.DarkTheme
import com.sottti.roller.coasters.domain.model.Theme.LightTheme
import com.sottti.roller.coasters.domain.model.Theme.SystemTheme
import com.sottti.roller.coasters.utils.device.managers.ColorContrastManager
import com.sottti.roller.coasters.utils.device.managers.LanguageManager
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

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
internal class SettingsLocalDataSourceTest {

    private lateinit var colorContrastManager: ColorContrastManager
    private lateinit var context: Context
    private lateinit var dataSource: SettingsLocalDataSource
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var languageManager: LanguageManager
    private lateinit var measurementSystemManager: MeasurementSystemManager
    private lateinit var sdkFeatures: SdkFeatures
    private lateinit var themeManager: ThemeManager

    @Before
    fun setup() {
        colorContrastManager = mockk()
        context = ApplicationProvider.getApplicationContext<Context>()
        dataStore = context.dataStore
        languageManager = mockk()
        measurementSystemManager = mockk()
        sdkFeatures = mockk {
            every { colorContrastAvailable() } returns true
            every { dynamicColorAvailable() } returns true
            every { lightDarkSystemThemingAvailable() } returns true
            every { measurementSystemAvailable() } returns true
        }
        themeManager = mockk()
        dataSource = SettingsLocalDataSource(
            colorContrastManager = colorContrastManager,
            dataStore = dataStore,
            languageManager = languageManager,
            measurementSystemManager = measurementSystemManager,
            sdkFeatures = sdkFeatures,
            themeManager = themeManager,
        )
        runTest { dataStore.edit { it.clear() } }
    }

    @After
    fun tearDown() {
        runTest { dataStore.edit { it.clear() } }
    }

    @Test
    fun testSetDynamicColorTrue() = runTest {
        dataSource.setDynamicColor(true)
        assertThat(dataSource.observeDynamicColor().first()).isTrue()
    }

    @Test
    fun testSetDynamicColorFalse() = runTest {
        dataSource.setDynamicColor(false)
        assertThat(dataSource.observeDynamicColor().first()).isFalse()
    }

    @Test
    fun testDynamicColorDefaultWhenMissing() = runTest {
        assertThat(dataSource.observeDynamicColor().first()).isTrue()
    }

    @Test
    fun testDynamicColorWhenFeatureUnavailable() = runTest {
        val customSdkFeatures =
            mockk<SdkFeatures> { every { dynamicColorAvailable() } returns false }
        val customDataSource = SettingsLocalDataSource(
            colorContrastManager = colorContrastManager,
            dataStore = dataStore,
            languageManager = languageManager,
            measurementSystemManager = measurementSystemManager,
            themeManager = themeManager,
            sdkFeatures = customSdkFeatures
        )

        assertThat(customDataSource.observeDynamicColor().first()).isFalse()
    }

    @Test
    fun testSetThemeDark() = runTest {
        every { themeManager.setTheme(DarkTheme) } just runs
        dataSource.setTheme(DarkTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(DarkTheme)
    }

    @Test
    fun testSetThemeLight() = runTest {
        every { themeManager.setTheme(LightTheme) } just runs
        dataSource.setTheme(LightTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(LightTheme)
    }

    @Test
    fun testSetThemeSystem() = runTest {
        every { themeManager.setTheme(SystemTheme) } just runs
        dataSource.setTheme(SystemTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeManagerCalledOnSetDark() = runTest {
        every { themeManager.setTheme(DarkTheme) } just runs
        dataSource.setTheme(DarkTheme)
        verify { themeManager.setTheme(DarkTheme) }
    }

    @Test
    fun testThemeDefaultWhenMissing() = runTest {
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeFallbackForInvalidValue() = runTest {
        dataStore.edit { preferences -> preferences[themeKey] = "invalid_key" }
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeWhenFeatureUnavailable() = runTest {
        val customSdkFeatures =
            mockk<SdkFeatures> { every { lightDarkSystemThemingAvailable() } returns false }
        val customDataSource = SettingsLocalDataSource(
            colorContrastManager = colorContrastManager,
            dataStore = dataStore,
            languageManager = languageManager,
            measurementSystemManager = measurementSystemManager,
            themeManager = themeManager,
            sdkFeatures = customSdkFeatures
        )
        assertThat(customDataSource.getTheme()).isEqualTo(LightTheme)
    }

    @Test
    fun testApplyStoredThemeWithStoredValue() = runTest {
        every { themeManager.setTheme(DarkTheme) } just runs
        dataSource.setTheme(DarkTheme)
        dataSource.applyStoredTheme()
        verify { themeManager.setTheme(DarkTheme) }
    }

    @Test
    fun testApplyStoredThemeWithDefault() = runTest {
        every { themeManager.setTheme(SystemTheme) } just runs
        dataStore.edit { it.clear() }
        dataSource.applyStoredTheme()
        verify { themeManager.setTheme(SystemTheme) }
    }

    @Test
    fun testSetColorContrastHigh() = runTest {
        dataSource.setColorContrast(HighContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(HighContrast)
    }

    @Test
    fun testSetColorContrastMedium() = runTest {
        dataSource.setColorContrast(MediumContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(MediumContrast)
    }

    @Test
    fun testSetColorContrastStandard() = runTest {
        dataSource.setColorContrast(StandardContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(StandardContrast)
    }

    @Test
    fun testSetColorContrastSystem() = runTest {
        dataSource.setColorContrast(SystemContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(SystemContrast)
    }

    @Test
    fun testColorContrastDefaultWhenMissing() = runTest {
        assertThat(dataSource.getColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testColorContrastFallbackForInvalidValue() = runTest {
        dataStore.edit { preferences ->
            preferences[colorContrastKey]
        }
        assertThat(dataSource.getColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testColorContrastWhenFeatureUnavailable() = runTest {
        val customSdkFeatures =
            mockk<SdkFeatures> { every { colorContrastAvailable() } returns false }
        val customDataSource = SettingsLocalDataSource(
            colorContrastManager = colorContrastManager,
            dataStore = dataStore,
            languageManager = languageManager,
            measurementSystemManager = measurementSystemManager,
            themeManager = themeManager,
            sdkFeatures = customSdkFeatures
        )
        assertThat(customDataSource.getColorContrast()).isEqualTo(StandardContrast)
    }

    @Test
    fun testGetSystemColorContrast() = runTest {
        every { colorContrastManager.colorContrast } returns SystemColorContrast.HighContrast
        assertThat(dataSource.getSystemColorContrast()).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun testGetSystemColorContrastManagerCall() = runTest {
        every { colorContrastManager.colorContrast } returns SystemColorContrast.HighContrast
        dataSource.getSystemColorContrast()
        verify { colorContrastManager.colorContrast }
    }

    @Test
    fun testSetLanguage() = runTest {
        val language = Language.EnglishGbLanguage
        every { languageManager.setLanguage(language) } just runs
        dataSource.setLanguage(language)
        verify { languageManager.setLanguage(language) }
    }

    @Test
    fun testGetLanguage() = runTest {
        val language = Language.EnglishGbLanguage
        every { languageManager.language } returns language
        assertThat(dataSource.getLanguage()).isEqualTo(language)
    }

    @Test
    fun testGetLanguageManagerCall() = runTest {
        every { languageManager.language } returns Language.EnglishGbLanguage
        dataSource.getLanguage()
        verify { languageManager.language }
    }

    @Test
    fun testSetMeasurementSystemMetric() = runTest {
        dataSource.setMeasurementSystem(Metric)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(Metric)
    }

    @Test
    fun testSetMeasurementSystemImperialUk() = runTest {
        dataSource.setMeasurementSystem(ImperialUk)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(ImperialUk)
    }

    @Test
    fun testSetMeasurementSystemSystem() = runTest {
        dataSource.setMeasurementSystem(System)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemDefaultWhenMissing() = runTest {
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemFallbackForInvalidValue() = runTest {
        dataStore.edit { preferences -> preferences[measurementSystemKey] = "invalid_key" }
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testGetSystemMeasurementSystem() = runTest {
        every {
            measurementSystemManager.measurementSystem
        } returns SystemMeasurementSystem.ImperialUs

        assertThat(dataSource.getSystemMeasurementSystem())
            .isEqualTo(SystemMeasurementSystem.ImperialUs)
    }

    @Test
    fun testGetSystemMeasurementSystemManagerCall() = runTest {
        every {
            measurementSystemManager.measurementSystem
        } returns SystemMeasurementSystem.ImperialUs
        dataSource.getSystemMeasurementSystem()
        verify { measurementSystemManager.measurementSystem }
    }
}