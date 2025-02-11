package com.sottti.roller.coasters.data.work.manager

import android.content.Context
import androidx.startup.Initializer
import com.sottti.roller.coasters.data.work.manager.di.provideScheduler

internal class RollerCoastersSyncInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        provideScheduler(context).scheduleSync()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(androidx.work.WorkManagerInitializer::class.java)
}