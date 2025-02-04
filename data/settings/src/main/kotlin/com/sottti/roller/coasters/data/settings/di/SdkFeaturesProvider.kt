package com.sottti.roller.coasters.data.settings.di

import android.content.Context
import com.sottti.roller.coasters.utils.device.sdk.SdkFeatures
import dagger.hilt.android.EntryPointAccessors

public fun provideSdkFeatures(
    context: Context,
): SdkFeatures =
    EntryPointAccessors.fromApplication(
        context = context.applicationContext,
        entryPoint = SdkFeaturesEntryPoint::class.java,
    ).getSdkFeatures()