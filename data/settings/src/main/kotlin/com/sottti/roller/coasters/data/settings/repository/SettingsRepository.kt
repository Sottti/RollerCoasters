package com.sottti.roller.coasters.data.settings.repository

import com.sottti.roller.coasters.data.settings.model.ColorContrast
import com.sottti.roller.coasters.data.settings.model.Theme
import com.sottti.roller.coasters.utils.device.system.SystemColorContrast
import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public suspend fun setDynamicColor(enabled: Boolean)
    public fun observeDynamicColor(): Flow<Boolean>

    public fun observeTheme(): Flow<Theme>
    public suspend fun applyStoredTheme()
    public suspend fun getTheme(): Theme
    public suspend fun setTheme(theme: Theme)

    public suspend fun setColorContrast(contrast: ColorContrast)
    public suspend fun getColorContrast(): ColorContrast
    public fun observeColorContrast(): Flow<ColorContrast>

    public fun getSystemColorContrast(): SystemColorContrast
}