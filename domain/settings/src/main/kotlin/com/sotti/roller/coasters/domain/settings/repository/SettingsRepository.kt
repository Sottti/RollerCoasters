package com.sotti.roller.coasters.domain.settings.repository

import com.sotti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sotti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sotti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sotti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sotti.roller.coasters.domain.settings.model.theme.AppTheme
import com.sotti.roller.coasters.domain.settings.model.theme.SystemTheme
import kotlinx.coroutines.flow.Flow
import java.util.Locale

public interface SettingsRepository {
    public fun observeAppDynamicColor(): Flow<AppDynamicColor>
    public suspend fun getAppDynamicColor(): AppDynamicColor
    public suspend fun setAppDynamicColor(appDynamicColor: AppDynamicColor)

    public fun observeAppTheme(): Flow<AppTheme>
    public suspend fun getAppTheme(): AppTheme
    public suspend fun setAppTheme(appTheme: AppTheme)

    public fun observeSystemTheme(): Flow<SystemTheme>
    public fun getSystemTheme(): SystemTheme

    public suspend fun applyStoredAppTheme()

    public fun observeAppColorContrast(): Flow<AppColorContrast>
    public suspend fun getAppColorContrast(): AppColorContrast
    public suspend fun setAppColorContrast(contrast: AppColorContrast)

    public fun getSystemColorContrast(): SystemColorContrast

    public fun observeAppLanguage(): Flow<AppLanguage>
    public fun setAppLanguage(appLanguage: AppLanguage)
    public suspend fun getAppLanguage(): AppLanguage

    public fun observeSystemLocale(): Flow<Locale>

    public fun observeAppMeasurementSystem(): Flow<AppMeasurementSystem>
    public suspend fun getAppMeasurementSystem(): AppMeasurementSystem
    public suspend fun setAppMeasurementSystem(appMeasurementSystem: AppMeasurementSystem)

    public fun getSystemMeasurementSystem(): SystemMeasurementSystem
}
