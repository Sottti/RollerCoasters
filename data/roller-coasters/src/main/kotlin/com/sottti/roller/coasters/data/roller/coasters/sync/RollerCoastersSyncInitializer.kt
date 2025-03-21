package com.sottti.roller.coasters.data.roller.coasters.sync

import android.content.Context
import androidx.startup.Initializer
import androidx.work.WorkManagerInitializer
import com.sottti.roller.coasters.data.roller.coasters.di.provideRollerCoastersSyncScheduler
import kotlin.jvm.java

internal class RollerCoastersSyncInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        provideRollerCoastersSyncScheduler(context).scheduleSync()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(WorkManagerInitializer::class.java)
}