package com.sotti.roller.coasters.presentation.settings.data.di

import com.sotti.roller.coasters.presentation.settings.model.SettingsState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object SettingsPresentationModule {

    @Provides
    @ViewModelScoped
    fun provideInitialSettingsState(): SettingsState? = null
}
