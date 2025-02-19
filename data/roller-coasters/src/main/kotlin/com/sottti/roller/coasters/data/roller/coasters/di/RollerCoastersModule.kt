package com.sottti.roller.coasters.data.roller.coasters.di

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging.RollerCoastersRemoteMediator
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        remoteMediator: RollerCoastersRemoteMediator,
    ): RollerCoastersRepository = RollerCoastersRepositoryImpl(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
        remoteMediator = remoteMediator,
    )
}