package com.sottti.roller.coasters.data.roller.coasters.repository

import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId

public interface RollerCoastersRepository {
    public suspend fun getRollerCoaster(id: RollerCoasterId): Result<RollerCoaster>
    public suspend fun getRollerCoasters(pageNumber: PageNumber): Result<List<RollerCoaster>>
    public suspend fun syncAllRollerCoasters(): Result<Unit>
}