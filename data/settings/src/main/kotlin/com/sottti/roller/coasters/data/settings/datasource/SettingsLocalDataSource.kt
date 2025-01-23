package com.sottti.roller.coasters.data.settings.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sottti.roller.coasters.domain.settings.model.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    companion object {
        private const val DYNAMIC_COLOR_DEFAULT_VALUE = true
        private val DYNAMIC_COLOR_KEY = booleanPreferencesKey("dynamic_color")

        private val THEME_DEFAULT_VALUE = Theme.SystemTheme.key
        private val THEME_KEY = stringPreferencesKey("theme")
    }

    fun observeDynamicColor(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[DYNAMIC_COLOR_KEY] ?: DYNAMIC_COLOR_DEFAULT_VALUE
        }

    suspend fun setDynamicColor(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DYNAMIC_COLOR_KEY] = enabled
        }
    }

    suspend fun setTheme(theme: Theme) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.key
        }
    }

    fun observeTheme(): Flow<Theme> = themeFlow

    suspend fun getTheme(): Theme = themeFlow.first()

    private val themeFlow: Flow<Theme> = dataStore.data.map { preferences ->
        when (preferences[THEME_KEY] ?: THEME_DEFAULT_VALUE) {
            Theme.LightTheme.key -> Theme.LightTheme
            Theme.DarkTheme.key -> Theme.DarkTheme
            else -> Theme.SystemTheme
        }
    }
}