package com.sottti.roller.coasters.data.work.manager

import androidx.work.WorkManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RollerCoasterSyncScheduler @Inject constructor(
    private val workManager: WorkManager
) {
    fun scheduleSync() {
        workManager.scheduleUniqueWork()
        workManager.schedulePeriodicWork()
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