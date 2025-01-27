package com.sottti.roller.coasters.data.settings.repository

import com.sottti.roller.coasters.data.settings.ThemeManager
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.Theme
import com.sottti.roller.coasters.utils.device.system.SystemColorContrast
import com.sottti.roller.coasters.utils.device.system.SystemSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
    private val systemSettings: SystemSettings,
    private val themeManager: ThemeManager,
) : SettingsRepository {

    override suspend fun setDynamicColor(enabled: Boolean) {
        localDataSource.setDynamicColor(enabled)
    }

    override fun observeDynamicColor(): Flow<Boolean> =
        localDataSource.observeDynamicColor()

    override suspend fun setTheme(theme: Theme) {
        themeManager.setTheme(theme)
        localDataSource.setTheme(theme)
    }

    override suspend fun getTheme(): Theme = localDataSource.getTheme()

    override fun observeTheme(): Flow<Theme> = localDataSource.observeTheme()

    override suspend fun applyStoredTheme() {
        val theme = localDataSource.getTheme()
        themeManager.setTheme(theme)
    }

    override suspend fun setColorContrast(
        contrast: ColorContrast,
    ) {
        localDataSource.setColorContrast(contrast)
    }

    override suspend fun getColorContrast(): ColorContrast =
        localDataSource.getColorContrast()

    override fun observeColorContrast(): Flow<ColorContrast> =
        localDataSource.observeColorContrast()

    override fun getSystemColorContrast(): SystemColorContrast =
        systemSettings.colorContrast
}