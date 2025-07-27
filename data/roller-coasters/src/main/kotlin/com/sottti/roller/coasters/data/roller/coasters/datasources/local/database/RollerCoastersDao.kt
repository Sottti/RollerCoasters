package com.sottti.roller.coasters.data.roller.coasters.datasources.local.database

import androidx.paging.PagingSource
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

@Dao
internal interface RollerCoastersDao {

    @Transaction
    suspend fun insertRollerCoasters(
        pictures: List<PictureRoomModel>,
        rollerCoasters: List<RollerCoasterRoomModel>,
    ) {
        insertRollerCoastersWithoutPictures(rollerCoasters)
        rollerCoasters.forEach { rollerCoaster -> deletePictures(rollerCoaster.id) }
        insertPictures(pictures)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRollerCoastersWithoutPictures(
        rollerCoasters: List<RollerCoasterRoomModel>,
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictures(pictures: List<PictureRoomModel>)

    @Query("DELETE FROM pictures WHERE rollerCoasterId = :rollerCoasterId")
    suspend fun deletePictures(rollerCoasterId: Int)

    @Query("SELECT * FROM roller_coasters WHERE id = :id")
    fun observeRollerCoaster(id: Int): Flow<RollerCoasterRoomModel?>

    @Query("SELECT * FROM roller_coasters WHERE id IN (:ids) ORDER BY id ASC")
    suspend fun getRollerCoasters(ids: List<Int>): List<RollerCoasterRoomModel>

    @Query("SELECT * FROM pictures WHERE rollerCoasterId = :rollerCoasterId")
    suspend fun getPictures(rollerCoasterId: Int): List<PictureRoomModel>

    @Query("SELECT * FROM pictures WHERE rollerCoasterId = :rollerCoasterId")
    fun observePictures(rollerCoasterId: Int): Flow<List<PictureRoomModel>>

    @RawQuery
    suspend fun getPagedRollerCoasters(query: SupportSQLiteQuery): List<RollerCoasterRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteRollerCoaster(favouriteRollerCoaster: FavouriteRollerCoasterRoomModel)

    @Query("DELETE FROM favourites WHERE rollerCoasterId = :rollerCoasterId")
    suspend fun removeFavouriteRollerCoaster(rollerCoasterId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE rollerCoasterId = :rollerCoasterId)")
    suspend fun isFavouriteRollerCoaster(rollerCoasterId: Int): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE rollerCoasterId = :rollerCoasterId)")
    fun observeIsFavouriteRollerCoasterFlow(rollerCoasterId: Int): Flow<Boolean>

    @Query(
        """
        SELECT * FROM roller_coasters
        WHERE id IN (SELECT rollerCoasterId FROM favourites)
        ORDER BY name_current ASC
    """,
    )
    fun observePagedFavouriteRollerCoasters(): PagingSource<Int, RollerCoasterRoomModel>
}
