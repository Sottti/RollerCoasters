package com.sottti.roller.coasters.di.settings.colorContrast

import android.content.Context
import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveResolvedColorContrast
import dagger.hilt.android.EntryPointAccessors

public fun provideObserveResolvedColorContrast(
    context: Context,
): ObserveResolvedColorContrast =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = ObserveResolvedColorContrastEntryPoint::class.java,
    ).observeColorContrast()