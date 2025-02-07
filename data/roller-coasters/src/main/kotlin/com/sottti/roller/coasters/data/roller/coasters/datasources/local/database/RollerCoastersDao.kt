package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import kotlinx.serialization.InternalSerializationApi

@Dao
internal interface RollerCoastersDao {

    @Transaction
    @OptIn(InternalSerializationApi::class)
    suspend fun insertRollerCoastersWithPictures(
        pictures: List<PictureRoomModel>,
        rollerCoasters: List<RollerCoasterRoomModel>,
    ) {
        insertRollerCoasters(rollerCoasters)
        insertPictures(pictures)
    }

    @OptIn(InternalSerializationApi::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRollerCoasters(rollerCoasters: List<RollerCoasterRoomModel>)

    @OptIn(InternalSerializationApi::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictures(pictures: List<PictureRoomModel>)
}