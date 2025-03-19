package com.sottti.roller.coasters.domain.roller.coasters.di

import com.sottti.roller.coasters.domain.roller.coasters.usecase.SyncAllRollerCoasters
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface ProvideSyncAllRollerCoastersEntryPoint {
    fun syncAllRollerCoasters(): SyncAllRollerCoasters
}