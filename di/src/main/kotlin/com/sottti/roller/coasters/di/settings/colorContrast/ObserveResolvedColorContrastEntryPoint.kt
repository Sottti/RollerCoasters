package com.sottti.roller.coasters.di.settings.colorContrast

import com.sottti.roller.coasters.domain.settings.usecase.colorContrast.ObserveResolvedColorContrast
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface ObserveResolvedColorContrastEntryPoint {
    fun observeColorContrast(): ObserveResolvedColorContrast
}