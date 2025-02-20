package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherNotMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.notMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.stubs.COASTER_NAME_ANOTHER
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

        dao = database.dao()
    }

    @After
    fun teardown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun insertAndRetrieveRollerCoaster() = runTest {
        dao.insertRollerCoasters(
            pictures = listOf(notMainPictureRoomModel),
            rollerCoasters = listOf(rollerCoasterRoomModel),
        )

        val result: RollerCoasterRoomModel? =
            dao.getRollerCoaster(rollerCoasterRoomModel.id)

        assertThat(result).isEqualTo(rollerCoasterRoomModel)
    }

    @Test
    fun insertAndRetrievePictures() = runTest {
        dao.insertPictures(listOf(notMainPictureRoomModel))

        val result = dao.getPictures(rollerCoasterRoomModel.id)

        assertThat(result).isEqualTo(listOf(notMainPictureRoomModel))
    }

    @Test
    fun insertAndReplaceRollerCoaster() = runTest {

        dao.insertRollerCoasters(
            pictures = listOf(notMainPictureRoomModel),
            rollerCoasters = listOf(rollerCoasterRoomModel),
        )
        dao.insertRollerCoasters(
            pictures = listOf(anotherNotMainPictureRoomModel),
            rollerCoasters = listOf(anotherRollerCoasterRoomModel),
        )

        val result = dao.getRollerCoaster(anotherRollerCoasterRoomModel.id)

        assertThat(result).isEqualTo(anotherRollerCoasterRoomModel)
        assertThat(result).isNotEqualTo(rollerCoasterRoomModel)
    }

    @Test
    fun retrieveNonExistentRollerCoaster() = runTest {
        val result = dao.getRollerCoaster(NON_EXISTENT_ID)
        assertThat(result).isNull()
    }

    @Test
    fun retrieveNonExistentPictures() = runTest {
        val result = dao.getPictures(NON_EXISTENT_ID)
        assertThat(result).isEmpty()
    }

    @Test
    fun getRollerCoasters_shouldReturnCorrectResults() = runTest {
        dao.insertRollerCoasters(listOf(notMainPictureRoomModel), listOf(rollerCoasterRoomModel))

        val result = dao.getRollerCoasters(listOf(rollerCoasterRoomModel).map { it.id })

        assertThat(result).isEqualTo(listOf(rollerCoasterRoomModel))
    }

    @Test
    fun insertEmptyRollerCoasters_shouldNotCrash() = runTest {
        dao.insertRollerCoasters(
            pictures = emptyList(),
            rollerCoasters = emptyList()
        )

        val result = dao.getRollerCoaster(NON_EXISTENT_ID)
        assertThat(result).isNull()
    }

    @Test
    fun insertAndReplaceRollerCoaster_shouldKeepPictures() = runTest {
        dao.insertRollerCoasters(listOf(notMainPictureRoomModel), listOf(rollerCoasterRoomModel))

        val updatedRollerCoaster = rollerCoasterRoomModel.copy(
            name = rollerCoasterRoomModel.name.copy(current = COASTER_NAME_ANOTHER)
        )
        dao.insertRollerCoasters(listOf(notMainPictureRoomModel), listOf(updatedRollerCoaster))

        val pictures = dao.getPictures(updatedRollerCoaster.id)
        assertThat(pictures).isEqualTo(listOf(notMainPictureRoomModel))
    }

    @Test
    fun getRollerCoasters_withEmptyList_shouldReturnEmptyList() = runTest {
        dao.insertRollerCoasters(listOf(notMainPictureRoomModel), listOf(rollerCoasterRoomModel))

        val result = dao.getRollerCoasters(emptyList())

        assertThat(result).isEmpty()
    }

    @Test
    fun getRollerCoasters_withSomeInvalidIds_shouldReturnOnlyValidOnes() = runTest {
        dao.insertRollerCoasters(listOf(notMainPictureRoomModel), listOf(rollerCoasterRoomModel))

        val mixedIds = listOf(rollerCoasterRoomModel).map { it.id } + listOf(NON_EXISTENT_ID)
        val result = dao.getRollerCoasters(mixedIds)

        assertThat(result.map { it.id }).isEqualTo(listOf(rollerCoasterRoomModel).map { it.id })
    }
}

private const val NON_EXISTENT_ID = 9999