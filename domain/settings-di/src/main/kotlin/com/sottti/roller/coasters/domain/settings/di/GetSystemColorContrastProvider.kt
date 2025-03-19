package com.sottti.roller.coasters.domain.settings.di

import android.content.Context
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.GetSystemColorContrast
import dagger.hilt.android.EntryPointAccessors

public fun provideGetSystemColorContrast(
    context: Context,
): GetSystemColorContrast =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = GetSystemColorContrastEntryPoint::class.java,
    ).getSystemColorContrast()