package com.sottti.roller.coasters.domain.settings.di

import android.content.Context
import com.sottti.roller.coasters.domain.settings.usecase.ObserveDynamicColor
import dagger.hilt.android.EntryPointAccessors

public fun provideObserveDynamicColor(
    context: Context,
): ObserveDynamicColor =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = ObserveDynamicColorEntryPoint::class.java,
    ).observeDynamicColor()