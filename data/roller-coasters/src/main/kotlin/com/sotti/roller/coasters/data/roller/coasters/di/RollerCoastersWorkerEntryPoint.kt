package com.sotti.roller.coasters.data.roller.coasters.di

import com.sotti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal fun interface RollerCoastersWorkerEntryPoint {
    fun rollerCoastersRepository(): RollerCoastersRepository
}
