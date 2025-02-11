package com.sottti.roller.coasters.data.roller.coasters.di

import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface RollerCoastersRepositoryEntryPoint {
    fun getRollerCoastersRepository(): RollerCoastersRepository
}