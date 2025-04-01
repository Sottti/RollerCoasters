package com.sottti.roller.coasters.domain.settings.repository

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import java.util.Locale

public interface SettingsRepository {
    public suspend fun setAppDynamicColor(appDynamicColor: AppDynamicColor)
    public fun observeAppDynamicColor(): Flow<AppDynamicColor>

    public suspend fun setAppTheme(appTheme: AppTheme)
    public suspend fun getAppTheme(): AppTheme
    public fun observeAppTheme(): Flow<AppTheme>

    public suspend fun applyStoredAppTheme()

    public suspend fun setAppColorContrast(contrast: AppColorContrast)
    public suspend fun getAppColorContrast(): AppColorContrast
    public fun observeAppColorContrast(): Flow<AppColorContrast>

    public fun getSystemColorContrast(): SystemColorContrast

    public fun setLanguage(appLanguage: AppLanguage)
    public suspend fun getLanguage(): AppLanguage

    public fun observeAppMeasurementSystem(): Flow<AppMeasurementSystem>
    public suspend fun getAppMeasurementSystem(): AppMeasurementSystem
    public suspend fun setAppMeasurementSystem(appMeasurementSystem: AppMeasurementSystem)

    public fun getSystemMeasurementSystem(): SystemMeasurementSystem

    public fun getDefaultLocale(): Locale
}