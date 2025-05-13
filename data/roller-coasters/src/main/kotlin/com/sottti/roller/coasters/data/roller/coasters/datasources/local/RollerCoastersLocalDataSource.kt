package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import androidx.paging.PagingSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toFavouriteRollercoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toPicturesRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging.FavouriteRollerCoastersPagingSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging.FilteredRollerCoastersPagingSource
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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
        if (rollerCoasters.isNotEmpty()) {
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
    }

    @OptIn(InternalSerializationApi::class)
    fun observeRollerCoaster(
        id: RollerCoasterId,
        measurementSystem: ResolvedMeasurementSystem,
    ): Flow<RollerCoaster?> =
        dao
            .observeRollerCoaster(id.value)
            .combine(dao.observePictures(rollerCoasterId = id.value)) { rollerCoaster, pictures ->
                rollerCoaster?.toDomain(
                    measurementSystem = measurementSystem,
                    pictures = pictures,
                )
            }

    fun observeFilteredRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
        sortByFilter: SortByFilter,
        typeFilter: TypeFilter,
    ): PagingSource<Int, RollerCoaster> =
        FilteredRollerCoastersPagingSource(
            dao = dao,
            measurementSystem,
            sortByFilter = sortByFilter,
            typeFilter = typeFilter,
        )

    suspend fun addFavouriteRollerCoaster(rollerCoasterId: RollerCoasterId) {
        dao.addFavouriteRollerCoaster(
            favouriteRollerCoaster = rollerCoasterId.toFavouriteRollercoasterRoomModel()
        )
    }

    suspend fun removeFavouriteRollerCoaster(rollerCoasterId: RollerCoasterId) {
        dao.removeFavouriteRollerCoaster(rollerCoasterId.value)
    }

    fun observeIsFavouriteRollerCoaster(rollerCoasterId: RollerCoasterId): Flow<Boolean> =
        dao.observeIsFavouriteRollerCoasterFlow(rollerCoasterId.value)

    suspend fun isFavouriteRollerCoaster(rollerCoasterId: RollerCoasterId): Boolean =
        dao.isFavouriteRollerCoasterFlow(rollerCoasterId.value)

    fun observeFavouriteRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
    ): PagingSource<Int, RollerCoaster> =
        FavouriteRollerCoastersPagingSource(
            dao = dao,
            measurementSystem = measurementSystem,
        )
}