package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging.RollerCoastersPagingSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherNotMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.notMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.domain.fixtures.anotherNotMainPicture
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.mainPicture
import com.sottti.roller.coasters.domain.fixtures.notMainPicture
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.Pictures
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter.Alphabetical
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter.All
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
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
    fun `get paged roller coasters returns a paging source`() {
        val result = localDataSource.observePagedRollerCoasters(
            measurementSystem = Metric,
            sortByFilter = Alphabetical,
            typeFilter = All,
        )
        assertThat(result).isInstanceOf(RollerCoastersPagingSource::class.java)
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `observing roller coaster with valid id and metric system emits coaster`() = runTest {
        val rollerCoasterEntity = rollerCoasterRoomModel
        val pictures = listOf(notMainPictureRoomModel)

        coEvery {
            dao.observeRollerCoaster(rollerCoasterId.value)
        } returns flowOf(rollerCoasterEntity)
        coEvery { dao.observePictures(rollerCoasterId.value) } returns flowOf(pictures)

        val emissions = localDataSource.observeRollerCoaster(rollerCoasterId, Metric).toList()

        assertThat(emissions).hasSize(1)
        assertThat(emissions.first()).isEqualTo(rollerCoaster(Metric))
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `observing roller coaster with valid id and imperial us system emits coaster`() = runTest {
        val rollerCoasterEntity = rollerCoasterRoomModel
        val pictures = listOf(notMainPictureRoomModel)

        coEvery {
            dao.observeRollerCoaster(rollerCoasterId.value)
        } returns flowOf(rollerCoasterEntity)
        coEvery { dao.observePictures(rollerCoasterId.value) } returns flowOf(pictures)

        val emissions = localDataSource.observeRollerCoaster(rollerCoasterId, ImperialUs).toList()

        assertThat(emissions).hasSize(1)
        assertThat(emissions.first()).isEqualTo(rollerCoaster(ImperialUs))
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `observing roller coaster with valid id and imperial uk system emits coaster`() = runTest {
        val rollerCoasterEntity = rollerCoasterRoomModel
        val pictures = listOf(notMainPictureRoomModel)

        coEvery { dao.observeRollerCoaster(rollerCoasterId.value) } returns flowOf(
            rollerCoasterEntity
        )
        coEvery { dao.observePictures(rollerCoasterId.value) } returns flowOf(pictures)

        val emissions = localDataSource.observeRollerCoaster(rollerCoasterId, ImperialUk).toList()

        assertThat(emissions).hasSize(1)
        assertThat(emissions.first()).isEqualTo(rollerCoaster(ImperialUk))
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `observing roller coaster with invalid id emits null`() = runTest {
        coEvery { dao.observeRollerCoaster(rollerCoasterId.value) } returns flowOf(null)
        coEvery { dao.observePictures(rollerCoasterId.value) } returns flowOf(emptyList())

        val emissions = localDataSource.observeRollerCoaster(rollerCoasterId, Metric).toList()

        assertThat(emissions).hasSize(1)
        assertThat(emissions.first()).isNull()
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `observing coaster with no pictures emits roller coaster with empty pictures`() = runTest {
        coEvery {
            dao.observeRollerCoaster(rollerCoasterId.value)
        } returns flowOf(rollerCoasterRoomModel)
        coEvery { dao.observePictures(rollerCoasterId.value) } returns flowOf(emptyList())

        val emissions = localDataSource.observeRollerCoaster(rollerCoasterId, Metric).toList()

        assertThat(emissions).hasSize(1)
        assertThat(emissions.first()).isEqualTo(
            rollerCoaster.copy(
                pictures = Pictures(
                    main = mainPicture,
                    other = emptyList()
                )
            )
        )
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `observing roller coaster reacts to updated roller coaster data`() = runTest {
        val initialRollerCoasterEntity = rollerCoasterRoomModel
        val updatedRollerCoasterEntity = anotherRollerCoasterRoomModel

        coEvery { dao.observeRollerCoaster(rollerCoasterId.value) } returns flow {
            emit(initialRollerCoasterEntity)
            emit(updatedRollerCoasterEntity)
        }
        coEvery {
            dao.observePictures(rollerCoasterId.value)
        } returns flowOf(emptyList())

        val emissions =
            localDataSource
                .observeRollerCoaster(rollerCoasterId, Metric).take(2)
                .toList()

        assertThat(emissions).hasSize(2)
        assertThat(emissions[0]?.id?.value).isEqualTo(rollerCoasterRoomModel.id)
        assertThat(emissions[1]?.id?.value).isEqualTo(anotherRollerCoasterRoomModel.id)
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `observing roller coaster reacts to updated pictures`() = runTest {
        val initialPictures = listOf(notMainPictureRoomModel)
        val updatedPictures = listOf(notMainPictureRoomModel, anotherNotMainPictureRoomModel)

        coEvery {
            dao.observeRollerCoaster(rollerCoasterId.value)
        } returns flowOf(rollerCoasterRoomModel)
        coEvery { dao.observePictures(rollerCoasterId.value) } returns flow {
            emit(initialPictures)
            emit(updatedPictures)
        }

        val emissions =
            localDataSource
                .observeRollerCoaster(rollerCoasterId, Metric).take(2)
                .toList()

        assertThat(emissions).hasSize(2)
        assertThat(emissions[0]).isEqualTo(rollerCoaster)
        assertThat(emissions[1]).isEqualTo(
            rollerCoaster.copy(
                pictures = rollerCoaster.pictures.copy(
                    other = listOf(notMainPicture, anotherNotMainPicture)
                ),
            )
        )
    }
}