package com.sottti.roller.coasters.data.work.manager

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import java.util.concurrent.TimeUnit

internal object RollerCoastersSyncPeriodicWork {
    const val NAME = "periodic_roller_coasters_sync"
    val existingWorkPolicy = ExistingPeriodicWorkPolicy.KEEP
    val request =
        PeriodicWorkRequestBuilder<RollerCoastersSyncWorker>(
            repeatInterval = 72,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        )
            .setConstraints(periodicWorkRequestConstraints())
            .build()
}

private fun periodicWorkRequestConstraints(): Constraints =
    Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .setRequiresCharging(true)
        .setRequiresDeviceIdle(true)
        .build()