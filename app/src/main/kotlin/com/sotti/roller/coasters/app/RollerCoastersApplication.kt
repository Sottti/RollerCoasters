package com.sotti.roller.coasters.app

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
internal class RollerCoastersApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration
            .Builder()
            .setWorkerFactory(hiltWorkerFactory)
            .build()
}
