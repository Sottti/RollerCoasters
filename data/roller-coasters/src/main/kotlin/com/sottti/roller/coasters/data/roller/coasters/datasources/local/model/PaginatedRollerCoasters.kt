package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roller_coaster_page")
internal data class PaginatedRollerCoasters(
    @PrimaryKey(autoGenerate = false)
    val pageNumber: Int,

    @ColumnInfo(name = "roller_coaster_ids")
    val rollerCoasterIds: List<Int>,
)