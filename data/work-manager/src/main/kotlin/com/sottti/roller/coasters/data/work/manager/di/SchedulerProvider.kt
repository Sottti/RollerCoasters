package com.sottti.roller.coasters.data.work.manager.di

import android.content.Context
import dagger.hilt.android.EntryPointAccessors

internal fun provideScheduler(
    context: Context,
) = EntryPointAccessors.fromApplication(
    context,
    RollerCoastersSyncEntryPoint::class.java
).scheduler()