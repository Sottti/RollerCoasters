package com.sottti.roller.coasters.domain.roller.coasters.repository

import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
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
}