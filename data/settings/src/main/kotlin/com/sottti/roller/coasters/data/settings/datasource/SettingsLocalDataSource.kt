package com.sottti.roller.coasters.data.settings.datasource

import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sottti.roller.coasters.data.settings.mappers.key
import com.sottti.roller.coasters.data.settings.mappers.toColorContrast
import com.sottti.roller.coasters.data.settings.mappers.toLanguage
import com.sottti.roller.coasters.data.settings.mappers.toLocaleList
import com.sottti.roller.coasters.data.settings.mappers.toMeasurementSystem
import com.sottti.roller.coasters.data.settings.mappers.toTheme
import com.sottti.roller.coasters.domain.model.ColorContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.domain.model.MeasurementSystem
import com.sottti.roller.coasters.domain.model.Theme
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    sdkFeatures: SdkFeatures,
) {
    companion object {
        internal const val DATA_STORE_NAME = "settings"

        @VisibleForTesting
        internal val dynamicColorKey = booleanPreferencesKey("dynamic_color")

        @VisibleForTesting
        internal val colorContrastKey = stringPreferencesKey("color_contrast")

        @VisibleForTesting
        internal val measurementSystemKey = stringPreferencesKey("measurement_system")

        @VisibleForTesting
        internal val themeKey = stringPreferencesKey("theme")
    }

    suspend fun setDynamicColor(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[dynamicColorKey] = enabled
        }
    }

    fun observeDynamicColor(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[dynamicColorKey] ?: dynamicColorDefault
        }

    suspend fun setTheme(theme: Theme) {
        dataStore.edit { preferences ->
            preferences[themeKey] = theme.key
        }
    }

    suspend fun getTheme(): Theme = themeFlow.first()

    fun observeTheme(): Flow<Theme> = themeFlow

    suspend fun setColorContrast(colorContrast: ColorContrast) {
        dataStore.edit { preferences ->
            preferences[colorContrastKey] = colorContrast.key
        }
    }

    suspend fun getColorContrast(): ColorContrast = colorContrastFlow.first()

    fun observeColorContrast(): Flow<ColorContrast> = colorContrastFlow

    fun setLanguage(language: Language) {
        AppCompatDelegate.setApplicationLocales(language.toLocaleList())
    }

    fun getLanguage(): Language {
        val localeList = AppCompatDelegate.getApplicationLocales()
        val currentLocale = localeList[0]
        return currentLocale.toLanguage()
    }

    suspend fun setMeasurementSystem(measurementSystem: MeasurementSystem) {
        dataStore.edit { preferences ->
            preferences[measurementSystemKey] = measurementSystem.key
        }
    }

    suspend fun getMeasurementSystem(): MeasurementSystem = measurementSystemFlow.first()

    fun observeMeasurementSystem(): Flow<MeasurementSystem> = measurementSystemFlow

    private val dynamicColorDefault = when {
        sdkFeatures.dynamicColorAvailable() -> true
        else -> false
    }

    private val themeDefaultValue = when {
        sdkFeatures.lightDarkSystemThemingAvailable() -> Theme.SystemTheme.key
        else -> Theme.LightTheme.key
    }

    private val colorContrastDefaultValue = when {
        sdkFeatures.colorContrastAvailable() -> SystemContrast.key
        else -> StandardContrast.key
    }

    private val measurementSystemDefaultValue = when {
        sdkFeatures.measurementSystemAvailable() -> MeasurementSystem.System.key
        else -> MeasurementSystem.Metric.key
    }

    private val themeFlow: Flow<Theme> = dataStore.data.map { preferences ->
        val key = (preferences[themeKey] ?: themeDefaultValue)
        key.toTheme()
    }

    private val colorContrastFlow: Flow<ColorContrast> = dataStore.data.map { preferences ->
        val key = preferences[colorContrastKey] ?: colorContrastDefaultValue
        key.toColorContrast()
    }

    private val measurementSystemFlow: Flow<MeasurementSystem> = dataStore.data.map { preferences ->
        val key = preferences[measurementSystemKey] ?: measurementSystemDefaultValue
        key.toMeasurementSystem()
    }
}