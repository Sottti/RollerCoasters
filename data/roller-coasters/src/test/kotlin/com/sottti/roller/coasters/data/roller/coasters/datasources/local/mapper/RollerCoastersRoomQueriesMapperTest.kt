package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import org.junit.Assert.assertEquals
import org.junit.Test

class RollerCoastersRoomQueriesMapperTest {

    @Test
    fun `query with height sort by filter and all type filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 10,
            offset = 0,
            sortByFilter = SortByFilter.Height,
            typeFilter = TypeFilter.All,
        )

        val expectedSql = "SELECT * FROM roller_coasters ORDER BY heightMax " +
                "DESC, openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(2, actual.argCount)
    }

    @Test
    fun `query with steel type filter and speed sort by filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 5,
            offset = 10,
            sortByFilter = SortByFilter.Speed,
            typeFilter = TypeFilter.Steel,
        )

        val expectedSql =
            "SELECT * FROM roller_coasters WHERE LOWER(type) = ? ORDER BY " +
                    "speedMax DESC, openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(3, actual.argCount)
    }

    @Test
    fun `query with wood type filter and alphabetical sort by filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 20,
            offset = 40,
            sortByFilter = SortByFilter.Alphabetical,
            typeFilter = TypeFilter.Wood,
        )

        val expectedSql =
            "SELECT * FROM roller_coasters WHERE LOWER(type) = ? ORDER BY " +
                    "name_current ASC, openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(3, actual.argCount)
    }

    @Test
    fun `query with drop sort by filter and no type filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 15,
            offset = 5,
            sortByFilter = SortByFilter.Drop,
            typeFilter = TypeFilter.All,
        )

        val expectedSql =
            "SELECT * FROM roller_coasters ORDER BY dropMax DESC, " +
                    "openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(2, actual.argCount)
    }

    @Test
    fun `query with max vertical sort by filter and wood type filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 8,
            offset = 2,
            sortByFilter = SortByFilter.MaxVertical,
            typeFilter = TypeFilter.Wood,
        )

        val expectedSql =
            "SELECT * FROM roller_coasters WHERE LOWER(type) = ? ORDER BY " +
                    "maxVerticalMax DESC, openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(3, actual.argCount)
    }

    @Test
    fun `query with inversions sort by filter and steel type filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 12,
            offset = 6,
            sortByFilter = SortByFilter.Inversions,
            typeFilter = TypeFilter.Steel,
        )

        val expectedSql =
            "SELECT * FROM roller_coasters WHERE LOWER(type) = ? ORDER BY " +
                    "inversionsMax DESC, openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(3, actual.argCount)
    }

    @Test
    fun `query with length sort by filter and no type filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 25,
            offset = 15,
            sortByFilter = SortByFilter.Length,
            typeFilter = TypeFilter.All,
        )

        val expectedSql =
            "SELECT * FROM roller_coasters ORDER BY lengthMax DESC, " +
                    "openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(2, actual.argCount)
    }

    @Test
    fun `query with g-force sort by filter and no type filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 30,
            offset = 0,
            sortByFilter = SortByFilter.GForce,
            typeFilter = TypeFilter.All,
        )

        val expectedSql =
            "SELECT * FROM roller_coasters ORDER BY gForceMax DESC, " +
                    "openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(2, actual.argCount)
    }

    @Test
    fun `query with alphabetical sort by filter and all type filter`() {
        val actual = createFilteredRollerCoastersQuery(
            limit = 50,
            offset = 25,
            sortByFilter = SortByFilter.Alphabetical,
            typeFilter = TypeFilter.All
        )

        val expectedSql =
            "SELECT * FROM roller_coasters ORDER BY name_current ASC, " +
                    "openedDate ASC, name_current ASC LIMIT ? OFFSET ?"
        assertEquals(expectedSql, actual.sql)
        assertEquals(2, actual.argCount)
    }
}
