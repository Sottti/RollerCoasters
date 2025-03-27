package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.RollerCoastersApiCalls
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper.toDomain
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RollerCoastersRemoteDataSource @Inject constructor(
    private val api: RollerCoastersApiCalls,
) {
    suspend fun getRollerCoaster(
        id: RollerCoasterId,
        measurementSystem: SystemMeasurementSystem,
    ): Result<RollerCoaster> =
        api
            .getRollerCoaster(id)
            .mapBoth(
                success = { rollerCoaster -> Ok(rollerCoaster.toDomain(measurementSystem)) },
                failure = { exception -> Err(exception) },
            )

    suspend fun syncRollerCoasters(
        onStoreRollerCoasters: suspend (List<RollerCoaster>) -> Unit,
    ): Result<Unit> {
        val limit = 250
        var successfulCalls = 0
        val rollerCoastersPage = api
            .getRollerCoasters(offset = 0, limit = limit)
            .onSuccess { successfulCalls++ }
            .onFailure { exception -> return Err(exception) }
            .value

        val totalItems = rollerCoastersPage.pagination.total

        val rollerCoasters = withContext(Dispatchers.Default) {
            rollerCoastersPage.rollerCoasters.map { it.toDomain(Metric) }
        }
        onStoreRollerCoasters(rollerCoasters)

        val offsets = (limit until totalItems step limit).toList()
        val expectedSuccessfulCalls = offsets.size + 1

        val mutex = Mutex()
        var error: Exception? = null

        return coroutineScope {
            val deferredResults = offsets.map { offset ->
                async {
                    val result = api.getRollerCoasters(offset = offset, limit = limit)
                    mutex.withLock {
                        result
                            .onSuccess { successfulCalls++ }
                            .onFailure { exceptionApi -> error = exceptionApi }
                    }
                    result.mapBoth(
                        success = { page ->
                            val mappedCoasters = withContext(Dispatchers.Default) {
                                page.rollerCoasters.map { it.toDomain(Metric) }
                            }
                            onStoreRollerCoasters(mappedCoasters)
                            Ok(Unit)
                        },
                        failure = { exception -> Err(exception) }
                    )
                }
            }

            val results = deferredResults.awaitAll()
            results
                .firstOrNull { result -> result.isErr }
                ?.let { result -> return@coroutineScope result }

            if (successfulCalls == expectedSuccessfulCalls) Ok(Unit)
            else Err(error ?: Exception("Error syncing roller coasters"))
        }
    }
}