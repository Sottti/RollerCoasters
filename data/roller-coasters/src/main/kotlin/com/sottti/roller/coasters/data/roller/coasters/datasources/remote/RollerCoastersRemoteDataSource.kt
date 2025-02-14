package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
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
        val limit = 200
        val rollerCoastersPage = api
            .getRollerCoasters(offset = 0, limit = limit)
            .value

        val totalItems = rollerCoastersPage.pagination.total

        val rollerCoasters = withContext(Dispatchers.Default) {
            rollerCoastersPage.rollerCoasters.map { it.toDomain() }
        }

        onStoreRollerCoasters(rollerCoasters)

        val offsets = (limit until totalItems step limit).toList()
        coroutineScope {
            offsets.forEach { offset ->
                launch {
                    val additionalCoasters = api
                        .getRollerCoasters(offset = offset, limit = limit)
                        .value
                        .rollerCoasters

                    val mappedCoasters = withContext(Dispatchers.Default) {
                        additionalCoasters.map { it.toDomain() }
                    }
                    onStoreRollerCoasters(mappedCoasters)
                }
            }
        }
        return Ok(Unit)
    }
}