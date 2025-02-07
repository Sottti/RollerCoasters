package com.sottti.roller.coasters.data.work.manager.di

import com.sottti.roller.coasters.data.work.manager.schedulers.RollerCoasterSyncScheduler
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface RollerCoastersSyncEntryPoint {
    fun scheduler(): RollerCoasterSyncScheduler
}