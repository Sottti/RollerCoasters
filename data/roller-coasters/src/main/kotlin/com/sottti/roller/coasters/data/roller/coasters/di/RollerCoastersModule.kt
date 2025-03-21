package com.sottti.roller.coasters.data.roller.coasters.di

import android.content.Context
import androidx.work.WorkManager
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RollerCoastersModule {

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: RollerCoastersLocalDataSource,
        remoteDataSource: RollerCoastersRemoteDataSource,
    ): RollerCoastersRepository = RollerCoastersRepositoryImpl(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
    )

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context,
    ): WorkManager = WorkManager.getInstance(context)
}