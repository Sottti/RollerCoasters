package com.sottti.roller.coasters.data.roller.coasters.sync

import android.content.Context
import androidx.work.WorkManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RollerCoasterSyncScheduler @Inject constructor(
    private val context: Context,
) {
    fun schedule() {
        with(receiver = WorkManager.getInstance(context)) {
            RollerCoastersSyncUniqueWork.schedule(this)
            RollerCoastersSyncPeriodicWork.schedule(this)
        }
    }
}
