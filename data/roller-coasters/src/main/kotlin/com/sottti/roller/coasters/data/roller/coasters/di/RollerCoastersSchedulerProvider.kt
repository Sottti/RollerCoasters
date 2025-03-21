package com.sottti.roller.coasters.data.roller.coasters.di

import android.content.Context
import com.sottti.roller.coasters.data.roller.coasters.sync.RollerCoasterSyncScheduler
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlin.jvm.java

internal fun provideRollerCoastersSyncScheduler(
    context: Context,
) = EntryPointAccessors.fromApplication(
    context = context,
    entryPoint = RollerCoastersSyncEntryPoint::class.java
).scheduler()

@EntryPoint
@InstallIn(SingletonComponent::class)
private interface RollerCoastersSyncEntryPoint {
    fun scheduler(): RollerCoasterSyncScheduler
}