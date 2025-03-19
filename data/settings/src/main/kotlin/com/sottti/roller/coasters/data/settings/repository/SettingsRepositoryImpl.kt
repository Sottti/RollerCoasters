package com.sottti.roller.coasters.data.settings.repository

import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.domain.model.ColorContrast
import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.domain.model.MeasurementSystem
import com.sottti.roller.coasters.domain.model.SystemColorContrast
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.model.Theme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
) : SettingsRepository {

    override suspend fun setDynamicColor(enabled: Boolean) {
        localDataSource.setDynamicColor(enabled)
    }

    override fun observeDynamicColor(): Flow<Boolean> =
        localDataSource.observeDynamicColor()

    override suspend fun setTheme(theme: Theme) {
        localDataSource.setTheme(theme)
    }

    override suspend fun getTheme(): Theme = localDataSource.getTheme()

    override fun observeTheme(): Flow<Theme> = localDataSource.observeTheme()

    override suspend fun applyStoredTheme() {
        localDataSource.applyStoredTheme()
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
        localDataSource.getSystemColorContrast()

    override fun setLanguage(language: Language) {
        localDataSource.setLanguage(language)
    }

    override suspend fun getLanguage(): Language =
        localDataSource.getLanguage()

    override suspend fun setMeasurementSystem(measurementSystem: MeasurementSystem) {
        localDataSource.setMeasurementSystem(measurementSystem)
    }

    override suspend fun getMeasurementSystem(): MeasurementSystem =
        localDataSource.getMeasurementSystem()

    override fun observeMeasurementSystem(): Flow<MeasurementSystem> =
        localDataSource.observeMeasurementSystem()

    override fun getSystemMeasurementSystem(): SystemMeasurementSystem =
        localDataSource.getSystemMeasurementSystem()
}