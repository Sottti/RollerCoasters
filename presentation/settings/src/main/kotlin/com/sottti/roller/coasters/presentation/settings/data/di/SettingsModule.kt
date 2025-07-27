package com.sottti.roller.coasters.presentation.settings.data.di

import com.sottti.roller.coasters.presentation.settings.model.SettingsState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object SettingsModule {

    @Provides
    @ViewModelScoped
    fun provideInitialSettingsState(): SettingsState? = null
}
