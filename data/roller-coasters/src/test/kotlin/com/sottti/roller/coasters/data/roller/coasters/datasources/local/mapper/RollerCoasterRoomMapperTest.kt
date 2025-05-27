package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherNotMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.notMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import kotlinx.serialization.InternalSerializationApi
import org.junit.Test

@OptIn(InternalSerializationApi::class)
internal class RollerCoasterRoomMapperTest {

    @Test
    fun `map roller coaster domain model to room model`() {
        val result = rollerCoaster().toRoom()
        assertThat(result).isEqualTo(rollerCoasterRoomModel)
    }

    @Test
    fun `map another roller coaster domain model to room model`() {
        val result = anotherRollerCoaster().toRoom()
        assertThat(result).isEqualTo(anotherRollerCoasterRoomModel)
    }

    @Test
    fun `map pictures domain model to room model`() {
        val result = rollerCoaster().toPicturesRoom()
        assertThat(result).isEqualTo(listOf(notMainPictureRoomModel))
    }

    @Test
    fun `map another pictures domain model to room model`() {
        val result = anotherRollerCoaster().toPicturesRoom()
        assertThat(result).isEqualTo(listOf(anotherNotMainPictureRoomModel))
    }
}