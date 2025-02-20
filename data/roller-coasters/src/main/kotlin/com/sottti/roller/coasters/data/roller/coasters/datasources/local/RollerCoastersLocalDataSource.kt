package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import androidx.paging.PagingSource
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toPicturesRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging.RollerCoastersPagingSource
import com.sottti.roller.coasters.domain.model.NotFound
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

internal class RollerCoastersLocalDataSource @Inject constructor(
    private val dao: RollerCoastersDao,
) {

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
        withContext(Dispatchers.Default) {
            val rollerCoastersRoomModel =
                rollerCoasters.map { rollerCoaster -> rollerCoaster.toRoom() }

            val picturesRoomModel =
                rollerCoasters.flatMap { rollerCoaster -> rollerCoaster.toPicturesRoom() }

            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = rollerCoastersRoomModel,
            )
        }
    }

    @OptIn(InternalSerializationApi::class)
    suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): Result<RollerCoaster> {
        val rollerCoaster = dao
            .getRollerCoaster(id.value)
            ?: return Err(NotFound)

        val pictures = dao.getPictures(id.value)

        return withContext(Dispatchers.Default) {
            Ok(rollerCoaster.toDomain(pictures))
        }
    }

    fun getPagedRollerCoastersSortedByHeight(): PagingSource<Int, RollerCoaster> =
        RollerCoastersPagingSource(dao)
}