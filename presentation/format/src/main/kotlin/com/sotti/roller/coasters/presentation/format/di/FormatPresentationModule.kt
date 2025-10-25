package com.sotti.roller.coasters.presentation.format.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
public object FormatPresentationModule {

    @Provides
    public fun provideLocale(
        @ApplicationContext context: Context,
    ): Locale = context.resources.configuration.locales[0]
}
