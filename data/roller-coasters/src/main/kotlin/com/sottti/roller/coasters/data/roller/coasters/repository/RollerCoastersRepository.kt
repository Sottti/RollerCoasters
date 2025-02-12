package com.sottti.roller.coasters.data.roller.coasters.repository

import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId

public interface RollerCoastersRepository {
    public suspend fun getRollerCoaster(id: RollerCoasterId): RollerCoaster
    public suspend fun syncAllRollerCoasters()
}