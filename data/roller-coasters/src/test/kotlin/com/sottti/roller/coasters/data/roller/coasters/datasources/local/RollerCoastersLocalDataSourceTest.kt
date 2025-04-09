package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging.RollerCoastersPagingSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherNotMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.notMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoasterId
import com.sottti.roller.coasters.domain.fixtures.rollerCoasterWithoutOtherPictures
import com.sottti.roller.coasters.domain.model.NotFound
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.Alphabetical
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter.All
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.InternalSerializationApi
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class RollerCoastersLocalDataSourceTest {

    private lateinit var dao: RollerCoastersDao
    private lateinit var localDataSource: RollerCoastersLocalDataSource

    @Before
    fun setup() {
        dao = mockk()
        localDataSource = RollerCoastersLocalDataSource(dao)
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `store roller coaster with single item`() = runTest {
        coEvery {
            dao.insertRollerCoasters(
                pictures = listOf(notMainPictureRoomModel),
                rollerCoasters = listOf(rollerCoasterRoomModel),
            )
        } just runs

        localDataSource.storeRollerCoaster(rollerCoaster)

        coVerify(exactly = 1) {
            dao.insertRollerCoasters(
                pictures = listOf(notMainPictureRoomModel),
                rollerCoasters = listOf(rollerCoasterRoomModel),
            )
        }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `store roller coasters with multiple items`() = runTest {
        coEvery {
            dao.insertRollerCoasters(
                pictures = listOf(notMainPictureRoomModel, anotherNotMainPictureRoomModel),
                rollerCoasters = listOf(rollerCoasterRoomModel, anotherRollerCoasterRoomModel),
            )
        } just runs

        localDataSource.storeRollerCoasters(listOf(rollerCoaster, anotherRollerCoaster))

        coVerify(exactly = 1) {
            dao.insertRollerCoasters(
                pictures = listOf(notMainPictureRoomModel, anotherNotMainPictureRoomModel),
                rollerCoasters = listOf(rollerCoasterRoomModel, anotherRollerCoasterRoomModel),
            )
        }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `store roller coasters when list is empty`() = runTest {
        localDataSource.storeRollerCoasters(emptyList())
        coVerify(exactly = 0) { dao.insertRollerCoasters(any(), any()) }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `get roller coaster returns roller coaster when data exists`() = runTest {
        coEvery { dao.getRollerCoaster(rollerCoasterId.value) } returns rollerCoasterRoomModel
        coEvery { dao.getPictures(rollerCoasterId.value) } returns listOf(notMainPictureRoomModel)

        val result = localDataSource.getRollerCoaster(rollerCoasterId, Metric)

        assertThat(result).isEqualTo(Ok(rollerCoaster(Metric)))
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `get roller coaster when not found`() = runTest {
        coEvery { dao.getRollerCoaster(rollerCoasterId.value) } returns null

        val result = localDataSource.getRollerCoaster(rollerCoasterId, Metric)

        assertThat(result).isEqualTo(Err(NotFound))
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `get roller coaster when pictures are missing`() = runTest {
        coEvery { dao.getRollerCoaster(rollerCoasterId.value) } returns rollerCoasterRoomModel
        coEvery { dao.getPictures(rollerCoasterId.value) } returns emptyList()

        val result = localDataSource.getRollerCoaster(rollerCoasterId, Metric)

        assertThat(result).isEqualTo(Ok(rollerCoasterWithoutOtherPictures(Metric)))
    }

    @Test
    fun `get paged roller coasters returns a paging source`() {
        val result = localDataSource.observePagedRollerCoasters(
            measurementSystem = Metric,
            sortByFilter = Alphabetical,
            typeFilter = All,
        )
        assertThat(result).isInstanceOf(RollerCoastersPagingSource::class.java)
    }
}