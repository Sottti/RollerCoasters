package com.sottti.roller.coasters.domain.settings.di.dynamicColor

import android.content.Context
import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveDynamicColor
import dagger.hilt.android.EntryPointAccessors

public fun provideObserveDynamicColor(
    context: Context,
): ObserveDynamicColor =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = ObserveDynamicColorEntryPoint::class.java,
    ).observeDynamicColor()