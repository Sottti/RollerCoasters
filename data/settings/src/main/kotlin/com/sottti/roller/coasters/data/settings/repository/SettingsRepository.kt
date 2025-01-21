package com.sottti.roller.coasters.data.settings.repository

import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public fun observeDynamicColor(): Flow<Boolean>
    public suspend fun setDynamicColor(enabled: Boolean)
}