package com.sottti.roller.coasters.domain.settings.di

import com.sottti.roller.coasters.domain.settings.usecase.ObserveColorContrast
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface ObserveColorContrastEntryPoint {
    fun observeColorContrast(): ObserveColorContrast
}