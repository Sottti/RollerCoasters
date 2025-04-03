package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import kotlinx.serialization.InternalSerializationApi

// TODO: Create a test class

@Dao
internal interface RollerCoastersDao {

    @Transaction
    @OptIn(InternalSerializationApi::class)
    suspend fun insertRollerCoasters(
        pictures: List<PictureRoomModel>,
        rollerCoasters: List<RollerCoasterRoomModel>,
    ) {
        insertRollerCoastersWithoutPictures(rollerCoasters)
        rollerCoasters.forEach { rollerCoaster -> deletePictures(rollerCoaster.id) }
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

    @Query("DELETE FROM pictures WHERE rollerCoasterId = :rollerCoasterId")
    suspend fun deletePictures(rollerCoasterId: Int)

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM roller_coasters WHERE id = :id")
    suspend fun getRollerCoaster(id: Int): RollerCoasterRoomModel?

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM roller_coasters WHERE id IN (:ids) ORDER BY id ASC")
    suspend fun getRollerCoasters(ids: List<Int>): List<RollerCoasterRoomModel>

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM pictures WHERE rollerCoasterId = :id")
    suspend fun getPictures(id: Int): List<PictureRoomModel>

    @RawQuery
    @OptIn(InternalSerializationApi::class)
    suspend fun getPagedRollerCoasters(query: SupportSQLiteQuery): List<RollerCoasterRoomModel>
}