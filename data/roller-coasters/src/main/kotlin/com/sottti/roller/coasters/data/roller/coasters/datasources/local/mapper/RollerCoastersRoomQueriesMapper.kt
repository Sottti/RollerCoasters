package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import androidx.sqlite.db.SimpleSQLiteQuery
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_DROP_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_G_FORCE_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_HEIGHT_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_INVERSIONS_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_LENGTH_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_MAX_VERTICAL_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_NAME_CURRENT
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_OPENED_DATE
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_SPEED_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.COL_TYPE
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoasterRoomConstants.TABLE_ROLLER_COASTERS
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoasterApiConstants
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter

internal fun createFilteredRollerCoastersQuery(
    limit: Int,
    offset: Int,
    sortByFilter: SortByFilter,
    typeFilter: TypeFilter,
): SimpleSQLiteQuery {
    val args = buildList<Any> {
        if (typeFilter != TypeFilter.All) add(typeFilter.toSqlValue())
        add(limit)
        add(offset)
    }

    val query = buildString {
        append("SELECT * FROM $TABLE_ROLLER_COASTERS")
        if (typeFilter != TypeFilter.All) {
            append(" WHERE LOWER($COL_TYPE) = ?")
        }

        val direction = if (sortByFilter == SortByFilter.Alphabetical) "ASC" else "DESC"

        append(" ORDER BY ")
        append(sortByFilter.toSqlValue())
        append(" $direction, ")
        append("$COL_OPENED_DATE ASC, $COL_NAME_CURRENT ASC ")
        append("LIMIT ? OFFSET ?")
    }

    return SimpleSQLiteQuery(query, args.toTypedArray<Any>())
}

private fun SortByFilter.toSqlValue(): String =
    when (this) {
        SortByFilter.Alphabetical -> COL_NAME_CURRENT
        SortByFilter.Drop -> COL_DROP_MAX
        SortByFilter.GForce -> COL_G_FORCE_MAX
        SortByFilter.Height -> COL_HEIGHT_MAX
        SortByFilter.Inversions -> COL_INVERSIONS_MAX
        SortByFilter.Length -> COL_LENGTH_MAX
        SortByFilter.MaxVertical -> COL_MAX_VERTICAL_MAX
        SortByFilter.Speed -> COL_SPEED_MAX
    }

private fun TypeFilter.toSqlValue(): String =
    when (this) {
        TypeFilter.All -> error("Should not reach this branch")
        TypeFilter.Steel -> RollerCoasterApiConstants.VALUE_TYPE_STEEL
        TypeFilter.Wood -> RollerCoasterApiConstants.VALUE_TYPE_WOOD
    }