package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters.ListConverters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherPaginatedRollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.emptyPaginatedRollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.pageNumber
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.paginatedRollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.picturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoastersRoom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.InternalSerializationApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class, InternalSerializationApi::class)
internal class RollerCoastersDaoTest {

    private lateinit var dao: RollerCoastersDao
    private lateinit var database: RollerCoastersDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = RollerCoastersDatabase::class.java,
        )
            .allowMainThreadQueries()
            .build()

        dao = database.rollerCoastersDao()
    }

    @After
    fun teardown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun insertAndRetrieveRollerCoaster() = runTest {
        dao.insertRollerCoasters(
            pictures = picturesRoomModel,
            rollerCoasters = rollerCoastersRoom,
        )

        val result: RollerCoasterRoomModel? =
            dao.getRollerCoasterById(rollerCoasterRoomModel.id)

        assertThat(result).isEqualTo(rollerCoasterRoomModel)
    }

    @Test
    fun insertAndRetrievePictures() = runTest {
        dao.insertPictures(picturesRoomModel)

        val result: List<PictureRoomModel> =
            dao.getPicturesByRollerCoasterId(rollerCoasterRoomModel.id)

        assertThat(result).isEqualTo(picturesRoomModel)
    }

    @Test
    fun insertAndReplaceRollerCoaster() = runTest {
        val updatedCoaster = rollerCoasterRoomModel.copy(
            name = rollerCoasterRoomModel.name.copy(current = "Updated Name"),
        )

        dao.insertRollerCoasters(
            pictures = picturesRoomModel,
            rollerCoasters = listOf(rollerCoasterRoomModel),
        )
        dao.insertRollerCoasters(
            pictures = picturesRoomModel,
            rollerCoasters = listOf(updatedCoaster),
        )

        val result = dao.getRollerCoasterById(updatedCoaster.id)

        assertThat(result).isEqualTo(updatedCoaster)
        assertThat(result).isNotEqualTo(rollerCoasterRoomModel)
    }

    @Test
    fun retrieveNonExistentRollerCoaster() = runTest {
        val result = dao.getRollerCoasterById(9999)
        assertThat(result).isNull()
    }

    @Test
    fun retrieveNonExistentPictures() = runTest {
        val result = dao.getPicturesByRollerCoasterId(9999)
        assertThat(result).isEmpty()
    }

    @Test
    fun insertAndRetrievePaginatedRollerCoasters() = runTest {
        dao.insertPaginatedRollerCoasters(paginatedRollerCoastersRoom)

        val result = dao
            .getPaginatedRollerCoasters(pageNumber.value)
            ?.let(ListConverters::toIntList)

        assertThat(result).isEqualTo(paginatedRollerCoastersRoom.rollerCoasterIds)
    }

    @Test
    fun insertAndReplacePaginatedRollerCoasters() = runTest {
        dao.insertPaginatedRollerCoasters(paginatedRollerCoastersRoom)
        dao.insertPaginatedRollerCoasters(anotherPaginatedRollerCoastersRoom)

        val resultList = dao
            .getPaginatedRollerCoasters(pageNumber.value)
            ?.let(ListConverters::toIntList)

        assertThat(resultList).isEqualTo(anotherPaginatedRollerCoastersRoom.rollerCoasterIds)
    }

    @Test
    fun retrieveNonExistentPaginatedRollerCoasters() = runTest {
        val resultList = dao
            .getPaginatedRollerCoasters(9999)
            ?.let(ListConverters::toIntList)

        assertThat(resultList).isNull()
    }

    @Test
    fun deletePaginatedRollerCoasters() = runTest {
        dao.insertPaginatedRollerCoasters(paginatedRollerCoastersRoom)
        dao.deletePaginatedRollerCoasters(pageNumber.value)

        val resultList = dao
            .getPaginatedRollerCoasters(pageNumber.value)
            ?.let(ListConverters::toIntList)

        assertThat(resultList).isNull()
    }

    @Test
    fun insertAndRetrieveEmptyPaginatedRollerCoasters() = runTest {
        dao.insertPaginatedRollerCoasters(emptyPaginatedRollerCoastersRoom)

        val resultList = dao
            .getPaginatedRollerCoasters(pageNumber.value)
            ?.let(ListConverters::toIntList)

        assertThat(resultList).isEqualTo(emptyList<Int>())
    }
}