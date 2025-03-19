package com.sottti.roller.coasters.data.roller.coasters.di

import android.content.Context
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import dagger.hilt.android.EntryPointAccessors

public fun provideRollerCoastersRepository(
    context: Context,
): RollerCoastersRepository =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = RollerCoastersRepositoryEntryPoint::class.java,
    ).getRollerCoastersRepository()