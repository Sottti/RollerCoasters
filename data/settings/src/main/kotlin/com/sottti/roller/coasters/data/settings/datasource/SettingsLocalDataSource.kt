package com.sottti.roller.coasters.data.settings.datasource

import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sottti.roller.coasters.data.settings.mappers.key
import com.sottti.roller.coasters.data.settings.mappers.toAppColorContrast
import com.sottti.roller.coasters.data.settings.mappers.toLanguage
import com.sottti.roller.coasters.data.settings.mappers.toLocaleList
import com.sottti.roller.coasters.data.settings.mappers.toMeasurementSystem
import com.sottti.roller.coasters.data.settings.mappers.toTheme
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.Language
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.Theme
import com.sottti.roller.coasters.utils.device.managers.LocaleManager
import com.sottti.roller.coasters.utils.device.managers.MeasurementSystemManager
import com.sottti.roller.coasters.utils.device.managers.SystemColorContrastManager
import com.sottti.roller.coasters.utils.device.managers.ThemeManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val localeManager: LocaleManager,
    private val measurementSystemManager: MeasurementSystemManager,
    private val systemColorContrastManager: SystemColorContrastManager,
    private val themeManager: ThemeManager,
    features: Features,
) {
    companion object {
        internal const val DATA_STORE_NAME = "settings"

        @VisibleForTesting
        internal val appDynamicColorKey = booleanPreferencesKey("app_dynamic_color")

        @VisibleForTesting
        internal val appColorContrastKey = stringPreferencesKey("app_color_contrast")

        @VisibleForTesting
        internal val measurementSystemKey = stringPreferencesKey("measurement_system")

        @VisibleForTesting
        internal val themeKey = stringPreferencesKey("theme")
    }

    suspend fun setAppDynamicColor(appDynamicColor: AppDynamicColor) {
        dataStore.edit { preferences ->
            preferences[appDynamicColorKey] = appDynamicColor.enabled
        }
    }

    fun observeAppDynamicColor(): Flow<AppDynamicColor> =
        dataStore.data.map { preferences ->
            AppDynamicColor(preferences[appDynamicColorKey] ?: appDynamicColorDefault)
        }

    suspend fun setTheme(theme: Theme) {
        themeManager.setTheme(theme)
        dataStore.edit { preferences ->
            preferences[themeKey] = theme.key
        }
    }

    suspend fun getTheme(): Theme = themeFlow.first()

    fun observeTheme(): Flow<Theme> = themeFlow

    suspend fun applyStoredTheme() {
        themeManager.setTheme(themeFlow.first())
    }

    suspend fun setAppColorContrast(appColorContrast: AppColorContrast) {
        dataStore.edit { preferences ->
            preferences[appColorContrastKey] = appColorContrast.key
        }
    }

    suspend fun getAppColorContrast(): AppColorContrast = appAppColorContrastFlow.first()

    fun observeAppColorContrast(): Flow<AppColorContrast> = appAppColorContrastFlow

    fun getSystemColorContrast(): SystemColorContrast =
        systemColorContrastManager.systemColorContrast

    fun setLanguage(language: Language) {
        localeManager.setLocaleList(language.toLocaleList())
    }

    fun getLanguage(): Language =
        localeManager.appLocale.toLanguage()

    suspend fun setMeasurementSystem(measurementSystem: MeasurementSystem) {
        dataStore.edit { preferences ->
            preferences[measurementSystemKey] = measurementSystem.key
        }
    }

    suspend fun getMeasurementSystem(): MeasurementSystem = measurementSystemFlow.first()

    fun observeMeasurementSystem(): Flow<MeasurementSystem> = measurementSystemFlow

    fun getSystemMeasurementSystem(): SystemMeasurementSystem =
        measurementSystemManager.measurementSystem

    private val appDynamicColorDefault by lazy {
        features.systemDynamicColorAvailable()
    }

    private val themeFlow: Flow<Theme> =
        dataStore.data.map { preferences ->
            val key = (preferences[themeKey] ?: themeDefaultValue)
            key.toTheme()
        }

    private val appAppColorContrastFlow: Flow<AppColorContrast> =
        dataStore.data.map { preferences ->
            val key = preferences[appColorContrastKey] ?: appColorContrastDefaultValue
            key.toAppColorContrast()
        }

    private val measurementSystemFlow: Flow<MeasurementSystem> =
        dataStore.data.map { preferences ->
            val key = preferences[measurementSystemKey] ?: measurementSystemDefaultValue
            key.toMeasurementSystem()
        }

    private val themeDefaultValue by lazy {
        when {
            features.lightDarkSystemThemingAvailable() -> Theme.SystemTheme.key
            else -> Theme.LightTheme.key
        }
    }

    private val appColorContrastDefaultValue by lazy {
        when {
            features.systemColorContrastAvailable() -> SystemContrast.key
            else -> StandardContrast.key
        }
    }

    private val measurementSystemDefaultValue by lazy {
        MeasurementSystem.System.key
    }
}