package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters.ListConverters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PaginatedRollerCoasters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
@Database(
    entities = [
        PictureRoomModel::class,
        RollerCoasterRoomModel::class,
        PaginatedRollerCoasters::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(ListConverters::class)
internal abstract class RollerCoastersDatabase : RoomDatabase() {
    companion object {
        const val ROLLER_COASTERS_DATABASE_NAME: String = "roller_coasters_db"
    }

    abstract fun rollerCoastersDao(): RollerCoastersDao
}