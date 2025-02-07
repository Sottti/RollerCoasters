package com.sottti.roller.coasters.data.roller.coasters.repository

import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.model.RollerCoaster
import javax.inject.Inject

internal class RollerCoastersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RollerCoastersRemoteDataSource,
    private val localDataSource: RollerCoastersLocalDataSource,
) : RollerCoastersRepository {

    override suspend fun syncAllRollerCoasters() {
        remoteDataSource.syncAllRollerCoasters { rollerCoasters ->
            localDataSource.storeRollerCoasters(rollerCoasters)
        }
    }

    override suspend fun getRandomRollerCoaster(): RollerCoaster =
        remoteDataSource.getRandomRollerCoaster()
}