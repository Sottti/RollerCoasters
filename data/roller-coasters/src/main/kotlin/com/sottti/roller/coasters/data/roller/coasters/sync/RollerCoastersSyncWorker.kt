package com.sottti.roller.coasters.data.roller.coasters.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sottti.roller.coasters.data.roller.coasters.di.RollerCoastersWorkerEntryPoint
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoints

@HiltWorker
internal class RollerCoastersSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) : CoroutineWorker(appContext = context, params = params) {

    private val rollerCoasterRepository: RollerCoastersRepository by lazy {
        EntryPoints.get(
            context,
            RollerCoastersWorkerEntryPoint::class.java
        ).rollerCoastersRepository()
    }

    override suspend fun doWork(): Result = when {
        rollerCoasterRepository.syncAllRollerCoasters().isOk -> Result.success()
        else -> Result.retry()
    }
}
