package com.sottti.roller.coasters.data.settings.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : SettingsRepository {

    companion object {
        private const val DYNAMIC_COLOR_DEFAULT_VALUE = true
        private val DYNAMIC_COLOR_KEY = booleanPreferencesKey("dynamic_color")

        private val THEME_DEFAULT_VALUE = Theme.SystemTheme.key
        private val THEME_KEY = stringPreferencesKey("theme")
    }

    override fun observeDynamicColor(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[DYNAMIC_COLOR_KEY] ?: DYNAMIC_COLOR_DEFAULT_VALUE
        }

    override suspend fun setDynamicColor(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DYNAMIC_COLOR_KEY] = enabled
        }
    }

    override suspend fun setTheme(theme: Theme) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.key
        }
    }

    override suspend fun getTheme(): Theme {
        val preferences = dataStore.data.first()
        return when (preferences[THEME_KEY] ?: THEME_DEFAULT_VALUE) {
            Theme.LightTheme.key -> Theme.LightTheme
            Theme.DarkTheme.key -> Theme.DarkTheme
            else -> Theme.SystemTheme
        }
    }

    override fun observeTheme(): Flow<Theme> =
        dataStore.data.map { preferences ->
            when (preferences[THEME_KEY] ?: THEME_DEFAULT_VALUE) {
                Theme.LightTheme.key -> Theme.LightTheme
                Theme.DarkTheme.key -> Theme.DarkTheme
                else -> Theme.SystemTheme
            }
        }
}