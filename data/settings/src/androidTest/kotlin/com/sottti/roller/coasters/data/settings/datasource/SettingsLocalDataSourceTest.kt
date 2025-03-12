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
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource.Companion.themeKey
import com.sottti.roller.coasters.data.settings.mappers.toLocaleList
import com.sottti.roller.coasters.domain.model.ColorContrast.HighContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.MediumContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.model.Language
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
            every { dynamicColorAvailable() } returns true
            every { lightDarkSystemThemingAvailable() } returns true
            every { colorContrastAvailable() } returns true
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
        val result = dataSource.observeDynamicColor().first()
        assertThat(result).isTrue()

        dataSource.setDynamicColor(false)
        val resultFalse = dataSource.observeDynamicColor().first()
        assertThat(resultFalse).isFalse()
    }

    @Test
    fun testSetAndGetTheme() = runTest {
        dataSource.setTheme(DarkTheme)
        val result = dataSource.getTheme()
        assertThat(result).isEqualTo(DarkTheme)

        dataSource.setTheme(LightTheme)
        val resultLight = dataSource.getTheme()
        assertThat(resultLight).isEqualTo(LightTheme)

        dataSource.setTheme(SystemTheme)
        val resultSystem = dataSource.getTheme()
        assertThat(resultSystem).isEqualTo(SystemTheme)
    }

    @Test
    fun testSetAndObserveTheme() = runTest {
        dataSource.setTheme(LightTheme)
        val result = dataSource.observeTheme().first()
        assertThat(result).isEqualTo(LightTheme)

        dataSource.setTheme(DarkTheme)
        val resultDark = dataSource.observeTheme().first()
        assertThat(resultDark).isEqualTo(DarkTheme)

        dataSource.setTheme(SystemTheme)
        val resultSystem = dataSource.observeTheme().first()
        assertThat(resultSystem).isEqualTo(SystemTheme)
    }

    @Test
    fun testSetAndGetColorContrast() = runTest {
        dataSource.setColorContrast(HighContrast)
        val result = dataSource.getColorContrast()
        assertThat(result).isEqualTo(HighContrast)

        dataSource.setColorContrast(MediumContrast)
        val resultMedium = dataSource.getColorContrast()
        assertThat(resultMedium).isEqualTo(MediumContrast)

        dataSource.setColorContrast(StandardContrast)
        val resultStandard = dataSource.getColorContrast()
        assertThat(resultStandard).isEqualTo(StandardContrast)

        dataSource.setColorContrast(SystemContrast)
        val resultSystem = dataSource.getColorContrast()
        assertThat(resultSystem).isEqualTo(SystemContrast)
    }

    @Test
    fun testSetAndObserveColorContrast() = runTest {
        dataSource.setColorContrast(MediumContrast)
        val result = dataSource.observeColorContrast().first()
        assertThat(result).isEqualTo(MediumContrast)

        dataSource.setColorContrast(HighContrast)
        val resultHigh = dataSource.observeColorContrast().first()
        assertThat(resultHigh).isEqualTo(HighContrast)
    }

    @Test
    fun testDefaultValuesUsedWhenPreferencesAreMissing() = runTest {
        assertThat(dataSource.getTheme()).isEqualTo(SystemTheme)
        assertThat(dataSource.getColorContrast()).isEqualTo(SystemContrast)
        assertThat(dataSource.observeDynamicColor().first()).isTrue()
    }

    @Test
    fun testDefaultValuesWhenFeaturesUnavailable() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val customSdkFeatures = mockk<SdkFeatures> {
            every { dynamicColorAvailable() } returns false
            every { lightDarkSystemThemingAvailable() } returns false
            every { colorContrastAvailable() } returns false
        }
        val customDataSource = SettingsLocalDataSource(context.dataStore, customSdkFeatures)

        context.dataStore.edit { it.clear() }

        assertThat(customDataSource.observeDynamicColor().first()).isFalse()
        assertThat(customDataSource.getTheme()).isEqualTo(LightTheme)
        assertThat(customDataSource.getColorContrast()).isEqualTo(StandardContrast)
    }

    @Test
    fun testSetAndGetLanguage() = runTest {
        mockkStatic(AppCompatDelegate::class)
        val language = Language.EnglishGbLanguage
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
        assertThat(result).isEqualTo(Language.SystemLanguage)
    }

    @Test
    fun testThemeFallbackForInvalidStoredValue() = runTest {
        dataStore.edit { preferences ->
            preferences[themeKey] = "invalid_key"
        }

        val result = dataSource.getTheme()
        assertThat(result).isEqualTo(SystemTheme)
    }

    @Test
    fun testColorContrastFallbackForInvalidStoredValue() = runTest {
        dataStore.edit { preferences ->
            preferences[colorContrastKey] = "invalid_key"
        }

        val result = dataSource.getColorContrast()
        assertThat(result).isEqualTo(SystemContrast)
    }
}