package com.sotti.roller.coasters.domain.roller.coasters.repository

import androidx.paging.PagingData
import com.sotti.roller.coasters.domain.model.Result
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sotti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sotti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sotti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sotti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import kotlinx.coroutines.flow.Flow

public interface RollerCoastersRepository {
    public fun observeRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
        sortByFilter: SortByFilter,
        typeFilter: TypeFilter,
    ): Flow<PagingData<RollerCoaster>>

    public fun observeRollerCoaster(
        id: RollerCoasterId,
        measurementSystem: ResolvedMeasurementSystem,
    ): Flow<RollerCoaster>

    public fun scheduleRollerCoastersSync()

    public suspend fun syncAllRollerCoasters(): Result<Unit>

    public suspend fun addFavouriteRollerCoaster(id: RollerCoasterId)

    public suspend fun removeFavouriteRollerCoaster(id: RollerCoasterId)

    public suspend fun isFavouriteRollerCoaster(id: RollerCoasterId): Boolean

    public fun observeIsFavouriteRollerCoaster(id: RollerCoasterId): Flow<Boolean>

    public fun observeFavouriteRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
    ): Flow<PagingData<RollerCoaster>>

    public suspend fun searchRollerCoasters(
        measurementSystem: ResolvedMeasurementSystem,
        query: SearchQuery,
    ): Result<List<RollerCoaster>>
}
