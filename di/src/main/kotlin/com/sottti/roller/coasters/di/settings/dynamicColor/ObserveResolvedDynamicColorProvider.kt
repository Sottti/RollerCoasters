package com.sottti.roller.coasters.di.settings.dynamicColor

import android.content.Context
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveResolvedDynamicColor
import dagger.hilt.android.EntryPointAccessors

public fun provideObserveResolvedDynamicColor(
    context: Context,
): ObserveResolvedDynamicColor =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = ObserveResolvedDynamicColorEntryPoint::class.java,
    ).observeResolvedDynamicColor()