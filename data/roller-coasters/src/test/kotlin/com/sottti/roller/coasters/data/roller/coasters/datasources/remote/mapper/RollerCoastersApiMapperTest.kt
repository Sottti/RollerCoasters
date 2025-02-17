package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.rollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import org.junit.Test

internal class RollerCoastersApiMapperTest {

    @Test
    fun `map roller coaster api model to domain model`() {
        val result = rollerCoasterApiModel.toDomain()
        assertThat(result).isEqualTo(rollerCoaster)
    }

}