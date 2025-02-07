package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.RollerCoastersApi
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import javax.inject.Inject

internal class RollerCoastersRemoteDataSource @Inject constructor(
    private val api: RollerCoastersApi,
) {
    suspend fun syncAllRollerCoasters(
        onStoreRollerCoasters: suspend (List<RollerCoaster>) -> Unit
    ) {
        var offset = 0
        val limit = 1000
        var totalItems: Int

        do {
            val response = api.getRollerCoasters(offset, limit)
            val rollerCoasters = response.data.map { it.toDomain() }
            if (rollerCoasters.isEmpty()) break
            onStoreRollerCoasters(rollerCoasters)
            totalItems = response.pagination.total
            offset += limit
        } while (offset < totalItems)
    }

    suspend fun getRandomRollerCoaster(): RollerCoaster =
        api
            .getRandomCoaster()
            .toDomain()
}