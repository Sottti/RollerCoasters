package com.sottti.roller.coasters.data.work.manager

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RollerCoasterSyncScheduler @Inject constructor(
    private val workManager: WorkManager
) {

    fun scheduleSync() {
        workManager.enqueueUniqueWork(
            uniqueWorkName = INITIAL_ROLLER_COASTERS_SYNC,
            existingWorkPolicy = ExistingWorkPolicy.KEEP,
            request = oneTimeWorkRequest,
        )

        workManager.enqueueUniquePeriodicWork(
            uniqueWorkName = PERIODIC_ROLLER_COASTERS_SYNC,
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
            request = periodicWorkRequest,
        )
    }
}

private const val INITIAL_ROLLER_COASTERS_SYNC = "initial_roller_coasters_sync"
private const val PERIODIC_ROLLER_COASTERS_SYNC = "periodic_roller_coasters_sync"

private val oneTimeWorkRequest =
    OneTimeWorkRequestBuilder<RollerCoastersSyncWorker>()
        .setConstraints(constraints())
        .build()

private val periodicWorkRequest =
    PeriodicWorkRequestBuilder<RollerCoastersSyncWorker>(
        repeatInterval = 6,
        repeatIntervalTimeUnit = TimeUnit.HOURS
    )
        .setConstraints(constraints())
        .build()

private fun constraints(): Constraints = Constraints.Builder()
    .setRequiresBatteryNotLow(true)
    .build()