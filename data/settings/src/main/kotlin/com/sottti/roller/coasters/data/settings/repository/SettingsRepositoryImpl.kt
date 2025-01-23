package com.sottti.roller.coasters.data.settings.repository

import com.sottti.roller.coasters.data.settings.ThemeManager
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
    private val themeManager: ThemeManager,
) : SettingsRepository {

    override suspend fun setDynamicColor(enabled: Boolean) {
        localDataSource.setDynamicColor(enabled)
    }

    override fun observeDynamicColor(): Flow<Boolean> =
        localDataSource.observeDynamicColor()

    override suspend fun setTheme(theme: Theme) {
        themeManager.setAppTheme(theme)
        localDataSource.setTheme(theme)
    }

    override suspend fun getTheme(): Theme = localDataSource.getTheme()

    override fun observeTheme(): Flow<Theme> = localDataSource.observeTheme()
}