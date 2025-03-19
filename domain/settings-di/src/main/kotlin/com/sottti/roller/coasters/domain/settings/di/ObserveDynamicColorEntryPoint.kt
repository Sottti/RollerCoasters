package com.sottti.roller.coasters.domain.settings.di

import com.sottti.roller.coasters.domain.settings.usecase.ObserveDynamicColor
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface ObserveDynamicColorEntryPoint {
    fun observeDynamicColor(): ObserveDynamicColor
}