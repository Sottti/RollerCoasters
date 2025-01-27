package com.sottti.roller.coasters.utils.device.di

import com.sottti.roller.coasters.utils.device.system.SystemSettings
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface SystemSettingsEntryPoint {
    fun getSystemSettings(): SystemSettings
}