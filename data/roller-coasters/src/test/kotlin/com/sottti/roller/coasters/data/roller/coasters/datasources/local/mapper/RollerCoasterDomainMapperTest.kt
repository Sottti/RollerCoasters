package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.picturesRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import kotlinx.serialization.InternalSerializationApi
import org.junit.Test

@OptIn(InternalSerializationApi::class)
internal class RollerCoasterDomainMapperTest {

    @Test
    fun `map roller coaster room model to domain model`() {
        val result = rollerCoasterRoomModel.toDomain(picturesRoomModel)
        assertThat(result).isEqualTo(rollerCoaster)
    }
}