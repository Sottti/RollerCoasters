package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.stubs.picturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoastersRoom
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

        Truth.assertThat(result).isEqualTo(rollerCoasterRoomModel)
    }

    @Test
    fun insertAndRetrievePictures() = runTest {
        dao.insertPictures(picturesRoomModel)

        val result: List<PictureRoomModel> =
            dao.getPicturesByRollerCoasterId(rollerCoasterRoomModel.id)

        Truth.assertThat(result).isEqualTo(picturesRoomModel)
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

        Truth.assertThat(result).isEqualTo(updatedCoaster)
        Truth.assertThat(result).isNotEqualTo(rollerCoasterRoomModel)
    }

    @Test
    fun retrieveNonExistentRollerCoaster() = runTest {
        val result = dao.getRollerCoasterById(9999)
        Truth.assertThat(result).isNull()
    }

    @Test
    fun retrieveNonExistentPictures() = runTest {
        val result = dao.getPicturesByRollerCoasterId(9999)
        Truth.assertThat(result).isEmpty()
    }
}