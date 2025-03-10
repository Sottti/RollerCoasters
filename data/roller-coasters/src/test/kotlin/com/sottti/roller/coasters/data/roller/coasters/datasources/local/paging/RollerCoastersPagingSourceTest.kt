package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.stubs.anotherRollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoaster
import com.sottti.roller.coasters.domain.model.SortByFilter
import com.sottti.roller.coasters.domain.model.TypeFilter
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.InternalSerializationApi
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, InternalSerializationApi::class)
internal class RollerCoastersPagingSourceTest {

    private lateinit var dao: RollerCoastersDao
    private lateinit var pagingSource: RollerCoastersPagingSource
    private val sortByFilter = SortByFilter.ALPHABETICAL
    private val typeFilter = TypeFilter.ALL

    @Before
    fun setup() {
        dao = mockk()
        pagingSource = RollerCoastersPagingSource(
            dao = dao,
            sortByFilter = sortByFilter,
            typeFilter = typeFilter,
        )
    }

    @Test
    fun `getRefreshKey - should return null when anchorPosition is null`() {
        val state = PagingState<Int, RollerCoaster>(
            pages = emptyList(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 2),
            leadingPlaceholderCount = 0,
        )

        val refreshKey = pagingSource.getRefreshKey(state)

        assertThat(refreshKey).isNull()
    }

    @Test
    fun `getRefreshKey - should return previous key plus one when available`() {
        val state = PagingState(
            pages = listOf(
                PagingSource.LoadResult.Page(
                    data = listOf(rollerCoaster, anotherRollerCoaster),
                    prevKey = 2,
                    nextKey = null,
                )
            ),
            anchorPosition = 1,
            config = PagingConfig(pageSize = 2),
            leadingPlaceholderCount = 0,
        )

        val refreshKey = pagingSource.getRefreshKey(state)

        assertThat(refreshKey).isEqualTo(3)
    }

    @Test
    fun `getRefreshKey - should return next key minus one when available`() {
        val state = PagingState(
            pages = listOf(
                PagingSource.LoadResult.Page<Int, RollerCoaster>(
                    data = listOf(rollerCoaster),
                    prevKey = null,
                    nextKey = 2,
                )
            ),
            anchorPosition = 1,
            config = PagingConfig(pageSize = 2),
            leadingPlaceholderCount = 0,
        )

        val refreshKey = pagingSource.getRefreshKey(state)

        assertThat(refreshKey).isEqualTo(1)
    }

    @Test
    fun `getRefreshKey - should return null when prevKey and nextKey are null`() {
        val state = PagingState(
            pages = listOf(
                PagingSource.LoadResult.Page<Int, RollerCoaster>(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null,
                )
            ),
            anchorPosition = 0,
            config = PagingConfig(pageSize = 2),
            leadingPlaceholderCount = 0,
        )

        val refreshKey = pagingSource.getRefreshKey(state)

        assertThat(refreshKey).isNull()
    }
}