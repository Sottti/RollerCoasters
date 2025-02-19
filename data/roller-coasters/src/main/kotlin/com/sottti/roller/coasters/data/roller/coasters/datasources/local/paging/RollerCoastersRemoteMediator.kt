package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.michaelbull.result.mapBoth
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class RollerCoastersRemoteMediator @Inject constructor(
    private val localDataSource: RollerCoastersLocalDataSource,
    private val remoteDataSource: RollerCoastersRemoteDataSource,
) : RemoteMediator<PageNumber, RollerCoaster>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<PageNumber, RollerCoaster>,
    ): MediatorResult = try {
        val page = getPageToLoad(loadType, state) ?: return MediatorResult.Success(true)
        fetchAndStoreRollerCoasters(pageNumber = page, pageSize = state.config.pageSize)
    } catch (e: Exception) {
        MediatorResult.Error(e)
    }

    private fun getPageToLoad(
        loadType: LoadType,
        state: PagingState<PageNumber, RollerCoaster>,
    ): PageNumber? = when (loadType) {
        LoadType.REFRESH -> PageNumber(0)
        LoadType.PREPEND -> null
        LoadType.APPEND -> getNextPage(state)
    }

    private fun getNextPage(
        state: PagingState<PageNumber, RollerCoaster>,
    ): PageNumber? = state.pages.lastOrNull()?.nextKey

    private suspend fun fetchAndStoreRollerCoasters(
        pageNumber: PageNumber,
        pageSize: Int,
    ): MediatorResult =
        remoteDataSource
            .getRollerCoastersPage(pageNumber)
            .mapBoth(
                success = { rollerCoasters ->
                    localDataSource.storeRollerCoasters(rollerCoasters)
                    MediatorResult.Success(endOfPaginationReached = rollerCoasters.size < pageSize)
                },
                failure = { exception -> MediatorResult.Error(exception) }
            )
}