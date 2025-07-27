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
            scheduleUniqueWork()
            schedulePeriodicWork()
        }
    }
}

private fun WorkManager.schedulePeriodicWork() {
    enqueueUniquePeriodicWork(
        existingPeriodicWorkPolicy = RollerCoastersSyncPeriodicWork.existingWorkPolicy,
        request = RollerCoastersSyncPeriodicWork.request,
        uniqueWorkName = RollerCoastersSyncPeriodicWork.NAME,
    )
}

private fun WorkManager.scheduleUniqueWork() {
    enqueueUniqueWork(
        existingWorkPolicy = RollerCoastersSyncUniqueWork.existingWorkPolicy,
        request = RollerCoastersSyncUniqueWork.request,
        uniqueWorkName = RollerCoastersSyncUniqueWork.NAME,
    )
}
