package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.converters.ListConverters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.FavouriteRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel

@Database(
    entities = [
        FavouriteRollerCoasterRoomModel::class,
        PictureRoomModel::class,
        RollerCoasterRoomModel::class,
    ],
    version = 2,
    exportSchema = false,
)
@TypeConverters(ListConverters::class)
internal abstract class RollerCoastersDatabase : RoomDatabase() {
    companion object {
        const val ROLLER_COASTERS_DATABASE_NAME: String = "roller_coasters.db"
        fun create(context: Context) =
            Room.databaseBuilder(
                context = context,
                klass = RollerCoastersDatabase::class.java,
                name = ROLLER_COASTERS_DATABASE_NAME,
            )
                .fallbackToDestructiveMigration(dropAllTables = true)
                .createFromAsset("databases/roller_coasters.db")
                .build()
    }

    abstract fun dao(): RollerCoastersDao
}