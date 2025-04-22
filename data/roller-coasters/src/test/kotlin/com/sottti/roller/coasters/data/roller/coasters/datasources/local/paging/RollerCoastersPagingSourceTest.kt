package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper.toDomain
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.mainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.InternalSerializationApi
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, InternalSerializationApi::class)
internal class RollerCoastersPagingSourceTest {

    private lateinit var dao: RollerCoastersDao
    private lateinit var pagingSource: RollerCoastersPagingSource
    private val sortByFilter = SortByFilter.Alphabetical
    private val typeFilter = TypeFilter.All

    @Before
    fun setup() {
        dao = mockk()
        pagingSource = RollerCoastersPagingSource(
            dao = dao,
            measurementSystem = Metric,
            sortByFilter = sortByFilter,
            typeFilter = typeFilter,
        )
    }

    @Test
    fun `load - returns correctly when successful`() = runBlocking {
        coEvery {
            dao.getPagedRollerCoasters(any())
        } returns listOf(rollerCoasterRoomModel, anotherRollerCoasterRoomModel)

        coEvery {
            dao.getPictures(rollerCoasterRoomModel.id)
        } returns listOf(mainPictureRoomModel)

        coEvery {
            dao.getPictures(anotherRollerCoasterRoomModel.id)
        } returns listOf(anotherMainPictureRoomModel)

        val params = PagingSource.LoadParams.Refresh(
            key = 0,
            loadSize = 2,
            placeholdersEnabled = false,
        )

        val result = pagingSource.load(params)

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Page::class.java)

        val page = result as PagingSource.LoadResult.Page

        assertThat(page.data)
            .containsExactly(
                rollerCoasterRoomModel.toDomain(Metric, listOf(mainPictureRoomModel)),
                anotherRollerCoasterRoomModel.toDomain(Metric, listOf(anotherMainPictureRoomModel)),
            )
        assertThat(page.prevKey).isNull()
        assertThat(page.nextKey).isEqualTo(1)
    }

    @Test
    fun `load - returns correctly when no data`() = runBlocking {
        coEvery { dao.getPagedRollerCoasters(any()) } returns emptyList()
        coEvery { dao.getPictures(any()) } returns emptyList()

        val params = PagingSource.LoadParams.Refresh(
            key = 0,
            loadSize = 2,
            placeholdersEnabled = false,
        )

        val result = pagingSource.load(params)

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Page::class.java)

        val page = result as PagingSource.LoadResult.Page
        assertThat(page.data).isEmpty()
        assertThat(page.prevKey).isNull()
        assertThat(page.nextKey).isNull()
    }

    @Test
    fun `load - returns correctly when exception occurs`() = runBlocking {
        coEvery { dao.getPagedRollerCoasters(any()) } throws RuntimeException("DB Error")

        val params = PagingSource.LoadParams.Refresh(
            key = 0,
            loadSize = 2,
            placeholdersEnabled = false,
        )

        val result = pagingSource.load(params)

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)

        val error = result as PagingSource.LoadResult.Error
        assertThat(error.throwable).isInstanceOf(RuntimeException::class.java)
        assertThat(error.throwable.message).isEqualTo("DB Error")
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
                PagingSource.LoadResult.Page(
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