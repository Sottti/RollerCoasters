package com.sotti.roller.coasters.di.settings.colorContrast

import com.sotti.roller.coasters.domain.settings.usecase.colorContrast.ObserveResolvedColorContrast
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal fun interface ObserveResolvedColorContrastEntryPoint {
    fun observeResolvedColorContrast(): ObserveResolvedColorContrast
}
