package com.sottti.roller.coasters.data.roller.coasters.datasources

import com.github.michaelbull.result.Err
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.picturesRoom
import com.sottti.roller.coasters.data.roller.coasters.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.rollerCoasterId
import com.sottti.roller.coasters.data.roller.coasters.rollerCoasterRoom
import com.sottti.roller.coasters.data.roller.coasters.rollerCoasters
import com.sottti.roller.coasters.data.roller.coasters.rollerCoastersRoom
import com.sottti.roller.coasters.domain.model.NotFound
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import junit.framework.TestCase.assertEquals
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

        coEvery { dao.getRollerCoasterById(rollerCoasterId.value) } returns rollerCoasterRoom
        coEvery { dao.getPicturesByRollerCoasterId(rollerCoasterId.value) } returns picturesRoom

        val result = dataSource.getRollerCoaster(rollerCoasterId)

        assert(result.isOk)
        assertEquals(result.value, rollerCoaster)
        coVerify(exactly = 1) { dao.getRollerCoasterById(rollerCoasterId.value) }
        coVerify(exactly = 1) { dao.getPicturesByRollerCoasterId(rollerCoasterId.value) }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Get roller coaster - Not found`() = runTest {
        coEvery { dao.getRollerCoasterById(rollerCoasterId.value) } returns null

        val result = dataSource.getRollerCoaster(rollerCoasterId)

        assert(result.isErr)
        assertEquals(result, Err(NotFound))
        coVerify(exactly = 1) { dao.getRollerCoasterById(rollerCoasterId.value) }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Store roller coaster - Stores successfully`() = runTest {
        coEvery {
            dao.insertRollerCoasters(
                pictures = picturesRoom,
                rollerCoasters = listOf(rollerCoasterRoom),
            )
        } just runs

        dataSource.storeRollerCoaster(rollerCoaster)

        coVerify(exactly = 1) {
            dao.insertRollerCoasters(
                pictures = picturesRoom,
                rollerCoasters = listOf(rollerCoasterRoom)
            )
        }
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `Store multiple roller coasters - Stores successfully`() = runTest {
        coEvery {
            dao.insertRollerCoasters(
                pictures = picturesRoom,
                rollerCoasters = rollerCoastersRoom,
            )
        } just runs

        dataSource.storeRollerCoasters(rollerCoasters)

        coVerify(exactly = 1) {
            dao.insertRollerCoasters(
                pictures = picturesRoom,
                rollerCoasters = rollerCoastersRoom
            )
        }
    }
}