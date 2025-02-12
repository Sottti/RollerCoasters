package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toPicturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toRoom
import com.sottti.roller.coasters.domain.model.Id
import com.sottti.roller.coasters.domain.model.RollerCoaster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

internal class RollerCoastersLocalDataSource @Inject constructor(
    private val dao: RollerCoastersDao
) {
    @OptIn(InternalSerializationApi::class)
    suspend fun storeRollerCoasters(
        rollerCoasters: List<RollerCoaster>,
    ) {
        withContext(Dispatchers.IO) {
            val rollerCoastersRoomModel =
                rollerCoasters.map { rollerCoaster -> rollerCoaster.toRoom() }

            val picturesRoomModel =
                rollerCoasters
                    .flatMap { rollerCoaster -> rollerCoaster.toPicturesRoomModel() }

            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = rollerCoastersRoomModel,
            )
        }
    }

    @OptIn(InternalSerializationApi::class)
    suspend fun getRollerCoasterById(
        id: Id,
    ): RollerCoaster? =
        withContext(Dispatchers.IO) {
            dao
                .getRollerCoasterById(id.value)
                ?.toDomain(dao.getPicturesByRollerCoasterId(id.value))
        }
}