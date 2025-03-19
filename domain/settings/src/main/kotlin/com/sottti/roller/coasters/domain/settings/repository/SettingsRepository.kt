package com.sottti.roller.coasters.domain.settings.repository

import com.sottti.roller.coasters.domain.model.ColorContrast
import com.sottti.roller.coasters.domain.model.Language
import com.sottti.roller.coasters.domain.model.MeasurementSystem
import com.sottti.roller.coasters.domain.model.SystemColorContrast
import com.sottti.roller.coasters.domain.model.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.model.Theme
import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public suspend fun setDynamicColor(enabled: Boolean)
    public fun observeDynamicColor(): Flow<Boolean>

    public suspend fun setTheme(theme: Theme)
    public suspend fun getTheme(): Theme
    public fun observeTheme(): Flow<Theme>

    public suspend fun applyStoredTheme()

    public suspend fun setColorContrast(contrast: ColorContrast)
    public suspend fun getColorContrast(): ColorContrast
    public fun observeColorContrast(): Flow<ColorContrast>

    public fun getSystemColorContrast(): SystemColorContrast

    public fun setLanguage(language: Language)
    public suspend fun getLanguage(): Language

    public fun observeMeasurementSystem(): Flow<MeasurementSystem>
    public suspend fun getMeasurementSystem(): MeasurementSystem
    public suspend fun setMeasurementSystem(measurementSystem: MeasurementSystem)

    public fun getSystemMeasurementSystem(): SystemMeasurementSystem
}