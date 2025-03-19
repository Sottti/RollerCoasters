package com.sottti.roller.coasters.domain.settings.di

import android.content.Context
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveColorContrast
import dagger.hilt.android.EntryPointAccessors

public fun provideObserveColorContrast(
    context: Context,
): ObserveColorContrast =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = ObserveColorContrastEntryPoint::class.java,
    ).observeColorContrast()