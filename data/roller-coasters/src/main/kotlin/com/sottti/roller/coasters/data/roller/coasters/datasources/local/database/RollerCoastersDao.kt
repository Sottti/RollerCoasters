package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
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

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM roller_coasters ORDER BY maxHeight DESC LIMIT :limit OFFSET :offset")
    suspend fun getPagedRollerCoastersSortedByHeight(
        limit: Int,
        offset: Int,
    ): List<RollerCoasterRoomModel>
}