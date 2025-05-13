package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.createFavouriteRollerCoastersQuery
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal class FavouriteRollerCoastersPagingSource(
    private val dao: RollerCoastersDao,
    private val measurementSystem: ResolvedMeasurementSystem,
) : PagingSource<Int, RollerCoaster>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RollerCoaster> =
        try {
            val page = params.key ?: 0
            val pageSize = params.loadSize
            val offset = page * pageSize

            val query = createFavouriteRollerCoastersQuery(limit = pageSize, offset = offset)

            val result = dao
                .getPagedFavouriteRollerCoasters(query = query)
                .map { coaster ->
                    coaster.toDomain(
                        measurementSystem = measurementSystem,
                        pictures = dao.getPictures(coaster.id),
                    )
                }

            LoadResult.Page(
                data = result,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (result.size < pageSize) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, RollerCoaster>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val closestPage = state.closestPageToPosition(anchorPosition)
            when {
                closestPage?.prevKey != null -> closestPage.prevKey?.plus(1)
                closestPage?.nextKey != null -> closestPage.nextKey?.minus(1)
                else -> null
            }
        }
}