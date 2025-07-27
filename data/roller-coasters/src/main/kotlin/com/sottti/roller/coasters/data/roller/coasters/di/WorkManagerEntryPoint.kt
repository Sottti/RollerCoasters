package com.sottti.roller.coasters.data.roller.coasters.di

import androidx.work.WorkerFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface WorkManagerEntryPoint {
    fun getWorkerFactory(): WorkerFactory
}
