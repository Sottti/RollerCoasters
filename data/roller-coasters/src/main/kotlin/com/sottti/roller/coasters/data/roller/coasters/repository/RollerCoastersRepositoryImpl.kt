package com.sottti.roller.coasters.data.roller.coasters.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.onSuccess
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging.RollerCoastersRemoteMediator
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RollerCoastersRepositoryImpl @Inject constructor(
    private val localDataSource: RollerCoastersLocalDataSource,
    private val remoteDataSource: RollerCoastersRemoteDataSource,
    private val remoteMediator: RollerCoastersRemoteMediator,
) : RollerCoastersRepository {

    companion object {
        const val PREFETCH_DISTANCE = 10
        const val PAGE_SIZE = 200
        val pagerConfig = PagingConfig(
            enablePlaceholders = true,
            initialLoadSize = PAGE_SIZE,
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getRollerCoastersPaged(
        page: PageNumber,
    ): Flow<PagingData<RollerCoaster>> =
        Pager(
            config = pagerConfig,
            pagingSourceFactory = { localDataSource.getPagedRollerCoasters() },
            remoteMediator = remoteMediator,
        ).flow

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