package com.sotti.roller.coasters.di.settings.dynamicColor

import com.sotti.roller.coasters.domain.settings.usecase.dynamicColor.ObserveResolvedDynamicColor
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal fun interface ObserveResolvedDynamicColorEntryPoint {
    fun observeResolvedDynamicColor(): ObserveResolvedDynamicColor
}
