package com.sottti.roller.coasters.di.settings

import android.content.Context
import com.sottti.roller.coasters.domain.settings.usecase.GetSystemColorContrast
import dagger.hilt.android.EntryPointAccessors

public fun provideGetSystemColorContrast(
    context: Context,
): GetSystemColorContrast =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = GetSystemColorContrastEntryPoint::class.java,
    ).getSystemColorContrast()