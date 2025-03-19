package com.sottti.roller.coasters.domain.roller.coasters.di

import android.content.Context
import com.sottti.roller.coasters.domain.roller.coasters.usecase.SyncAllRollerCoasters
import dagger.hilt.android.EntryPointAccessors

public fun provideSyncAllRollerCoasters(
    context: Context,
): SyncAllRollerCoasters =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = ProvideSyncAllRollerCoastersEntryPoint::class.java,
    ).syncAllRollerCoasters()