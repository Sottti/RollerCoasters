package com.sotti.roller.coasters.data.network.di

import com.sotti.roller.coasters.data.network.createHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkDataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = createHttpClient()
}
