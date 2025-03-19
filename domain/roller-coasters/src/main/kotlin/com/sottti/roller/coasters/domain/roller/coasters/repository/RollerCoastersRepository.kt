package com.sottti.roller.coasters.domain.roller.coasters.repository

import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.MeasurementSystem
import kotlinx.coroutines.flow.Flow

public interface RollerCoastersRepository {
    public fun observeRollerCoasters(
        sortByFilter: SortByFilter,
        typeFilter: TypeFilter,
    ): Flow<PagingData<RollerCoaster>>

    public suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): Result<RollerCoaster>

    public suspend fun syncAllRollerCoasters(): Result<Unit>
}