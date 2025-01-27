package com.sottti.roller.coasters.data.settings.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sottti.roller.coasters.data.settings.key
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.HighContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.MediumContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.StandardContrast
import com.sottti.roller.coasters.data.settings.model.ColorContrast.SystemContrast
import com.sottti.roller.coasters.data.settings.model.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    companion object {
        internal const val DATA_STORE_NAME = "settings"

        private const val DYNAMIC_COLOR_DEFAULT_VALUE = true
        private val DYNAMIC_COLOR_KEY = booleanPreferencesKey("dynamic_color")

        private val THEME_DEFAULT_VALUE = Theme.SystemTheme.key
        private val THEME_KEY = stringPreferencesKey("theme")

        private val COLOR_CONTRAST_DEFAULT_VALUE = SystemContrast.key
        private val COLOR_CONTRAST_KEY = stringPreferencesKey("color_contrast")
    }

    suspend fun setDynamicColor(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DYNAMIC_COLOR_KEY] = enabled
        }
    }

    fun observeDynamicColor(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[DYNAMIC_COLOR_KEY] ?: DYNAMIC_COLOR_DEFAULT_VALUE
        }

    suspend fun setTheme(theme: Theme) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.key
        }
    }

    suspend fun getTheme(): Theme = themeFlow.first()

    fun observeTheme(): Flow<Theme> = themeFlow

    private val themeFlow: Flow<Theme> = dataStore.data.map { preferences ->
        when (preferences[THEME_KEY] ?: THEME_DEFAULT_VALUE) {
            Theme.LightTheme.key -> Theme.LightTheme
            Theme.DarkTheme.key -> Theme.DarkTheme
            else -> Theme.SystemTheme
        }
    }

    suspend fun setColorContrast(
        colorContrast: ColorContrast,
    ) {
        dataStore.edit { preferences ->
            preferences[COLOR_CONTRAST_KEY] = colorContrast.key
        }
    }

    suspend fun getColorContrast(): ColorContrast = colorContrastFlow.first()

    fun observeColorContrast(): Flow<ColorContrast> = colorContrastFlow

    private val colorContrastFlow: Flow<ColorContrast> = dataStore.data.map { preferences ->
        when (preferences[COLOR_CONTRAST_KEY] ?: COLOR_CONTRAST_DEFAULT_VALUE) {
            StandardContrast.key -> StandardContrast
            MediumContrast.key -> MediumContrast
            HighContrast.key -> HighContrast
            SystemContrast.key -> SystemContrast
            else -> SystemContrast
        }
    }
}