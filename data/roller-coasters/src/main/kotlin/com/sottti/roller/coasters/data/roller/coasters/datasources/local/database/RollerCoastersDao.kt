package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PaginatedRollerCoasters
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
    suspend fun insertRollerCoastersWithoutPictures(rollerCoasters: List<RollerCoasterRoomModel>)

    @OptIn(InternalSerializationApi::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictures(pictures: List<PictureRoomModel>)

    @Transaction
    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM roller_coasters WHERE id = :id")
    suspend fun getRollerCoasterById(id: Int): RollerCoasterRoomModel?

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM pictures WHERE rollerCoasterId = :id")
    suspend fun getPicturesByRollerCoasterId(id: Int): List<PictureRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaginatedRollerCoasters(page: PaginatedRollerCoasters)

    @Query("SELECT roller_coaster_ids FROM roller_coaster_page WHERE pageNumber = :pageNumber")
    suspend fun getPaginatedRollerCoasters(pageNumber: Int): String?

    @Query("DELETE FROM roller_coaster_page WHERE pageNumber = :pageNumber")
    suspend fun deletePaginatedRollerCoasters(pageNumber: Int)
}