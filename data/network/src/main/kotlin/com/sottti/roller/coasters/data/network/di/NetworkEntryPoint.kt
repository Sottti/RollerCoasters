package com.sottti.roller.coasters.data.network.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface NetworkEntryPoint {
    fun getHttpClient(): HttpClient
}