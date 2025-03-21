package com.sottti.roller.coasters.domain.settings.repository

import com.sottti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sottti.roller.coasters.domain.settings.model.colorContrast.SystemColorContrast
import com.sottti.roller.coasters.domain.settings.model.dynamicColor.AppDynamicColor
import com.sottti.roller.coasters.domain.settings.model.language.Language
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.theme.Theme
import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public suspend fun setAppDynamicColor(appDynamicColor: AppDynamicColor)
    public fun observeAppDynamicColor(): Flow<AppDynamicColor>

    public suspend fun setTheme(theme: Theme)
    public suspend fun getTheme(): Theme
    public fun observeTheme(): Flow<Theme>

    public suspend fun applyStoredTheme()

    public suspend fun setAppColorContrast(contrast: AppColorContrast)
    public suspend fun getAppColorContrast(): AppColorContrast
    public fun observeAppColorContrast(): Flow<AppColorContrast>

    public fun getSystemColorContrast(): SystemColorContrast

    public fun setLanguage(language: Language)
    public suspend fun getLanguage(): Language

    public fun observeMeasurementSystem(): Flow<MeasurementSystem>
    public suspend fun getMeasurementSystem(): MeasurementSystem
    public suspend fun setMeasurementSystem(measurementSystem: MeasurementSystem)

    public fun getSystemMeasurementSystem(): SystemMeasurementSystem
}