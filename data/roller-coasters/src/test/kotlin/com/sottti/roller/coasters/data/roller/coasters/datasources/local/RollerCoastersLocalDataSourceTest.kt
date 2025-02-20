package com.sottti.roller.coasters.data.roller.coasters.datasources.local

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.notMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterId
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
    fun `store roller coaster calls store roller coaster with single item`() = runTest {
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
    fun `get roller coaster returns roller coaster when data exists`() = runTest {
        coEvery { dao.getRollerCoaster(rollerCoasterId.value) } returns rollerCoasterRoomModel
        coEvery { dao.getPictures(rollerCoasterId.value) } returns listOf(notMainPictureRoomModel)

        val result = localDataSource.getRollerCoaster(rollerCoasterId)

        assertThat(result).isEqualTo(Ok(rollerCoaster))
    }

    @Test
    @OptIn(InternalSerializationApi::class)
    fun `ger roller coaster returns error when not found`() = runTest {
        coEvery { dao.getRollerCoaster(rollerCoasterId.value) } returns null

        val result = localDataSource.getRollerCoaster(rollerCoasterId)

        assertThat(result).isEqualTo(Err(NotFound))
    }
}