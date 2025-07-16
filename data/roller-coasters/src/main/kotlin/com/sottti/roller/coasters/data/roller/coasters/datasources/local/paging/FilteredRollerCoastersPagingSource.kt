package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.createFilteredRollerCoastersQuery
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem

internal class FilteredRollerCoastersPagingSource(
    private val dao: RollerCoastersDao,
    private val measurementSystem: ResolvedMeasurementSystem,
    private val sortByFilter: SortByFilter,
    private val typeFilter: TypeFilter,
) : PagingSource<Int, RollerCoaster>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, RollerCoaster> =
        try {
            val page = params.key ?: 0
            val pageSize = params.loadSize

            val query = createFilteredRollerCoastersQuery(
                limit = pageSize,
                offset = page * pageSize,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )

            val rollerCoasters = dao
                .getPagedRollerCoasters(query = query)
                .map { rollerCoaster ->
                    rollerCoaster.toDomain(
                        measurementSystem = measurementSystem,
                        pictures = dao.getPictures(rollerCoaster.id),
                    )
                }

            LoadResult.Page(
                data = rollerCoasters,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (rollerCoasters.size < pageSize) null else page + 1,
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