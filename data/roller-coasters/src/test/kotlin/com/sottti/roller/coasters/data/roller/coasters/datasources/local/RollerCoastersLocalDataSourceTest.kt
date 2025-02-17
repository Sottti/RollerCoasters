package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.github.michaelbull.result.Err
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.picturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterId
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasters
import com.sottti.roller.coasters.domain.model.NotFound
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

        assertThat(result.isOk).isTrue()
        assertThat(result.value).isEqualTo(rollerCoaster)

        coVerify(exactly = 1) { dao.getRollerCoasterById(rollerCoasterId.value) }
        coVerify(exactly = 1) { dao.getPicturesByRollerCoasterId(rollerCoasterId.value) }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Get roller coaster - Not found`() = runTest {
        coEvery { dao.getRollerCoasterById(rollerCoasterId.value) } returns null

        val result = dataSource.getRollerCoaster(rollerCoasterId)

        assertThat(result.isErr).isTrue()
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
    fun `Store multiple roller coasters - Stores successfully`() = runTest {
        coEvery {
            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = rollerCoastersRoom,
            )
        } just runs

        dataSource.storeRollerCoasters(rollerCoasters)

        coVerify(exactly = 1) {
            dao.insertRollerCoasters(
                pictures = picturesRoomModel,
                rollerCoasters = rollerCoastersRoom
            )
        }
    }
}