package com.sottti.roller.coasters.data.settings.datasource

import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sottti.roller.coasters.data.settings.managers.LocaleManager
import com.sottti.roller.coasters.data.settings.managers.MeasurementSystemManager
import com.sottti.roller.coasters.data.settings.managers.SystemColorContrastManager
import com.sottti.roller.coasters.data.settings.managers.ThemeManager
import com.sottti.roller.coasters.data.settings.mapper.key
import com.sottti.roller.coasters.data.settings.mapper.toAppColorContrast
import com.sottti.roller.coasters.data.settings.mapper.toAppMeasurementSystem
import com.sottti.roller.coasters.data.settings.mapper.toLanguage
import com.sottti.roller.coasters.data.settings.mapper.toLocaleList
import com.sottti.roller.coasters.data.settings.mapper.toTheme
import com.sottti.roller.coasters.domain.features.Features
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.StandardContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast.SystemContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Locale
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
        internal val appMeasurementSystemKey = stringPreferencesKey("app_measurement_system")

        @VisibleForTesting
        internal val appThemeKey = stringPreferencesKey("app_theme")
    }

    suspend fun setAppDynamicColor(appDynamicColor: AppDynamicColor) {
        dataStore.edit { preferences ->
            preferences[appDynamicColorKey] = when (appDynamicColor) {
                AppDynamicColor.Enabled -> true
                AppDynamicColor.Disabled -> false
            }
        }
    }

    fun observeAppDynamicColor(): Flow<AppDynamicColor> =
        dataStore.data.map { preferences ->
            when (preferences[appDynamicColorKey] ?: appDynamicColorDefault) {
                true -> AppDynamicColor.Enabled
                false -> AppDynamicColor.Disabled
            }
        }

    suspend fun setAppTheme(appTheme: AppTheme) {
        themeManager.setTheme(appTheme)
        dataStore.edit { preferences ->
            preferences[appThemeKey] = appTheme.key
        }
    }

    suspend fun getAppTheme(): AppTheme = appThemeFlow.first()

    fun observeAppTheme(): Flow<AppTheme> = appThemeFlow

    suspend fun applyStoredAppTheme() {
        themeManager.setTheme(appThemeFlow.first())
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

    fun setAppLanguage(appLanguage: AppLanguage) {
        localeManager.setLocaleList(appLanguage.toLocaleList())
    }

    fun getAppLanguage(): AppLanguage =
        localeManager.appLocale.toLanguage()

    fun getAppLocale(): Locale =
        localeManager.appLocale

    suspend fun setAppMeasurementSystem(appMeasurementSystem: AppMeasurementSystem) {
        dataStore.edit { preferences ->
            preferences[appMeasurementSystemKey] = appMeasurementSystem.key
        }
    }

    suspend fun getAppMeasurementSystem(): AppMeasurementSystem = appMeasurementSystemFlow.first()

    fun observeAppMeasurementSystem(): Flow<AppMeasurementSystem> = appMeasurementSystemFlow

    fun getSystemMeasurementSystem(): SystemMeasurementSystem =
        measurementSystemManager.systemMeasurementSystem

    private val appDynamicColorDefault by lazy {
        features.systemDynamicColorAvailable()
    }

    private val appThemeFlow: Flow<AppTheme> =
        dataStore.data.map { preferences ->
            val key = (preferences[appThemeKey] ?: appThemeDefaultValue)
            key.toTheme()
        }

    private val appAppColorContrastFlow: Flow<AppColorContrast> =
        dataStore.data.map { preferences ->
            val key = preferences[appColorContrastKey] ?: appColorContrastDefaultValue
            key.toAppColorContrast()
        }

    private val appMeasurementSystemFlow: Flow<AppMeasurementSystem> =
        dataStore.data.map { preferences ->
            val key = preferences[appMeasurementSystemKey] ?: appMeasurementSystemDefaultValue
            key.toAppMeasurementSystem()
        }

    private val appThemeDefaultValue by lazy {
        when {
            features.lightDarkSystemThemingAvailable() -> AppTheme.SystemAppTheme.key
            else -> AppTheme.LightAppTheme.key
        }
    }

    private val appColorContrastDefaultValue by lazy {
        when {
            features.systemColorContrastAvailable() -> SystemContrast.key
            else -> StandardContrast.key
        }
    }

    private val appMeasurementSystemDefaultValue by lazy {
        AppMeasurementSystem.System.key
    }
}