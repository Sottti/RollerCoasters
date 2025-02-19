package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PagedRollerCoasters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import kotlinx.serialization.InternalSerializationApi

@Dao
internal interface RollerCoastersDao {

    @Transaction
    @OptIn(InternalSerializationApi::class)
    suspend fun insertRollerCoasters(
        pictures: List<PictureRoomModel>,
        rollerCoasters: List<RollerCoasterRoomModel>,
    ) {
        insertRollerCoastersWithoutPictures(rollerCoasters)
        insertPictures(pictures)
    }

    @OptIn(InternalSerializationApi::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRollerCoastersWithoutPictures(
        rollerCoasters: List<RollerCoasterRoomModel>,
    )

    @OptIn(InternalSerializationApi::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictures(pictures: List<PictureRoomModel>)

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM roller_coasters WHERE id = :id")
    suspend fun getRollerCoaster(id: Int): RollerCoasterRoomModel?

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM roller_coasters WHERE id IN (:ids) ORDER BY id ASC")
    suspend fun getRollerCoasters(ids: List<Int>): List<RollerCoasterRoomModel>

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM pictures WHERE rollerCoasterId = :id")
    suspend fun getPictures(id: Int): List<PictureRoomModel>

    @Transaction
    @OptIn(InternalSerializationApi::class)
    suspend fun insertAndReplacePagedRollerCoasters(
        page: Int,
        pictures: List<PictureRoomModel>,
        rollerCoasters: List<RollerCoasterRoomModel>,
        pagedRollerCoasters: PagedRollerCoasters,
    ) {
        clearPage(page)
        insertRollerCoasters(pictures, rollerCoasters)
        insertPagedRollerCoasters(pagedRollerCoasters)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPagedRollerCoasters(page: PagedRollerCoasters)

    @Query("SELECT * FROM paged_roller_coasters WHERE page = :page")
    suspend fun getPagedRollerCoasters(page: Int): PagedRollerCoasters?

    @Transaction
    suspend fun clearPageWithCleanup(page: Int) {
        clearPage(page)
        deleteOrphanedRollerCoasters()
    }

    @Query("DELETE FROM paged_roller_coasters WHERE page = :page")
    suspend fun clearPage(page: Int)

    @Query("DELETE FROM roller_coasters WHERE id NOT IN (SELECT rollerCoasterId FROM paged_roller_coasters)")
    suspend fun deleteOrphanedRollerCoasters()
}