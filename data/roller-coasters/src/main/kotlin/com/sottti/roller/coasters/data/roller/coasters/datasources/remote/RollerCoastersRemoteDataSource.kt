package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.RollerCoastersApi
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RollerCoastersRemoteDataSource @Inject constructor(
    private val api: RollerCoastersApi,
) {
    suspend fun syncAllRollerCoasters(
        onStoreRollerCoasters: suspend (List<RollerCoaster>) -> Unit
    ) {
        val limit = 200
        val totalItems = withContext(Dispatchers.IO) {
            val rollerCoastersPage = api.getRollerCoasters(offset = 0, limit = limit)
            onStoreRollerCoasters(rollerCoastersPage.data.map { it.toDomain() })
            rollerCoastersPage.pagination.total
        }

        val offsets = (limit until totalItems step limit).toList()

        coroutineScope {
            offsets.forEach { offset ->
                launch(Dispatchers.IO) {
                    api.getRollerCoasters(
                        offset = offset,
                        limit = limit
                    ).data
                        .map { it.toDomain() }
                        .also { onStoreRollerCoasters(it) }
                }
            }
        }
    }

    suspend fun getRandomRollerCoaster(): RollerCoaster = api.getRandomCoaster().toDomain()

}