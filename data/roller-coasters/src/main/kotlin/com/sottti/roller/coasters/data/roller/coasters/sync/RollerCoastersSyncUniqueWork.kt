package com.sottti.roller.coasters.data.roller.coasters.sync

import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder

internal object RollerCoastersSyncUniqueWork {
    const val NAME = "initial_roller_coasters_sync"
    val existingWorkPolicy = ExistingWorkPolicy.KEEP
    val request =
        OneTimeWorkRequestBuilder<RollerCoastersSyncWorker>()
            .setConstraints(onTimeWorkRequestConstraints())
            .build()
}

private fun onTimeWorkRequestConstraints(): Constraints =
    Constraints.Builder().build()