package com.sottti.roller.coasters.data.roller.coasters.repository

import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import javax.inject.Inject

internal class RollerCoastersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RollerCoastersRemoteDataSource,
) : RollerCoastersRepository {

    override suspend fun getRandomRollerCoaster(): RollerCoaster =
        remoteDataSource.getRandomRollerCoaster()
}