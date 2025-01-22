package com.sottti.roller.coasters.data.settings.di

import android.content.Context
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import dagger.hilt.android.EntryPointAccessors

public fun provideSettingsRepository(
    context: Context,
): SettingsRepository =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = SettingsRepositoryEntryPoint::class.java
    ).getSettingsRepository()