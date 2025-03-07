package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.SortByFilter
import com.sottti.roller.coasters.domain.model.TypeFilter
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal class RollerCoastersPagingSource(
    private val dao: RollerCoastersDao,
    private val sortByFilter: SortByFilter?,
    private val typeFilter: TypeFilter?
) : PagingSource<Int, RollerCoaster>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, RollerCoaster> =
        try {
            val page = params.key ?: 0
            val pageSize = params.loadSize

            val rollerCoastersDomain =
                dao
                    .getPagedRollerCoastersSortedByHeight(
                        limit = pageSize,
                        offset = page * pageSize,
                    )
                    .map { rollerCoaster ->
                        val pictures = dao.getPictures(rollerCoaster.id)
                        rollerCoaster.toDomain(pictures)
                    }

            LoadResult.Page(
                data = rollerCoastersDomain,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (rollerCoastersDomain.size < pageSize) null else page + 1,
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