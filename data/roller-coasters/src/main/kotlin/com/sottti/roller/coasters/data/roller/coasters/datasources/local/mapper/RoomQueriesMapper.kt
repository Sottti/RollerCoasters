package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import androidx.sqlite.db.SimpleSQLiteQuery
import com.sottti.roller.coasters.domain.model.SortByFilter
import com.sottti.roller.coasters.domain.model.TypeFilter

internal fun createRollerCoastersQuery(
    limit: Int,
    offset: Int,
    sortByFilter: SortByFilter,
    typeFilter: TypeFilter,
): SimpleSQLiteQuery {
    val query = StringBuilder("SELECT * FROM roller_coasters")

    if (typeFilter != TypeFilter.ALL) {
        query.append(" WHERE LOWER(type) = '").append(typeFilter.toSqlValue()).append("'")
    }

    query.append(" ORDER BY ").append(sortByFilter.toSqlValue())
        .append(" DESC LIMIT ? OFFSET ?")

    return SimpleSQLiteQuery(query.toString(), arrayOf(limit, offset))
}

private fun SortByFilter.toSqlValue(): String =
    when (this) {
        SortByFilter.ALPHABETICAL -> "name_current"
        SortByFilter.DROP -> "`dropMax`"
        SortByFilter.G_FORCE -> "gForceMax"
        SortByFilter.HEIGHT -> "heightMax"
        SortByFilter.INVERSIONS -> "inversionsMax"
        SortByFilter.LENGTH -> "lengthMax"
        SortByFilter.MAX_VERTICAL -> "maxVerticalMax"
        SortByFilter.SPEED -> "speedMax"
    }

private fun TypeFilter.toSqlValue(): String =
    when (this) {
        TypeFilter.ALL -> ""
        TypeFilter.STEEL -> "steel"
        TypeFilter.WOOD -> "wood"
    }