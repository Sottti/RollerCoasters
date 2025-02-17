package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.github.michaelbull.result.Err
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters.ListConverters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.pageNumber
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.pageSize
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.picturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl.Companion.PAGE_SIZE
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterId
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasters
import com.sottti.roller.coasters.domain.model.NotFound
import com.sottti.roller.coasters.domain.model.PageNumber
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

@ExperimentalCoroutinesApi
internal class RollerCoastersLocalDataSourceTest {

    private lateinit var dataSource: RollerCoastersLocalDataSource
    private val dao: RollerCoastersDao = mockk()

    @Before
    fun setup() {
        dataSource = RollerCoastersLocalDataSource(dao)
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Get roller coaster - Exists in DB`() = runTest {
        coEvery {
            dao.getRollerCoasterById(rollerCoasterId.value)
        } returns rollerCoasterRoomModel
        coEvery {
            dao.getPicturesByRollerCoasterId(rollerCoasterId.value)
        } returns picturesRoomModel

        val result = dataSource.getRollerCoaster(rollerCoasterId)

        assertThat(result.value).isEqualTo(rollerCoaster)

        coVerify(exactly = 1) {
            dao.getRollerCoasterById(rollerCoasterId.value)
            dao.getPicturesByRollerCoasterId(rollerCoasterId.value)
        }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Get roller coaster - Not found`() = runTest {
        coEvery { dao.getRollerCoasterById(rollerCoasterId.value) } returns null

        val result = dataSource.getRollerCoaster(rollerCoasterId)

        assertThat(result).isEqualTo(Err(NotFound))

        coVerify(exactly = 1) { dao.getRollerCoasterById(rollerCoasterId.value) }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Store roller coaster - Stores successfully`() = runTest {
        coEvery {
            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = listOf(rollerCoasterRoomModel),
            )
        } just runs

        dataSource.storeRollerCoaster(rollerCoaster)

        coVerify(exactly = 1) {
            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = listOf(rollerCoasterRoomModel)
            )
        }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Store multiple roller coasters - Stores successfully without page number`() = runTest {
        coEvery {
            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = rollerCoastersRoom,
            )
        } just runs

        dataSource.storeRollerCoasters(rollerCoasters, pageNumber = null)

        coVerify(exactly = 1) {
            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = rollerCoastersRoom,
            )
        }

        coVerify(exactly = 0) { dao.insertPaginatedRollerCoasters(any()) }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Store multiple roller coasters - Stores successfully with page number`() = runTest {
        coEvery {
            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = rollerCoastersRoom,
            )
        } just runs

        coEvery {
            dao.insertPaginatedRollerCoasters(any())
        } just runs

        val pageNumber = PageNumber(1)
        dataSource.storeRollerCoasters(rollerCoasters, pageNumber)

        coVerify(exactly = 1) {
            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = rollerCoastersRoom,
            )
            dao.insertPaginatedRollerCoasters(withArg {
                assertThat(it.pageNumber).isEqualTo(pageNumber.value)
                assertThat(it.rollerCoasterIds)
                    .isEqualTo(rollerCoasters.map { coaster -> coaster.id.value })
            })
        }
    }


    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Get roller coasters page - Successfully retrieves full page`() = runTest {

        val rollerCoasterIds = rollerCoasters.map { rollerCoaster -> rollerCoaster.id.value }
        val rollerCoasterIdsString: String? = ListConverters.fromIntList(rollerCoasterIds)

        coEvery { dao.getPaginatedRollerCoasters(pageNumber.value) } returns rollerCoasterIdsString

        rollerCoasters.forEach { coaster ->
            coEvery { dao.getRollerCoasterById(coaster.id.value) } returns rollerCoasterRoomModel
            coEvery { dao.getPicturesByRollerCoasterId(coaster.id.value) } returns picturesRoomModel
        }

        val result = dataSource.getRollerCoasters(pageNumber, pageSize)

        assertThat(result.value).isEqualTo(rollerCoasters)

        coVerify(exactly = 1) { dao.getPaginatedRollerCoasters(pageNumber.value) }

        rollerCoasters.forEach { coaster ->
            coVerify(exactly = 1) {
                dao.getRollerCoasterById(coaster.id.value)
                dao.getPicturesByRollerCoasterId(coaster.id.value)
            }
        }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Get roller coasters - No stored coasters for page`() = runTest {
        coEvery { dao.getPaginatedRollerCoasters(pageNumber.value) } returns null

        val result = dataSource.getRollerCoasters(pageNumber)

        assertThat(result).isEqualTo(Err(NotFound))

        coVerify(exactly = 1) { dao.getPaginatedRollerCoasters(pageNumber.value) }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Get roller coasters - Stored IDs count does not match expected size`() =
        runTest {
            val coasterIds = rollerCoasters.take(PAGE_SIZE - 1)
                .map { rollerCoaster -> rollerCoaster.id.value }

            coEvery {
                dao.getPaginatedRollerCoasters(pageNumber.value)
            } returns ListConverters.fromIntList(coasterIds)

            val result = dataSource.getRollerCoasters(pageNumber)

            assertThat(result).isEqualTo(Err(NotFound))

            coVerify(exactly = 1) { dao.getPaginatedRollerCoasters(pageNumber.value) }
        }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Get roller coasters - At least one missing coaster`() = runTest {
        val coasterIds = rollerCoasters.map { rollerCoaster -> rollerCoaster.id.value }

        coEvery {
            dao.getPaginatedRollerCoasters(pageNumber.value)
        } returns ListConverters.fromIntList(coasterIds)

        coEvery { dao.getRollerCoasterById(rollerCoasters[0].id.value) } returns null
        rollerCoasters.drop(1).forEach { coaster ->
            coEvery { dao.getRollerCoasterById(coaster.id.value) } returns rollerCoasterRoomModel
            coEvery { dao.getPicturesByRollerCoasterId(coaster.id.value) } returns picturesRoomModel
        }

        val result = dataSource.getRollerCoasters(pageNumber)

        assertThat(result).isEqualTo(Err(NotFound))

        coVerify(exactly = 1) { dao.getPaginatedRollerCoasters(pageNumber.value) }
        coVerify(exactly = 0) { dao.getRollerCoasterById(rollerCoasters[0].id.value) }
    }
}