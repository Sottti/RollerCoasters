package com.sottti.roller.coasters.data.roller.coasters.repository

import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import com.sottti.roller.coasters.domain.model.SortByFilter
import com.sottti.roller.coasters.domain.model.TypeFilter
import kotlinx.coroutines.flow.Flow

public interface RollerCoastersRepository {
    public fun getRollerCoasters(
        sortByFilter: SortByFilter? = null,
        typeFilter: TypeFilter? = null,
    ): Flow<PagingData<RollerCoaster>>

    public suspend fun getRollerCoaster(id: RollerCoasterId): Result<RollerCoaster>
    public suspend fun syncAllRollerCoasters(): Result<Unit>
}