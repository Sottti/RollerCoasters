package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toPicturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toRoom
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

internal class RollerCoastersLocalDataSource @Inject constructor(
    private val dao: RollerCoastersDao
) {
    @OptIn(InternalSerializationApi::class)
    suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): Result<RollerCoaster> {
        val rollerCoaster = dao
            .getRollerCoasterById(id.value)
            ?: return Err(Exception("Roller coaster not found in local db"))

        val pictures = dao.getPicturesByRollerCoasterId(id.value)

        return withContext(Dispatchers.Default) {
            Ok(rollerCoaster.toDomain(pictures))
        }
    }

    @OptIn(InternalSerializationApi::class)
    suspend fun storeRollerCoaster(
        rollerCoaster: RollerCoaster,
    ) {
        storeRollerCoasters(listOf(rollerCoaster))
    }

    @OptIn(InternalSerializationApi::class)
    suspend fun storeRollerCoasters(
        rollerCoasters: List<RollerCoaster>,
    ) {
        val rollerCoastersRoomModel = withContext(Dispatchers.Default) {
            rollerCoasters.map { rollerCoaster -> rollerCoaster.toRoom() }
        }

        val picturesRoomModel = withContext(Dispatchers.Default) {
            rollerCoasters.flatMap { rollerCoaster -> rollerCoaster.toPicturesRoomModel() }
        }

        dao.insertRollerCoasters(
            pictures = picturesRoomModel,
            rollerCoasters = rollerCoastersRoomModel,
        )
    }
}