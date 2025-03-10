package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import androidx.sqlite.db.SimpleSQLiteQuery
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_DROP_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_G_FORCE_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_HEIGHT_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_INVERSIONS_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_LENGTH_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_MAX_VERTICAL_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_NAME_CURRENT
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_OPENED_DATE
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_SPEED_MAX
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.COL_TYPE
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.RollerCoasterRoomModelConstants.TABLE_ROLLER_COASTERS
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModelConstants
import com.sottti.roller.coasters.domain.model.SortByFilter
import com.sottti.roller.coasters.domain.model.TypeFilter

internal fun createRollerCoastersQuery(
    limit: Int,
    offset: Int,
    sortByFilter: SortByFilter,
    typeFilter: TypeFilter,
): SimpleSQLiteQuery {
    val args = mutableListOf<Any>()
    val query = StringBuilder("SELECT * FROM $TABLE_ROLLER_COASTERS")

    if (typeFilter != TypeFilter.ALL) {
        query.append(" WHERE LOWER($COL_TYPE) = ?")
        args += typeFilter.toSqlValue()
    }

    query.append(" ORDER BY ")
        .append(sortByFilter.toSqlValue())
        .append(" DESC, $COL_OPENED_DATE ASC, $COL_NAME_CURRENT ASC")
        .append(" LIMIT ? OFFSET ?")

    args += limit
    args += offset

    return SimpleSQLiteQuery(query.toString(), args.toTypedArray())
}

private fun SortByFilter.toSqlValue(): String =
    when (this) {
        SortByFilter.ALPHABETICAL -> COL_NAME_CURRENT
        SortByFilter.DROP -> COL_DROP_MAX
        SortByFilter.G_FORCE -> COL_G_FORCE_MAX
        SortByFilter.HEIGHT -> COL_HEIGHT_MAX
        SortByFilter.INVERSIONS -> COL_INVERSIONS_MAX
        SortByFilter.LENGTH -> COL_LENGTH_MAX
        SortByFilter.MAX_VERTICAL -> COL_MAX_VERTICAL_MAX
        SortByFilter.SPEED -> COL_SPEED_MAX
    }

private fun TypeFilter.toSqlValue(): String =
    when (this) {
        TypeFilter.ALL -> error("Should not reach this branch")
        TypeFilter.STEEL -> RollerCoasterApiModelConstants.VALUE_TYPE_STEEL
        TypeFilter.WOOD -> RollerCoasterApiModelConstants.VALUE_TYPE_WOOD
    }