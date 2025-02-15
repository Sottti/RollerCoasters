package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.RollerCoastersApiCalls
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper.toDomain
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RollerCoastersRemoteDataSource @Inject constructor(
    private val api: RollerCoastersApiCalls,
) {
    suspend fun getRollerCoaster(id: RollerCoasterId): Result<RollerCoaster> =
        api
            .getRollerCoaster(id)
            .mapBoth(
                success = { rollerCoaster -> Ok(rollerCoaster.toDomain()) },
                failure = { exception -> Err(Exception(exception.message)) },
            )

    suspend fun syncRollerCoasters(
        onStoreRollerCoasters: suspend (List<RollerCoaster>) -> Unit
    ): Result<Unit> {
        var successfulCalls = 0
        var error: Exception? = null
        val limit = 200
        val rollerCoastersPage = api
            .getRollerCoasters(offset = 0, limit = limit)
            .onSuccess { successfulCalls++ }
            .onFailure { return Err(Exception(it.message)) }
            .value

        val totalItems = rollerCoastersPage.pagination.total

        val rollerCoasters = withContext(Dispatchers.Default) {
            rollerCoastersPage.rollerCoasters.map { it.toDomain() }
        }

        onStoreRollerCoasters(rollerCoasters)

        val offsets = (limit until totalItems step limit).toList()
        val expectedSuccessfulCalls = offsets.size + 1
        coroutineScope {
            offsets.forEach { offset ->
                launch {
                    api
                        .getRollerCoasters(offset = offset, limit = limit)
                        .onSuccess { successfulCalls++ }
                        .onFailure { error = Exception(it.message) }
                        .onSuccess { page ->
                            val mappedCoasters = withContext(Dispatchers.Default) {
                                page.rollerCoasters.map { it.toDomain() }
                            }
                            onStoreRollerCoasters(mappedCoasters)
                        }
                }
            }
        }
        return if (successfulCalls == expectedSuccessfulCalls) Ok(Unit)
        else Err(error ?: Exception("Error syncing roller coasters"))
    }
}