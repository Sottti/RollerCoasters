package com.sottti.roller.coasters.data.network.di

import android.content.Context
import dagger.hilt.android.EntryPointAccessors
import io.ktor.client.HttpClient

public fun provideHttpClient(
    context: Context,
): HttpClient =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = NetworkEntryPoint::class.java,
    ).getHttpClient()