package com.sottti.roller.coasters.data.roller.coasters.sync

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

internal object RollerCoastersSyncPeriodicWork {
    private const val NAME = "periodic_roller_coasters_sync"
    private val existingWorkPolicy = ExistingPeriodicWorkPolicy.KEEP
    private val request =
        PeriodicWorkRequestBuilder<RollerCoastersSyncWorker>(
            repeatInterval = 72,
            repeatIntervalTimeUnit = TimeUnit.HOURS,
        )
            .setConstraints(periodicWorkRequestConstraints())
            .build()

    internal fun schedule(workManager: WorkManager) {
        workManager.enqueueUniquePeriodicWork(
            existingPeriodicWorkPolicy = existingWorkPolicy,
            request = request,
            uniqueWorkName = NAME,
        )
    }
}

private fun periodicWorkRequestConstraints(): Constraints =
    Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .setRequiresCharging(true)
        .setRequiresDeviceIdle(true)
        .build()
