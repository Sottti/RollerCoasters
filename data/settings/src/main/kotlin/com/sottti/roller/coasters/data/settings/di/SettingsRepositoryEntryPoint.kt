package com.sottti.roller.coasters.data.settings.di

import com.sottti.roller.coasters.data.settings.repository.SettingsRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface SettingsRepositoryEntryPoint {
    fun getSettingsRepository(): SettingsRepository
}