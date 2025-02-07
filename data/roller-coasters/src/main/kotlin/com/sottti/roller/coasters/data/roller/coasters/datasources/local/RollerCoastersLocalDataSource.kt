package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toPicturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toRoomModel
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

internal class RollerCoastersLocalDataSource @Inject constructor(
    private val dao: RollerCoastersDao
) {
    @OptIn(InternalSerializationApi::class)
    suspend fun storeRollerCoasters(
        rollerCoasters: List<RollerCoaster>,
    ) {
        val rollerCoastersRoomModel =
            rollerCoasters.map { rollerCoaster -> rollerCoaster.toRoomModel() }

        val picturesRoomModel =
            rollerCoasters
                .flatMap { rollerCoaster -> rollerCoaster.toPicturesRoomModel() }

        dao.insertRollerCoastersWithPictures(
            pictures = picturesRoomModel,
            rollerCoasters = rollerCoastersRoomModel,
        )
    }
}