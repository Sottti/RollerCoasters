package com.sottti.roller.coasters.data.settings.datasource

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sottti.roller.coasters.data.settings.mappers.key
import com.sottti.roller.coasters.data.settings.mappers.toLanguage
import com.sottti.roller.coasters.data.settings.mappers.toLocaleList
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.HighContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.MediumContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.data.settings.model.Language
import com.sottti.roller.coasters.data.settings.model.Theme
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
        private val dynamicColorKey = booleanPreferencesKey("dynamic_color")
        private val themeKey = stringPreferencesKey("theme")
        private val colorContrastKey = stringPreferencesKey("color_contrast")
    }

    private val dynamicColorDefault = when {
        sdkFeatures.isDynamicColorAvailable() -> true
        else -> false
    }
    private val themeDefaultValue = when {
        sdkFeatures.isLightDarkThemeSystemAvailable() -> Theme.SystemTheme.key
        else -> Theme.LightTheme.key
    }
    private val colorContrastDefaultValue = when {
        sdkFeatures.isColorContrastAvailable() -> SystemContrast.key
        else -> StandardContrast.key
    }

    private val themeFlow: Flow<Theme> = dataStore.data.map { preferences ->
        when (preferences[themeKey] ?: themeDefaultValue) {
            Theme.LightTheme.key -> Theme.LightTheme
            Theme.DarkTheme.key -> Theme.DarkTheme
            else -> Theme.SystemTheme
        }
    }

    private val colorContrastFlow: Flow<ColorContrast> = dataStore.data.map { preferences ->
        when (preferences[colorContrastKey] ?: colorContrastDefaultValue) {
            StandardContrast.key -> StandardContrast
            MediumContrast.key -> MediumContrast
            HighContrast.key -> HighContrast
            SystemContrast.key -> SystemContrast
            else -> SystemContrast
        }
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

    suspend fun setColorContrast(
        colorContrast: ColorContrast,
    ) {
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
}