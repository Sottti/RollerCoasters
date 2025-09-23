package com.sottti.roller.coasters.data.roller.coasters.sync

import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

internal object RollerCoastersSyncUniqueWork {
    private const val NAME = "unique_roller_coasters_sync"
    private val existingWorkPolicy = ExistingWorkPolicy.KEEP
    private val request =
        OneTimeWorkRequestBuilder<RollerCoastersSyncWorker>()
            .setConstraints(onTimeWorkRequestConstraints())
            .build()

    internal fun schedule(workManager: WorkManager) {
        workManager.enqueueUniqueWork(
            existingWorkPolicy = existingWorkPolicy,
            request = request,
            uniqueWorkName = NAME,
        )
    }
}

private fun onTimeWorkRequestConstraints(): Constraints =
    Constraints
        .Builder()
        .setRequiresDeviceIdle(true)
        .build()
