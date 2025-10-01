package com.sottti.roller.coasters.data.roller.coasters.di

import android.content.Context
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl
import com.sottti.roller.coasters.data.roller.coasters.sync.RollerCoastersSyncWorkerFactory
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RollerCoastersDataModule {

    @Binds
    @Singleton
    fun bindRepository(
        impl: RollerCoastersRepositoryImpl,
    ): RollerCoastersRepository

    @Binds
    @Singleton
    fun bindWorkerFactory(
        factory: RollerCoastersSyncWorkerFactory,
    ): WorkerFactory

    companion object {
        @Provides
        @Singleton
        fun provideWorkManager(
            @ApplicationContext context: Context,
        ): WorkManager = WorkManager.getInstance(context)
    }
}
