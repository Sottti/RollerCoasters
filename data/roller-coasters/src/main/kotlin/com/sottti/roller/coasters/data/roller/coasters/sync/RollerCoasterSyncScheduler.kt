package com.sottti.roller.coasters.data.roller.coasters.sync

import androidx.work.WorkManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RollerCoasterSyncScheduler @Inject constructor(
    private val workManager: WorkManager,
) {
    fun schedule() {
        RollerCoastersSyncPeriodicWork.schedule(workManager)
        RollerCoastersSyncUniqueWork.schedule(workManager)
    }
}
