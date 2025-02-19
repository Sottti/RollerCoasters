package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherNotMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherPagedRollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.notMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.pagedRollerCoastersRoom
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
    fun insertAndRetrievePagedRollerCoasters() = runTest {

        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)

        val result = dao
            .getPagedRollerCoasters(pagedRollerCoastersRoom.page)
            ?.rollerCoasterIds

        assertThat(result).isEqualTo(pagedRollerCoastersRoom.rollerCoasterIds)
    }

    @Test
    fun retrieveWhenEmptyPagedRollerCoasters() = runTest {

        val result = dao
            .getPagedRollerCoasters(pagedRollerCoastersRoom.page)
            ?.rollerCoasterIds

        assertThat(result).isNull()
    }

    @Test
    fun insertAndReplacePagedRollerCoasters_shouldClearOldAndInsertNew() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)

        val newPagedRollerCoasters =
            pagedRollerCoastersRoom.copy(rollerCoasterIds = listOf(NON_EXISTENT_ID))

        dao.insertAndReplacePagedRollerCoasters(
            page = pagedRollerCoastersRoom.page,
            pictures = listOf(notMainPictureRoomModel),
            rollerCoasters = listOf(rollerCoasterRoomModel),
            pagedRollerCoasters = newPagedRollerCoasters
        )

        val result = dao
            .getPagedRollerCoasters(pagedRollerCoastersRoom.page)
            ?.rollerCoasterIds

        assertThat(result).isEqualTo(newPagedRollerCoasters.rollerCoasterIds)
    }

    @Test
    fun replacePagedRollerCoasters_shouldInsertAndReplaceMultiplePages() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)
        dao.insertPagedRollerCoasters(anotherPagedRollerCoastersRoom)

        val newPagedRollerCoasters =
            pagedRollerCoastersRoom.copy(rollerCoasterIds = listOf(1111))

        val newSecondPagedRollerCoasters =
            anotherPagedRollerCoastersRoom.copy(rollerCoasterIds = listOf(2222))

        dao.insertAndReplacePagedRollerCoasters(
            page = pagedRollerCoastersRoom.page,
            pictures = listOf(notMainPictureRoomModel),
            rollerCoasters = listOf(rollerCoasterRoomModel),
            pagedRollerCoasters = newPagedRollerCoasters
        )
        dao.insertAndReplacePagedRollerCoasters(
            page = anotherPagedRollerCoastersRoom.page,
            pictures = listOf(anotherNotMainPictureRoomModel),
            rollerCoasters = listOf(anotherRollerCoasterRoomModel),
            pagedRollerCoasters = newSecondPagedRollerCoasters
        )

        val resultFirstPage = dao
            .getPagedRollerCoasters(pagedRollerCoastersRoom.page)
            ?.rollerCoasterIds

        val resultSecondPage = dao
            .getPagedRollerCoasters(anotherPagedRollerCoastersRoom.page)
            ?.rollerCoasterIds

        assertThat(resultFirstPage).isEqualTo(newPagedRollerCoasters.rollerCoasterIds)
        assertThat(resultSecondPage).isEqualTo(newSecondPagedRollerCoasters.rollerCoasterIds)
    }


    @Test
    fun clearPage_shouldRemovePagedRollerCoasters() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)

        dao.clearPage(pagedRollerCoastersRoom.page)

        val result = dao.getPagedRollerCoasters(pagedRollerCoastersRoom.page)

        assertThat(result).isNull()
    }

    @Test
    fun clearPage_shouldNotAffectOtherPages() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)
        dao.insertPagedRollerCoasters(anotherPagedRollerCoastersRoom)

        dao.clearPage(pagedRollerCoastersRoom.page)

        val resultFirstPage = dao.getPagedRollerCoasters(pagedRollerCoastersRoom.page)
        val resultSecondPage = dao.getPagedRollerCoasters(anotherPagedRollerCoastersRoom.page)

        assertThat(resultFirstPage).isNull()
        assertThat(resultSecondPage).isNotNull()
    }

    @Test
    fun getPagedRollerCoasters_afterClearPage_shouldReturnNull() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)
        dao.clearPage(pagedRollerCoastersRoom.page)

        val result = dao.getPagedRollerCoasters(pagedRollerCoastersRoom.page)

        assertThat(result).isNull()
    }

    @Test
    fun clearPage_shouldNotDeleteRollerCoastersOrPictures() = runTest {
        dao.insertRollerCoasters(listOf(notMainPictureRoomModel), listOf(rollerCoasterRoomModel))
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)

        dao.clearPage(pagedRollerCoastersRoom.page)

        val coasterResult = dao.getRollerCoaster(rollerCoasterRoomModel.id)
        val picturesResult = dao.getPictures(rollerCoasterRoomModel.id)

        assertThat(coasterResult).isNotNull()
        assertThat(picturesResult).isNotEmpty()
    }

    @Test
    fun insertPagedRollerCoasters_shouldReplaceExistingPage() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)

        val newPagedRollerCoasters = pagedRollerCoastersRoom
            .copy(rollerCoasterIds = listOf(8888))

        dao.insertPagedRollerCoasters(newPagedRollerCoasters)

        val result = dao
            .getPagedRollerCoasters(pagedRollerCoastersRoom.page)
            ?.rollerCoasterIds

        assertThat(result).isEqualTo(newPagedRollerCoasters.rollerCoasterIds)
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
    fun getPagedRollerCoasters_shouldReturnCorrectPage() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)
        dao.insertPagedRollerCoasters(anotherPagedRollerCoastersRoom)

        val resultFirstPage = dao.getPagedRollerCoasters(pagedRollerCoastersRoom.page)
        val resultSecondPage = dao.getPagedRollerCoasters(anotherPagedRollerCoastersRoom.page)

        assertThat(resultFirstPage?.rollerCoasterIds)
            .isEqualTo(pagedRollerCoastersRoom.rollerCoasterIds)

        assertThat(resultSecondPage?.rollerCoasterIds)
            .isEqualTo(anotherPagedRollerCoastersRoom.rollerCoasterIds)
    }

    @Test
    fun insertAndReplacePagedRollerCoasters_withEmptyList_shouldClearPage() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)

        val emptyPagedRollerCoasters =
            pagedRollerCoastersRoom.copy(rollerCoasterIds = emptyList())

        dao.insertAndReplacePagedRollerCoasters(
            page = pagedRollerCoastersRoom.page,
            pictures = emptyList(),
            rollerCoasters = emptyList(),
            pagedRollerCoasters = emptyPagedRollerCoasters
        )

        val result = dao.getPagedRollerCoasters(pagedRollerCoastersRoom.page)

        assertThat(result).isEqualTo(emptyPagedRollerCoasters)
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
    fun clearPage_shouldOnlyAffectSpecificPage() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)
        dao.insertPagedRollerCoasters(anotherPagedRollerCoastersRoom)

        dao.clearPage(pagedRollerCoastersRoom.page)

        val resultFirstPage = dao.getPagedRollerCoasters(pagedRollerCoastersRoom.page)
        val resultSecondPage = dao.getPagedRollerCoasters(anotherPagedRollerCoastersRoom.page)

        assertThat(resultFirstPage).isNull()
        assertThat(resultSecondPage).isNotNull()
    }

    @Test
    fun insertPagedRollerCoasters_shouldInsertNewPageIfNotExists() = runTest {
        val newPagedRollerCoasters = pagedRollerCoastersRoom.copy(page = 100)

        dao.insertPagedRollerCoasters(newPagedRollerCoasters)

        val result = dao.getPagedRollerCoasters(100)

        assertThat(result).isNotNull()
        assertThat(result?.rollerCoasterIds).isEqualTo(newPagedRollerCoasters.rollerCoasterIds)
    }

    @Test
    fun insertAndReplacePagedRollerCoasters_shouldInsertIfPageDidNotExist() = runTest {
        val newPagedRollerCoasters = pagedRollerCoastersRoom.copy(page = 777)

        dao.insertAndReplacePagedRollerCoasters(
            page = 777,
            pictures = listOf(notMainPictureRoomModel),
            rollerCoasters = listOf(rollerCoasterRoomModel),
            pagedRollerCoasters = newPagedRollerCoasters
        )

        val result = dao.getPagedRollerCoasters(777)

        assertThat(result).isNotNull()
        assertThat(result?.rollerCoasterIds)
            .isEqualTo(newPagedRollerCoasters.rollerCoasterIds)
    }

    @Test
    fun clearPage_onNonExistentPage_shouldNotCrash() = runTest {
        dao.clearPage(NON_EXISTENT_ID)

        val result = dao.getPagedRollerCoasters(NON_EXISTENT_ID)

        assertThat(result).isNull()
    }

    @Test
    fun insertAndReplacePagedRollerCoasters_shouldNotDuplicateExistingIds() = runTest {
        dao.insertPagedRollerCoasters(pagedRollerCoastersRoom)

        val sameIdsPagedRollerCoasters = pagedRollerCoastersRoom.copy(
            rollerCoasterIds = pagedRollerCoastersRoom.rollerCoasterIds + listOf(8888)
        )

        dao.insertAndReplacePagedRollerCoasters(
            page = pagedRollerCoastersRoom.page,
            pictures = listOf(notMainPictureRoomModel),
            rollerCoasters = listOf(rollerCoasterRoomModel),
            pagedRollerCoasters = sameIdsPagedRollerCoasters
        )

        val result = dao.getPagedRollerCoasters(pagedRollerCoastersRoom.page)?.rollerCoasterIds

        assertThat(result).containsExactlyElementsIn(sameIdsPagedRollerCoasters.rollerCoasterIds)
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