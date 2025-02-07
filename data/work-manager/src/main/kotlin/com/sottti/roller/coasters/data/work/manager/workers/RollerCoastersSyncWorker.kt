package com.sottti.roller.coasters.data.work.manager.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
internal class RollerCoastersSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: RollerCoastersRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result =
        try {
            repository.syncAllRollerCoasters()
            Result.success()
        } catch (_: Exception) {
            Result.retry()
        }
}