package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.model.PagedRollerCoasters
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.PAGE_NUMBER_INITIAL
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.PAGE_NUMBER_SECOND
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherPagedRollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.pagedRollerCoastersRoom
import com.sottti.roller.coasters.data.roller.coasters.stubs.anotherRollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster
import org.junit.Test

internal class PagedRollerCoastersRoomMapperTest {

    @Test
    fun `converts list of roller coasters correctly`() {
        val page = PageNumber(PAGE_NUMBER_INITIAL)
        val result = listOf(rollerCoaster).toPagedRollerCoastersRoom(page)
        val expectedResult = pagedRollerCoastersRoom
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `with empty list results in empty IDs`() {
        val page = PageNumber(PAGE_NUMBER_INITIAL)
        val result = emptyList<RollerCoaster>().toPagedRollerCoastersRoom(page)
        val expectedResult = PagedRollerCoasters(
            page = PAGE_NUMBER_INITIAL,
            rollerCoasterIds = emptyList(),
        )

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `handles single roller coaster`() {
        val page = PageNumber(PAGE_NUMBER_SECOND)
        val result = listOf(anotherRollerCoaster).toPagedRollerCoastersRoom(page)
        val expectedResult = anotherPagedRollerCoastersRoom
        assertThat(result).isEqualTo(expectedResult)
    }
}