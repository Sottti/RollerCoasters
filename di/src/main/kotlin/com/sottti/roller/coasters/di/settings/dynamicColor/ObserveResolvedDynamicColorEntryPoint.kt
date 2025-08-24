package com.sottti.roller.coasters.di.settings.dynamicColor

import com.sottti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveResolvedDynamicColor
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal fun interface ObserveResolvedDynamicColorEntryPoint {
    fun observeResolvedDynamicColor(): ObserveResolvedDynamicColor
}