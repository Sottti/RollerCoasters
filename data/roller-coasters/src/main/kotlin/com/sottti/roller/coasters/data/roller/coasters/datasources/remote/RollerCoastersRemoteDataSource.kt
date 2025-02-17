package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.RollerCoastersApiCalls
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl.Companion.PAGE_SIZE
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
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
    suspend fun getRollerCoastersPage(
        pageNumber: PageNumber,
    ): Result<List<RollerCoaster>> {
        val limit = PAGE_SIZE
        val offset = pageNumber * limit

        return api.getRollerCoasters(offset, PAGE_SIZE).mapBoth(
            success = { page -> Ok(page.rollerCoasters.map { it.toDomain() }) },
            failure = { exception -> Err(exception) }
        )
    }

    suspend fun getRollerCoaster(id: RollerCoasterId): Result<RollerCoaster> =
        api
            .getRollerCoaster(id)
            .mapBoth(
                success = { rollerCoaster -> Ok(rollerCoaster.toDomain()) },
                failure = { exception -> Err(exception) },
            )

    suspend fun syncRollerCoasters(
        onStoreRollerCoasters: suspend (List<RollerCoaster>) -> Unit
    ): Result<Unit> {
        val limit = PAGE_SIZE
        var successfulCalls = 0
        val rollerCoastersPage = api
            .getRollerCoasters(offset = 0, limit = limit)
            .onSuccess { successfulCalls++ }
            .onFailure { exception -> return Err(exception) }
            .value

        val totalItems = rollerCoastersPage.pagination.total

        val rollerCoasters = withContext(Dispatchers.Default) {
            rollerCoastersPage.rollerCoasters.map { it.toDomain() }
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
                            .onFailure { error = it }
                    }
                    result.mapBoth(
                        success = { page ->
                            val mappedCoasters = withContext(Dispatchers.Default) {
                                page.rollerCoasters.map { it.toDomain() }
                            }
                            onStoreRollerCoasters(mappedCoasters)
                            Ok(Unit)
                        },
                        failure = { exception -> Err(exception) }
                    )
                }
            }

            val results = deferredResults.awaitAll()
            results.firstOrNull { it.isErr }?.let { return@coroutineScope it }

            if (successfulCalls == expectedSuccessfulCalls) Ok(Unit)
            else Err(error ?: Exception("Error syncing roller coasters"))
        }
    }
}