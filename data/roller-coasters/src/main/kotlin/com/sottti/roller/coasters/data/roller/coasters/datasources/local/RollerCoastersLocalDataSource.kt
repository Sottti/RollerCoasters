package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toFavouriteRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toPicturesRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging.FilteredRollerCoastersPagingSource
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RollerCoastersLocalDataSource @Inject constructor(
    private val dao: RollerCoastersDao,
) {

    suspend fun storeRollerCoaster(
        rollerCoaster: RollerCoaster,
    ) {
        storeRollerCoasters(listOf(rollerCoaster))
    }

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

    fun observeRollerCoasters(
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
            favouriteRollerCoaster = rollerCoasterId.toFavouriteRollerCoasterRoomModel(),
        )
    }

    suspend fun removeFavouriteRollerCoaster(rollerCoasterId: RollerCoasterId) {
        dao.removeFavouriteRollerCoaster(rollerCoasterId.value)
    }

    fun observeIsFavouriteRollerCoaster(rollerCoasterId: RollerCoasterId): Flow<Boolean> =
        dao.observeIsFavouriteRollerCoasterFlow(rollerCoasterId.value)

    suspend fun isFavouriteRollerCoaster(rollerCoasterId: RollerCoasterId): Boolean =
        dao.isFavouriteRollerCoaster(rollerCoasterId.value)

    fun observeFavouriteRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
        pagerConfig: PagingConfig,
    ): Flow<PagingData<RollerCoaster>> =
        Pager(
            config = pagerConfig,
            pagingSourceFactory = { dao.observePagedFavouriteRollerCoasters() }
        ).flow.map { pagingData ->
            pagingData.map { roomModel ->
                roomModel.toDomain(
                    measurementSystem = measurementSystem,
                    pictures = dao.getPictures(roomModel.id),
                )
            }
        }
}
