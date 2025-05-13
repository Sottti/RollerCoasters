package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.FavouriteRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModel
import kotlinx.coroutines.flow.Flow
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
    fun observeRollerCoaster(id: Int): Flow<RollerCoasterRoomModel?>

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM roller_coasters WHERE id IN (:ids) ORDER BY id ASC")
    suspend fun getRollerCoasters(ids: List<Int>): List<RollerCoasterRoomModel>

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM pictures WHERE rollerCoasterId = :rollerCoasterId")
    suspend fun getPictures(rollerCoasterId: Int): List<PictureRoomModel>

    @OptIn(InternalSerializationApi::class)
    @Query("SELECT * FROM pictures WHERE rollerCoasterId = :rollerCoasterId")
    fun observePictures(rollerCoasterId: Int): Flow<List<PictureRoomModel>>

    @RawQuery
    @OptIn(InternalSerializationApi::class)
    suspend fun getPagedRollerCoasters(query: SupportSQLiteQuery): List<RollerCoasterRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteRollerCoaster(favouriteRollerCoaster: FavouriteRollerCoasterRoomModel)

    @Query("DELETE FROM favourites WHERE rollerCoasterId = :rollerCoasterId")
    suspend fun removeFavouriteRollerCoaster(rollerCoasterId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE rollerCoasterId = :rollerCoasterId)")
    suspend fun isFavouriteRollerCoasterFlow(rollerCoasterId: Int): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE rollerCoasterId = :rollerCoasterId)")
    fun observeIsFavouriteRollerCoasterFlow(rollerCoasterId: Int): Flow<Boolean>

    @RawQuery
    @OptIn(InternalSerializationApi::class)
    suspend fun getPagedFavouriteRollerCoasters(
        query: SupportSQLiteQuery,
    ): List<RollerCoasterRoomModel>
}