package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

internal class RollerCoastersPagingSource @Inject constructor(
    private val dao: RollerCoastersDao,
) : PagingSource<PageNumber, RollerCoaster>() {

    @OptIn(InternalSerializationApi::class)
    override suspend fun load(
        params: LoadParams<PageNumber>,
    ): LoadResult<PageNumber, RollerCoaster> = try {
        val currentPage = params.key ?: PageNumber.initial()

        val pagedRollerCoasters = dao
            .getPagedRollerCoasters(currentPage.value)
            ?: return emptyPage()

        val rollerCoasters = dao
            .getRollerCoasters(pagedRollerCoasters.rollerCoasterIds)
            .map { rollerCoasterRoom ->
                rollerCoasterRoom.toDomain(dao.getPictures(rollerCoasterRoom.id))
            }

        page(rollerCoasters = rollerCoasters, currentPage = currentPage)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(
        state: PagingState<PageNumber, RollerCoaster>,
    ): PageNumber? = state.anchorPosition?.let { anchor ->
        state.closestPageToPosition(anchor)?.prevKey?.let { PageNumber(it.value + 1) }
            ?: state.closestPageToPosition(anchor)?.nextKey?.let { PageNumber(it.value - 1) }
    }

    class Factory @Inject constructor(private val dao: RollerCoastersDao) {
        fun create(): RollerCoastersPagingSource = RollerCoastersPagingSource(dao)
    }
}

private fun page(
    rollerCoasters: List<RollerCoaster>,
    currentPage: PageNumber
): LoadResult.Page<PageNumber, RollerCoaster> =
    LoadResult.Page(
        data = rollerCoasters,
        prevKey = if (currentPage.value == 0) null else PageNumber(currentPage.value - 1),
        nextKey = if (rollerCoasters.isEmpty()) null else PageNumber(currentPage.value + 1),
    )

private fun emptyPage(): LoadResult.Page<PageNumber, RollerCoaster> =
    LoadResult.Page(data = emptyList(), prevKey = null, nextKey = null)