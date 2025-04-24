package com.sottti.roller.coasters.presentation.roller.coaster.details.di

import androidx.lifecycle.SavedStateHandle
import com.sottti.roller.coasters.presentation.navigation.NavigationDestination.RollerCoasterDetails.Companion.KEY_ROLLER_COASTER_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object RollerCoasterDetailsModule {

    @Provides
    fun provideRollerCoasterId(savedStateHandle: SavedStateHandle): Int =
        savedStateHandle[KEY_ROLLER_COASTER_ID]
            ?: throw IllegalArgumentException("rollerCoasterId is required")
}