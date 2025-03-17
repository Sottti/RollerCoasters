package com.sottti.roller.coasters.data.settings.datasource

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.DATA_STORE_NAME
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.colorContrastKey
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.measurementSystemKey
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.themeKey
import com.sottti.roller.coasters.data.settings.mappers.toLocaleList
import com.sottti.roller.coasters.domain.model.ColorContrast.HighContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.model.Language.EnglishGbLanguage
import com.sottti.roller.coasters.domain.model.Language.SystemLanguage
import com.sottti.roller.coasters.domain.model.MeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.model.MeasurementSystem.Metric
import com.sottti.roller.coasters.domain.model.MeasurementSystem.System
import com.sottti.roller.coasters.domain.model.Theme.DarkTheme
import com.sottti.roller.coasters.domain.model.Theme.LightTheme
import com.sottti.roller.coasters.domain.model.Theme.SystemTheme
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.unmockkStatic
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
internal class SettingsLocalDataSourceTest {

    private lateinit var dataSource: SettingsLocalDataSource
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var sdkFeatures: SdkFeatures

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dataStore = context.dataStore
        sdkFeatures = mockk {
            every { colorContrastAvailable() } returns true
            every { dynamicColorAvailable() } returns true
            every { lightDarkSystemThemingAvailable() } returns true
            every { measurementSystemAvailable() } returns true
        }

        dataSource = SettingsLocalDataSource(dataStore, sdkFeatures)

        runTest { dataStore.edit { it.clear() } }
    }

    @After
    fun tearDown() {
        runTest { dataStore.edit { it.clear() } }
        unmockkStatic(AppCompatDelegate::class)
    }

    @Test
    fun testSetAndGetDynamicColor() = runTest {
        dataSource.setDynamicColor(true)
        assertThat(dataSource.observeDynamicColor().first()).isTrue()

        dataSource.setDynamicColor(false)
        assertThat(dataSource.observeDynamicColor().first()).isFalse()
    }

    @Test
    fun testSetAndObserveDynamicColor() = runTest {
        dataSource.setDynamicColor(true)
        assertThat(dataSource.observeDynamicColor().first()).isTrue()

        dataSource.setDynamicColor(false)
        assertThat(dataSource.observeDynamicColor().first()).isFalse()
    }

    @Test
    fun testDefaultDynamicColorForMissingValue() = runTest {
        assertThat(dataSource.observeDynamicColor().first()).isTrue()
    }

    @Test
    fun testDefaultDynamicColorWhenFeatureUnavailable() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val customSdkFeatures = mockk<SdkFeatures> {
            every { dynamicColorAvailable() } returns false
        }
        val customDataSource = SettingsLocalDataSource(context.dataStore, customSdkFeatures)

        context.dataStore.edit { it.clear() }
        assertThat(customDataSource.observeDynamicColor().first()).isFalse()
    }

    @Test
    fun testSetAndGetTheme() = runTest {
        dataSource.setTheme(DarkTheme)
        assertThat(dataSource.getTheme()).isEqualTo(DarkTheme)

        dataSource.setTheme(LightTheme)
        assertThat(dataSource.getTheme()).isEqualTo(LightTheme)

        dataSource.setTheme(SystemTheme)
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testSetAndObserveTheme() = runTest {
        dataSource.setTheme(LightTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(LightTheme)

        dataSource.setTheme(DarkTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(DarkTheme)

        dataSource.setTheme(SystemTheme)
        assertThat(dataSource.observeTheme().first()).isEqualTo(SystemTheme)
    }

    @Test
    fun testDefaultThemeForMissingValue() = runTest {
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testThemeFallbackForInvalidStoredValue() = runTest {
        dataStore.edit { preferences -> preferences[themeKey] = "invalid_key" }
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
    }

    @Test
    fun testDefaultThemeWhenFeatureUnavailable() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val customSdkFeatures = mockk<SdkFeatures> {
            every { lightDarkSystemThemingAvailable() } returns false
        }
        val customDataSource = SettingsLocalDataSource(context.dataStore, customSdkFeatures)

        context.dataStore.edit { it.clear() }
        assertThat(customDataSource.getTheme()).isEqualTo(LightTheme)
    }

    @Test
    fun testSetAndGetColorContrast() = runTest {
        dataSource.setColorContrast(HighContrast)
        assertThat(dataSource.getColorContrast()).isEqualTo(HighContrast)

        dataSource.setColorContrast(MediumContrast)
        assertThat(dataSource.getColorContrast()).isEqualTo(MediumContrast)
    }

    @Test
    fun testSetAndObserveColorContrast() = runTest {
        dataSource.setColorContrast(MediumContrast)
        assertThat(dataSource.observeColorContrast().first()).isEqualTo(MediumContrast)
    }

    @Test
    fun testDefaultColorContrastForMissingValue() = runTest {
        assertThat(dataSource.getColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testColorContrastFallbackForInvalidStoredValue() = runTest {
        dataStore.edit { preferences -> preferences[colorContrastKey] = "invalid_key" }
        assertThat(dataSource.getColorContrast()).isEqualTo(SystemContrast)
    }

    @Test
    fun testDefaultColorContrastWhenFeatureUnavailable() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val customSdkFeatures = mockk<SdkFeatures> {
            every { colorContrastAvailable() } returns false
        }
        val customDataSource = SettingsLocalDataSource(context.dataStore, customSdkFeatures)

        context.dataStore.edit { it.clear() }
        assertThat(customDataSource.getColorContrast()).isEqualTo(StandardContrast)
    }

    @Test
    fun testSetAndGetLanguage() = runTest {
        mockkStatic(AppCompatDelegate::class)
        val language = EnglishGbLanguage
        val localeList = language.toLocaleList()
        every { AppCompatDelegate.getApplicationLocales() } returns localeList
        every { AppCompatDelegate.setApplicationLocales(localeList) } just runs

        dataSource.setLanguage(language)
        val result = dataSource.getLanguage()

        assertThat(result).isEqualTo(language)
        verify { AppCompatDelegate.setApplicationLocales(localeList) }
    }

    @Test
    fun testGetLanguageWithEmptyLocaleList() = runTest {
        mockkStatic(AppCompatDelegate::class)
        every {
            AppCompatDelegate.getApplicationLocales()
        } returns LocaleListCompat.getEmptyLocaleList()

        val result = dataSource.getLanguage()
        assertThat(result).isEqualTo(SystemLanguage)
    }

    @Test
    fun testSetAndGetMeasurementSystem() = runTest {
        dataSource.setMeasurementSystem(Metric)
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(Metric)

        dataSource.setMeasurementSystem(ImperialUk)
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(ImperialUk)
    }

    @Test
    fun testSetAndObserveMeasurementSystem() = runTest {
        dataSource.setMeasurementSystem(Metric)
        assertThat(dataSource.observeMeasurementSystem().first()).isEqualTo(Metric)
    }

    @Test
    fun testDefaultMeasurementSystemForMissingValue() = runTest {
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testMeasurementSystemFallbackForInvalidStoredValue() = runTest {
        dataStore.edit { preferences -> preferences[measurementSystemKey] = "invalid_key" }
        assertThat(dataSource.getMeasurementSystem()).isEqualTo(System)
    }

    @Test
    fun testDefaultMeasurementSystemWhenFeatureUnavailable() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val customSdkFeatures = mockk<SdkFeatures> {
            every { measurementSystemAvailable() } returns false
        }
        val customDataSource = SettingsLocalDataSource(context.dataStore, customSdkFeatures)

        context.dataStore.edit { it.clear() }
        assertThat(customDataSource.getMeasurementSystem()).isEqualTo(Metric)
    }
}