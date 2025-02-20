package com.sottti.roller.coasters.data.roller.coasters.datasources.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.michaelbull.result.mapBoth
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.PAGE_NUMBER_INITIAL
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class RollerCoastersRemoteMediator @Inject constructor(
    private val localDataSource: RollerCoastersLocalDataSource,
    private val remoteDataSource: RollerCoastersRemoteDataSource,
) : RemoteMediator<PageNumber, RollerCoaster>() {

    override suspend fun initialize(): InitializeAction =
        InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<PageNumber, RollerCoaster>
    ): MediatorResult = try {
        val page = getPageToLoad(loadType, state)
            ?: return MediatorResult.Success(endOfPaginationReached = true)
        fetchAndStoreRollerCoasters(page, state.config.pageSize)

    } catch (e: Exception) {
        MediatorResult.Error(e)
    }

    private fun getPageToLoad(
        loadType: LoadType,
        state: PagingState<PageNumber, RollerCoaster>
    ): PageNumber? = when (loadType) {
        LoadType.REFRESH -> PageNumber(PAGE_NUMBER_INITIAL)
        LoadType.PREPEND -> null
        LoadType.APPEND -> getNextPage(state)
    }

    private fun getNextPage(
        state: PagingState<PageNumber, RollerCoaster>
    ): PageNumber? {
        val lastKey = state.pages.lastOrNull()?.nextKey
        return when {
            lastKey != null -> PageNumber(lastKey.value + 1)
            else -> null
        }
    }

    private suspend fun fetchAndStoreRollerCoasters(
        pageNumber: PageNumber,
        pageSize: Int,
    ): MediatorResult =
        remoteDataSource
            .getRollerCoastersPage(pageNumber)
            .mapBoth(
                success = { rollerCoasters ->
                    localDataSource.storeRollerCoasters(pageNumber, rollerCoasters)
                    MediatorResult.Success(endOfPaginationReached = rollerCoasters.size < pageSize)
                },
                failure = { exception -> MediatorResult.Error(exception) }
            )
}