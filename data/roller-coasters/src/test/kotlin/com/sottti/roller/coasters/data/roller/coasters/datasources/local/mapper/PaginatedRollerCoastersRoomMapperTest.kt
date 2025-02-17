package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PaginatedRollerCoasters
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasters
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster
import org.junit.Test

internal class PaginatedRollerCoastersRoomMapperTest {

    @Test
    fun `converts list of roller coasters correctly`() {
        val pageNumber = PageNumber(3)
        val result = rollerCoasters.toPaginatedRollerCoastersRoom(pageNumber)
        val expectedResult = PaginatedRollerCoasters(
            pageNumber = 3,
            rollerCoasterIds = rollerCoasters.map { it.id.value },
        )

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `with empty list results in empty IDs`() {
        val pageNumber = PageNumber(1)
        val result = emptyList<RollerCoaster>().toPaginatedRollerCoastersRoom(pageNumber)
        val expectedResult = PaginatedRollerCoasters(
            pageNumber = 1,
            rollerCoasterIds = emptyList(),
        )

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `handles single roller coaster`() {
        val pageNumber = PageNumber(2)
        val result = listOf(rollerCoaster).toPaginatedRollerCoastersRoom(pageNumber)
        val expectedResult = PaginatedRollerCoasters(
            pageNumber = 2,
            rollerCoasterIds = listOf(rollerCoaster.id.value),
        )

        assertThat(result).isEqualTo(expectedResult)
    }
}