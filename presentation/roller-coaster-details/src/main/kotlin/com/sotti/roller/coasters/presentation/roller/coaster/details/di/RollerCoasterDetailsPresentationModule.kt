package com.sotti.roller.coasters.presentation.roller.coaster.details.di

import androidx.lifecycle.SavedStateHandle
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sotti.roller.coasters.presentation.navigation.NavigationDestination.RollerCoasterDetails.Companion.KEY_ROLLER_COASTER_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object RollerCoasterDetailsPresentationModule {

    @Provides
    fun provideRollerCoasterId(
        savedStateHandle: SavedStateHandle,
    ): RollerCoasterId {
        val id: Int = savedStateHandle[KEY_ROLLER_COASTER_ID]
            ?: throw IllegalArgumentException("A roller coaster id is required")
        return RollerCoasterId(id)
    }
}
