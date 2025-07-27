package com.sottti.roller.coasters.data.roller.coasters.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository

internal class RollerCoastersSyncWorker(
    context: Context,
    params: WorkerParameters,
    private val rollerCoasterRepository: RollerCoastersRepository,
) : CoroutineWorker(appContext = context, params = params) {

    override suspend fun doWork(): Result = when {
        rollerCoasterRepository.syncAllRollerCoasters().isOk -> Result.success()
        else -> Result.retry()
    }
}
