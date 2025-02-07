package com.sottti.roller.coasters.data.roller.coasters.repository

import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster

public interface RollerCoastersRepository {
    public suspend fun syncAllRollerCoasters()
    public suspend fun getRandomRollerCoaster(): RollerCoaster
}