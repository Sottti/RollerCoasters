package com.sottti.roller.coasters.data.roller.coasters.datasources

import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.toDomainModel
import javax.inject.Inject

internal class RollerCoastersRemoteDataSource @Inject constructor(
    private val endpoints: RollerCoastersEndpoints,
) {
    suspend fun getRandomRollerCoaster(): RollerCoaster =
        endpoints
            .getRandomCoaster()
            .toDomainModel()
}