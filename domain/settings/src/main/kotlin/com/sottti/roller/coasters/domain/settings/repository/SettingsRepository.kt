package com.sottti.roller.coasters.domain.settings.repository

import com.sottti.roller.coasters.domain.settings.model.Theme
import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public suspend fun setDynamicColor(enabled: Boolean)
    public fun observeDynamicColor(): Flow<Boolean>

    public fun observeAppTheme(): Flow<Theme>
    public suspend fun applyStoredAppTheme()
    public suspend fun getAppTheme(): Theme
    public suspend fun setAppTheme(theme: Theme)
}