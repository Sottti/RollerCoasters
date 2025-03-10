package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sottti.roller.coasters.domain.model.SortByFilter
import com.sottti.roller.coasters.domain.model.TypeFilter
import org.junit.Assert.assertEquals
import org.junit.Test

class RollerCoastersQueriesMapperTest {

    @Test
    fun `query with height sort by filter and all type filter`() {
        val actual = createRollerCoastersQuery(
            limit = 10,
            offset = 0,
            sortByFilter = SortByFilter.HEIGHT,
            typeFilter = TypeFilter.ALL
        )

        val expectedSql = "SELECT * FROM roller_coasters ORDER BY heightMax DESC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(2, actual.argCount)
    }

    @Test
    fun `query with steel type filter and speed sort by filter`() {
        val actual = createRollerCoastersQuery(
            limit = 5,
            offset = 10,
            sortByFilter = SortByFilter.SPEED,
            typeFilter = TypeFilter.STEEL
        )

        val expectedSql =
            "SELECT * FROM roller_coasters WHERE LOWER(type) = 'steel' ORDER BY speedMax DESC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(2, actual.argCount)
    }

    @Test
    fun `query with wood type filter and alphabetical sort by filter`() {
        val actual = createRollerCoastersQuery(
            limit = 20,
            offset = 40,
            sortByFilter = SortByFilter.ALPHABETICAL,
            typeFilter = TypeFilter.WOOD
        )

        val expectedSql =
            "SELECT * FROM roller_coasters WHERE LOWER(type) = 'wood' ORDER BY name_current DESC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(2, actual.argCount)
    }
}