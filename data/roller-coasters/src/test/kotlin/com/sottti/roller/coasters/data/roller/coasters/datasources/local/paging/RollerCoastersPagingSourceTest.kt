package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.PAGE_NUMBER_INITIAL
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.PAGE_NUMBER_SECOND
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.PAGE_NUMBER_THIRD
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.notMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.pagedRollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl.Companion.PAGE_SIZE
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterId
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.InternalSerializationApi
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class RollerCoastersPagingSourceTest {

    private lateinit var dao: RollerCoastersDao
    private lateinit var pagingSource: RollerCoastersPagingSource

    @Before
    fun setup() {
        dao = mockk()
        pagingSource = RollerCoastersPagingSource(dao)
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `load returns page when data is available`() = runTest {
        val pageNumber = PageNumber(PAGE_NUMBER_SECOND)

        coEvery {
            dao.getPagedRollerCoasters(pageNumber.value)
        } returns pagedRollerCoastersRoom

        coEvery {
            dao.getRollerCoasters(pagedRollerCoastersRoom.rollerCoasterIds)
        } returns listOf(rollerCoasterRoomModel)

        coEvery {
            dao.getPictures(rollerCoasterRoomModel.id)
        } returns listOf(notMainPictureRoomModel)

        val result = pagingSource.load(something(pageNumber))

        val page = result as PagingSource.LoadResult.Page
        assertThat(page.data).containsExactly(rollerCoaster)
        assertThat(page.prevKey?.value).isEqualTo(0)
        assertThat(page.nextKey?.value).isEqualTo(2)

        coVerify { dao.getPagedRollerCoasters(pageNumber.value) }
        coVerify { dao.getRollerCoasters(listOf(rollerCoasterId.value)) }
        coVerify { dao.getPictures(rollerCoasterId.value) }
    }

    @Test
    fun `load returns empty page when no data is found`() = runTest {
        val pageNumber = PageNumber(PAGE_NUMBER_INITIAL)
        coEvery { dao.getPagedRollerCoasters(pageNumber.value) } returns null

        val result = pagingSource.load(something(pageNumber))

        val page = result as PagingSource.LoadResult.Page
        assertThat(page.data).isEmpty()
        assertThat(page.prevKey).isNull()
        assertThat(page.nextKey).isNull()
    }

    @Test
    fun `load returns error when exception is thrown`() = runTest {
        val pageNumber = PageNumber(PAGE_NUMBER_INITIAL)
        val exception = RuntimeException("Database error")
        coEvery {
            dao.getPagedRollerCoasters(pageNumber.value)
        } throws exception

        val result = pagingSource.load(something(pageNumber))

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
        val error = result as PagingSource.LoadResult.Error
        assertThat(error.throwable).hasMessageThat().contains(exception.message)
    }

    @Test
    fun `getRefreshKey returns correct page`() {
        val state = PagingState<PageNumber, RollerCoaster>(
            pages = listOf(
                PagingSource.LoadResult.Page(
                    data = listOf(mockk(), mockk()),
                    prevKey = PageNumber(PAGE_NUMBER_INITIAL),
                    nextKey = PageNumber(PAGE_NUMBER_THIRD)
                ),
            ),
            anchorPosition = 1,
            config = mockk(),
            leadingPlaceholderCount = 0,
        )

        val refreshKey = pagingSource.getRefreshKey(state)

        assertThat(refreshKey).isEqualTo(PageNumber(PAGE_NUMBER_SECOND))
    }
}

private fun something(pageNumber: PageNumber): PagingSource.LoadParams.Refresh<PageNumber> =
    PagingSource.LoadParams.Refresh(
        key = pageNumber,
        loadSize = PAGE_SIZE,
        placeholdersEnabled = false,
    )