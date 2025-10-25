package com.sotti.roller.coasters.data.settings.repository

import com.sotti.roller.coasters.data.settings.datasource.SettingsLocalDataSource
import com.sotti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sotti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sotti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sotti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sotti.roller.coasters.domain.settings.model.theme.SystemTheme
import com.sotti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import java.util.Locale
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
) : SettingsRepository {

    override suspend fun setAppDynamicColor(appDynamicColor: AppDynamicColor) {
        localDataSource.setAppDynamicColor(appDynamicColor)
    }

    override suspend fun getAppDynamicColor(): AppDynamicColor =
        localDataSource.getAppDynamicColor()

    override fun observeAppDynamicColor(): Flow<AppDynamicColor> =
        localDataSource.observeAppDynamicColor()

    override suspend fun setAppTheme(appTheme: AppTheme) {
        localDataSource.setAppTheme(appTheme)
    }

    override fun observeSystemTheme(): Flow<SystemTheme> =
        localDataSource.observeSystemTheme()

    override fun getSystemTheme(): SystemTheme =
        localDataSource.getSystemTheme()

    override suspend fun getAppTheme(): AppTheme = localDataSource.getAppTheme()

    override fun observeAppTheme(): Flow<AppTheme> = localDataSource.observeAppTheme()

    override suspend fun applyStoredAppTheme() {
        localDataSource.applyStoredAppTheme()
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

    override fun setAppLanguage(appLanguage: AppLanguage) {
        localDataSource.setAppLanguage(appLanguage)
    }

    override suspend fun getAppLanguage(): AppLanguage =
        localDataSource.getAppLanguage()

    override fun observeAppLanguage(): Flow<AppLanguage> =
        localDataSource.observeAppLanguage()

    override fun observeSystemLocale(): Flow<Locale> =
        localDataSource.observeSystemLocale()

    override suspend fun setAppMeasurementSystem(appMeasurementSystem: AppMeasurementSystem) {
        localDataSource.setAppMeasurementSystem(appMeasurementSystem)
    }

    override suspend fun getAppMeasurementSystem(): AppMeasurementSystem =
        localDataSource.getAppMeasurementSystem()

    override fun observeAppMeasurementSystem(): Flow<AppMeasurementSystem> =
        localDataSource.observeAppMeasurementSystem()

    override fun getSystemMeasurementSystem(): SystemMeasurementSystem =
        localDataSource.getSystemMeasurementSystem()
}
