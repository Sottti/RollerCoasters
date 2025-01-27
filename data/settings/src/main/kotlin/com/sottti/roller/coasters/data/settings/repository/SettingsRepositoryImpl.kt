package com.sottti.roller.coasters.data.settings.repository

import com.sottti.roller.coasters.data.settings.ThemeManager
import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val themeManager: ThemeManager,
    private val localDataSource: SettingsLocalDataSource,
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
    ): ColorContrast {
        TODO("Not yet implemented")
    }

    override suspend fun getColorContrast(): ColorContrast {
        TODO("Not yet implemented")
    }

    override fun observeColorContrast(): Flow<ColorContrast> {
        TODO("Not yet implemented")
    }
}