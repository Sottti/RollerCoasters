package com.sottti.roller.coasters.data.roller.coasters.datasources.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paged_roller_coasters")
internal data class PagedRollerCoasters(
    @PrimaryKey(autoGenerate = false)
    val page: Int,

    @ColumnInfo(name = "roller_coaster_ids")
    val rollerCoasterIds: List<Int>,
)