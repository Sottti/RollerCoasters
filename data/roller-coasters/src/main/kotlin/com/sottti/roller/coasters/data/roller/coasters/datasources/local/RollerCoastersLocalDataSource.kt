package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.unwrap
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters.ListConverters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toPaginatedRollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toPicturesRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toRoom
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl.Companion.PAGE_SIZE
import com.sottti.roller.coasters.domain.model.NotFound
import com.sottti.roller.coasters.domain.model.PageNumber
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
    suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): Result<RollerCoaster> {
        val rollerCoaster = dao
            .getRollerCoasterById(id.value)
            ?: return Err(NotFound)

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
        pageNumber: PageNumber? = null,
    ) {
        val rollerCoastersRoomModel = withContext(Dispatchers.Default) {
            rollerCoasters.map { rollerCoaster -> rollerCoaster.toRoom() }
        }
        val picturesRoomModel = withContext(Dispatchers.Default) {
            rollerCoasters.flatMap { rollerCoaster -> rollerCoaster.toPicturesRoom() }
        }
        dao.insertRollerCoasters(
            pictures = picturesRoomModel,
            rollerCoasters = rollerCoastersRoomModel,
        )
        pageNumber?.let { pageNumber ->
            val paginatedRollerCoasters = withContext(Dispatchers.Default) {
                rollerCoasters.toPaginatedRollerCoastersRoom(pageNumber)
            }
            dao.insertPaginatedRollerCoasters(paginatedRollerCoasters)
        }
    }

    @OptIn(InternalSerializationApi::class)
    suspend fun getRollerCoasters(
        pageNumber: PageNumber,
        pageSize: Int = PAGE_SIZE,
    ): Result<List<RollerCoaster>> =
        getRollerCoasterIds(pageNumber, pageSize).mapBoth(
            success = { coasterIds -> fetchRollerCoastersOrFail(coasterIds, pageSize) },
            failure = { exception -> Err(exception) }
        )

    private suspend fun fetchRollerCoastersOrFail(
        rollerCoasterIds: List<Int>,
        pageSize: Int,
    ): Result<List<RollerCoaster>> {
        if (rollerCoasterIds.size != pageSize) return Err(NotFound)

        val rollerCoasters = withContext(Dispatchers.Default) {
            rollerCoasterIds.map { id -> getRollerCoaster(RollerCoasterId(id)) }
        }

        return when {
            rollerCoasters.any { result -> result.isErr } -> Err(NotFound)
            else -> Ok(rollerCoasters.map { result -> result.unwrap() })
        }
    }

    private suspend fun getRollerCoasterIds(
        pageNumber: PageNumber,
        pageSize: Int,
    ): Result<List<Int>> {
        val coasterIds = dao
            .getPaginatedRollerCoasters(pageNumber.value)
            ?.let(ListConverters::toIntList)
            ?: return Err(NotFound)

        return when (coasterIds.size) {
            pageSize -> Ok(coasterIds)
            else -> Err(NotFound)
        }
    }
}