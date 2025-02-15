package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.stubs.picturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterRoomModel
import kotlinx.serialization.InternalSerializationApi
import org.junit.Test

@OptIn(InternalSerializationApi::class)
internal class RollerCoasterRoomMapperTest {

    @Test
    fun `map roller coaster domain model to room model`() {
        val result = rollerCoaster.toRoom()
        assertThat(result).isEqualTo(rollerCoasterRoomModel)
    }

    @Test
    fun `map pictures domain model to room model`() {
        val result = rollerCoaster.toPicturesRoom()
        assertThat(result).isEqualTo(picturesRoomModel)
    }
}