package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.withId
import com.sottti.roller.coasters.data.roller.coasters.stubs.anotherRollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.domain.model.RollerCoaster
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.InternalSerializationApi
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, InternalSerializationApi::class)
internal class RollerCoastersPagingSourceTest {

    private lateinit var dao: RollerCoastersDao
    private lateinit var pagingSource: RollerCoastersPagingSource
    private val sortByFilter = null
    private val typeFilter = null

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
    fun `load - first page loads correctly`() = runTest {
        val pageSize = 2
        val page = 0
        val rollerCoasters = listOf(
            rollerCoasterRoomModel.withId(id = 1),
            rollerCoasterRoomModel.withId(id = 2),
        )

        coEvery {
            dao.getPagedRollerCoastersSortedByHeight(limit = pageSize, offset = 0)
        } returns rollerCoasters

        coEvery { dao.getPictures(any()) } returns emptyList()

        val expectedResult = PagingSource.LoadResult.Page(
            data = rollerCoasters.map { rollerCoaster -> rollerCoaster.toDomain(emptyList()) },
            prevKey = null,
            nextKey = page + 1,
        )

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = page,
                loadSize = pageSize,
                placeholdersEnabled = false,
            )
        )

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `load - next page loads correctly`() = runTest {
        val page = 1
        val pageSize = 2
        val rollerCoasters = listOf(
            rollerCoasterRoomModel.withId(id = 3),
            rollerCoasterRoomModel.withId(id = 4)
        )

        coEvery {
            dao.getPagedRollerCoastersSortedByHeight(limit = pageSize, offset = page * pageSize)
        } returns rollerCoasters

        coEvery { dao.getPictures(any()) } returns emptyList()

        val expectedResult = PagingSource.LoadResult.Page(
            data = rollerCoasters.map { rollerCoaster -> rollerCoaster.toDomain(emptyList()) },
            prevKey = page - 1,
            nextKey = page + 1
        )

        val result = pagingSource.load(
            PagingSource.LoadParams.Append(
                key = page,
                loadSize = pageSize,
                placeholdersEnabled = false
            )
        )

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `load - returns end of pagination when last page reached`() = runTest {
        val page = 2
        val pageSize = 2
        val rollerCoasters = listOf(rollerCoasterRoomModel.withId(id = 5))

        coEvery {
            dao.getPagedRollerCoastersSortedByHeight(limit = pageSize, offset = page * pageSize)
        } returns rollerCoasters

        coEvery { dao.getPictures(any()) } returns emptyList()

        val expectedResult = PagingSource.LoadResult.Page(
            data = rollerCoasters.map { it.toDomain(emptyList()) },
            prevKey = page - 1,
            nextKey = null,
        )

        val result = pagingSource.load(
            PagingSource.LoadParams.Append(
                key = page,
                loadSize = pageSize,
                placeholdersEnabled = false,
            )
        )

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `load - returns empty list when database has no items`() = runTest {
        coEvery { dao.getPagedRollerCoastersSortedByHeight(any(), any()) } returns emptyList()
        coEvery { dao.getPictures(any()) } returns emptyList()

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 2,
                placeholdersEnabled = false,
            )
        )

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Page::class.java)
        val pageResult = result as PagingSource.LoadResult.Page
        assertThat(pageResult.data).isEmpty()
        assertThat(pageResult.prevKey).isNull()
        assertThat(pageResult.nextKey).isNull()
    }

    @Test
    fun `load - returns error when dao throws exception`() = runTest {
        val exception = RuntimeException("Database error")

        coEvery { dao.getPagedRollerCoastersSortedByHeight(any(), any()) } throws exception

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 2,
                placeholdersEnabled = false,
            )
        )

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
        val errorResult = result as PagingSource.LoadResult.Error
        assertThat(errorResult.throwable).isEqualTo(exception)
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