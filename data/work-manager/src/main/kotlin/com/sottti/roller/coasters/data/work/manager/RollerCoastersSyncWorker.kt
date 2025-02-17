package com.sottti.roller.coasters.data.work.manager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sottti.roller.coasters.data.roller.coasters.di.provideRollerCoastersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
internal class RollerCoastersSyncWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result =
        when {
            provideRollerCoastersRepository(context)
                .syncAllRollerCoasters()
                .isOk -> Result.success()

            else -> Result.retry()
        }
}