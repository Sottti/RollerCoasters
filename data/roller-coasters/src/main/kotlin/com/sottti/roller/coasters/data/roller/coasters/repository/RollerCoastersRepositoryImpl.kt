package com.sottti.roller.coasters.data.roller.coasters.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.michaelbull.result.onSuccess
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.sync.RollerCoasterSyncScheduler
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

internal class RollerCoastersRepositoryImpl @Inject constructor(
    private val localDataSource: RollerCoastersLocalDataSource,
    private val remoteDataSource: RollerCoastersRemoteDataSource,
    private val rollerCoasterSyncScheduler: RollerCoasterSyncScheduler,
) : RollerCoastersRepository {

    companion object {
        const val PREFETCH_DISTANCE = 25
        const val PAGE_SIZE = 25
        val pagerConfig = PagingConfig(
            enablePlaceholders = true,
            initialLoadSize = PAGE_SIZE,
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
        )
    }

    override fun observeRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
        sortByFilter: SortByFilter,
        typeFilter: TypeFilter,
    ): Flow<PagingData<RollerCoaster>> =
        Pager(
            config = pagerConfig,
            pagingSourceFactory = {
                localDataSource.observeRollerCoasters(
                    measurementSystem = measurementSystem,
                    sortByFilter = sortByFilter,
                    typeFilter = typeFilter,
                )
            }
        ).flow


    override fun observeRollerCoaster(
        id: RollerCoasterId,
        measurementSystem: ResolvedMeasurementSystem,
    ): Flow<RollerCoaster> =
        localDataSource
            .observeRollerCoaster(id, measurementSystem)
            .onEach { rollerCoaster ->
                if (rollerCoaster == null) {
                    remoteDataSource
                        .getRollerCoaster(id, measurementSystem)
                        .onSuccess { localDataSource.storeRollerCoaster(it) }
                }
            }.filterNotNull()

    override fun scheduleRollerCoastersSync() {
        rollerCoasterSyncScheduler.schedule()
    }

    override suspend fun syncAllRollerCoasters(): Result<Unit> =
        remoteDataSource.syncRollerCoasters { rollerCoasters ->
            localDataSource.storeRollerCoasters(rollerCoasters)
        }

    override suspend fun addFavouriteRollerCoaster(id: RollerCoasterId) {
        localDataSource.addFavouriteRollerCoaster(id)
    }

    override suspend fun removeFavouriteRollerCoaster(id: RollerCoasterId) {
        localDataSource.removeFavouriteRollerCoaster(id)
    }

    override fun observeIsFavouriteRollerCoaster(id: RollerCoasterId): Flow<Boolean> =
        localDataSource.observeIsFavouriteRollerCoaster(id)

    override suspend fun isFavouriteRollerCoaster(id: RollerCoasterId): Boolean =
        localDataSource.isFavouriteRollerCoaster(id)

    override fun observeFavouriteRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
    ): Flow<PagingData<RollerCoaster>> =
        localDataSource.observeFavouriteRollerCoasters(
            measurementSystem = measurementSystem,
            pagerConfig = pagerConfig,
        )

    override suspend fun searchRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
        query: SearchQuery,
    ): Result<List<RollerCoaster>> =
        remoteDataSource
            .searchRollerCoasters(query = query, measurementSystem = measurementSystem)
            .onSuccess { rollerCoasters -> localDataSource.storeRollerCoasters(rollerCoasters) }
}