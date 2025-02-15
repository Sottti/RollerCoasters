package com.sottti.roller.coasters.data.roller.coasters

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDao
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.database.RollerCoastersDatabase
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
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
        database.close()
    }

    @Test
    fun insertAndRetrieveRollerCoaster() = runTest {
        dao.insertRollerCoasters(
            pictures = picturesRoom,
            rollerCoasters = rollerCoastersRoom,
        )

        val result: RollerCoasterRoomModel? =
            dao.getRollerCoasterById(rollerCoasterRoom.id)

        assertThat(result).isEqualTo(rollerCoasterRoom)
    }

    @Test
    fun insertAndRetrievePictures() = runTest {
        dao.insertPictures(picturesRoom)

        val result: List<PictureRoomModel> =
            dao.getPicturesByRollerCoasterId(rollerCoasterRoom.id)

        assertThat(result).isEqualTo(picturesRoom)
    }

    @Test
    fun insertAndReplaceRollerCoaster() = runTest {
        val updatedCoaster = rollerCoasterRoom.copy(
            name = rollerCoasterRoom.name.copy(current = "Updated Name"),
        )

        dao.insertRollerCoasters(
            pictures = picturesRoom,
            rollerCoasters = listOf(rollerCoasterRoom),
        )
        dao.insertRollerCoasters(
            pictures = picturesRoom,
            rollerCoasters = listOf(updatedCoaster),
        )

        val result = dao.getRollerCoasterById(updatedCoaster.id)

        assertThat(result).isEqualTo(updatedCoaster)
        assertThat(result).isNotEqualTo(rollerCoasterRoom)
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
}