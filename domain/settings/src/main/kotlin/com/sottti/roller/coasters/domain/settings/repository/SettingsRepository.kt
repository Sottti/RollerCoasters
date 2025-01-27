package com.sottti.roller.coasters.domain.settings.repository

import com.sottti.roller.coasters.domain.settings.model.Theme
import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public suspend fun setDynamicColor(enabled: Boolean)
    public fun observeDynamicColor(): Flow<Boolean>

    public fun observeTheme(): Flow<Theme>
    public suspend fun applyStoredTheme()
    public suspend fun getColorContrast(): ColorContrast
    public suspend fun getTheme(): Theme
    public suspend fun setTheme(theme: Theme)
}