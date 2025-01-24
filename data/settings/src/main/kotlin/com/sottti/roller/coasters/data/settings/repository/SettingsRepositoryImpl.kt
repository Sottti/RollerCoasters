package com.sottti.roller.coasters.data.settings.repository

import com.sottti.roller.coasters.data.settings.AppThemeManager
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.domain.settings.model.Theme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val appThemeManager: AppThemeManager,
    private val localDataSource: SettingsLocalDataSource,
) : SettingsRepository {

    override suspend fun setDynamicColor(enabled: Boolean) {
        localDataSource.setDynamicColor(enabled)
    }

    override fun observeDynamicColor(): Flow<Boolean> =
        localDataSource.observeDynamicColor()

    override suspend fun setAppTheme(theme: Theme) {
        appThemeManager.setAppTheme(theme)
        localDataSource.setAppTheme(theme)
    }

    override suspend fun getAppTheme(): Theme = localDataSource.getAppTheme()

    override fun observeAppTheme(): Flow<Theme> = localDataSource.observeAppTheme()

    override suspend fun applyStoredAppTheme() {
        val theme = localDataSource.getAppTheme()
        appThemeManager.setAppTheme(theme)
    }
}