package com.sottti.roller.coasters.data.roller.coasters.sync

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import javax.inject.Inject

internal class RollerCoastersSyncWorkerFactory @Inject constructor(
    private val rollerCoasterRepository: RollerCoastersRepository
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? =
        when (workerClassName) {
            RollerCoastersSyncWorker::class.java.name ->
                RollerCoastersSyncWorker(
                    context = appContext,
                    params = workerParameters,
                    rollerCoasterRepository = rollerCoasterRepository,
                )

            else -> null
        }
}