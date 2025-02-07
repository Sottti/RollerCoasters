package com.sottti.roller.coasters.data.work.manager

import android.content.Context
import androidx.startup.Initializer
import com.sottti.roller.coasters.data.work.manager.di.RollerCoastersSyncEntryPoint
import dagger.hilt.android.EntryPointAccessors

internal class RollerCoastersSyncInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        val entryPoint = EntryPointAccessors.fromApplication(
            context = context,
            entryPoint = RollerCoastersSyncEntryPoint::class.java
        )
        val scheduler = entryPoint.scheduler()
        scheduler.scheduleSync()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(androidx.work.WorkManagerInitializer::class.java)
}