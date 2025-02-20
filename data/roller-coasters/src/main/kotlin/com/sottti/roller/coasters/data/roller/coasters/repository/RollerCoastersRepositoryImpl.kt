package com.sottti.roller.coasters.data.roller.coasters.repository

import androidx.paging.PagingConfig
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.onSuccess
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import javax.inject.Inject

internal class RollerCoastersRepositoryImpl @Inject constructor(
    private val localDataSource: RollerCoastersLocalDataSource,
    private val remoteDataSource: RollerCoastersRemoteDataSource,
) : RollerCoastersRepository {

    companion object {
        const val PREFETCH_DISTANCE = 5
        const val PAGE_SIZE = 25
        val pagerConfig = PagingConfig(
            enablePlaceholders = true,
            initialLoadSize = PAGE_SIZE,
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
        )
    }

    override suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): Result<RollerCoaster> =
        localDataSource.getRollerCoaster(id).mapBoth(
            success = { rollerCoaster -> Ok(rollerCoaster) },
            failure = {
                remoteDataSource
                    .getRollerCoaster(id)
                    .onSuccess { localDataSource.storeRollerCoaster(it) }
            }
        )

    override suspend fun syncAllRollerCoasters(): Result<Unit> =
        remoteDataSource.syncRollerCoasters { rollerCoasters ->
            localDataSource.storeRollerCoasters(rollerCoasters)
        }
}