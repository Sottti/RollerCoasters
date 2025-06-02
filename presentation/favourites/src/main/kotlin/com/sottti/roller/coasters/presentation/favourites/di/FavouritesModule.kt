package com.sottti.roller.coasters.presentation.favourites.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(ViewModelComponent::class)
internal object FavouritesModule {

    @Provides
    fun provideCoroutineScope(): CoroutineScope? = null
}