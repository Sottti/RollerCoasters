package com.sottti.roller.coasters.data.settings.repository

import com.sottti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.Language
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.Theme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
) : SettingsRepository {

    override suspend fun setAppDynamicColor(appDynamicColor: AppDynamicColor) {
        localDataSource.setAppDynamicColor(appDynamicColor)
    }

    override fun observeAppDynamicColor(): Flow<AppDynamicColor> =
        localDataSource.observeAppDynamicColor()

    override suspend fun setTheme(theme: Theme) {
        localDataSource.setTheme(theme)
    }

    override suspend fun getTheme(): Theme = localDataSource.getTheme()

    override fun observeTheme(): Flow<Theme> = localDataSource.observeTheme()

    override suspend fun applyStoredTheme() {
        localDataSource.applyStoredTheme()
    }

    override suspend fun setAppColorContrast(
        contrast: AppColorContrast,
    ) {
        localDataSource.setAppColorContrast(contrast)
    }

    override suspend fun getAppColorContrast(): AppColorContrast =
        localDataSource.getAppColorContrast()

    override fun observeAppColorContrast(): Flow<AppColorContrast> =
        localDataSource.observeAppColorContrast()

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