package com.sottti.roller.coasters.data.roller.coasters.repository

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.onSuccess
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import javax.inject.Inject

internal class RollerCoastersRepositoryImpl @Inject constructor(
    private val localDataSource: RollerCoastersLocalDataSource,
    private val remoteDataSource: RollerCoastersRemoteDataSource,
) : RollerCoastersRepository {

    companion object {
        internal const val PAGE_SIZE = 200
    }

    override suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): Result<RollerCoaster> =
        localDataSource.getRollerCoaster(id).mapBoth(
            success = { rollerCoaster -> Ok(rollerCoaster) },
            failure = {
                remoteDataSource.getRollerCoaster(id)
                    .onSuccess { localDataSource.storeRollerCoaster(it) }
            }
        )

    override suspend fun getRollerCoasters(
        pageNumber: PageNumber
    ): Result<List<RollerCoaster>> =
        localDataSource.getRollerCoasters(pageNumber).mapBoth(
            success = { rollerCoasters -> Ok(rollerCoasters) },
            failure = {
                remoteDataSource
                    .getRollerCoastersPage(pageNumber)
                    .onSuccess { rollerCoasters ->
                        localDataSource.storeRollerCoasters(rollerCoasters, pageNumber)
                    }
            }
        )

    override suspend fun syncAllRollerCoasters(): Result<Unit> =
        remoteDataSource.syncRollerCoasters { rollerCoasters ->
            localDataSource.storeRollerCoasters(rollerCoasters)
        }
}