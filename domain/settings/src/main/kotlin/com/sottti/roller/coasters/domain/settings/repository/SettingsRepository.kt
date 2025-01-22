package com.sottti.roller.coasters.domain.settings.repository

import com.sottti.roller.coasters.domain.settings.model.Theme
import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public suspend fun setDynamicColor(enabled: Boolean)
    public fun observeDynamicColor(): Flow<Boolean>

    public suspend fun setTheme(theme: Theme)
    public suspend fun getTheme(): Theme
    public fun observeTheme(): Flow<Theme>
}