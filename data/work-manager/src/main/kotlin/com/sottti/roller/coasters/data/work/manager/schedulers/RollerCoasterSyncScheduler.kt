package com.sottti.roller.coasters.data.work.manager.schedulers

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.sottti.roller.coasters.data.work.manager.workers.RollerCoastersSyncWorker
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.concurrent.TimeUnit

@Singleton
internal class RollerCoasterSyncScheduler @Inject constructor(
    private val workManager: WorkManager
) {
    fun scheduleSync() {
        workManager.enqueueUniquePeriodicWork(
            uniqueWorkName = ROLLER_COASTERS_SYNC_WORK_NAME,
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
            request = rollerCoastersSyncWorkRequest(),
        )
    }
}

private fun rollerCoastersSyncWorkRequest(): PeriodicWorkRequest =
    PeriodicWorkRequestBuilder<RollerCoastersSyncWorker>(
        repeatInterval = 6,
        repeatIntervalTimeUnit = TimeUnit.HOURS
    )
        .setConstraints(constraints)
        .build()

private const val ROLLER_COASTERS_SYNC_WORK_NAME: String = "sync_all_roller_coasters"

private val constraints =
    Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build()