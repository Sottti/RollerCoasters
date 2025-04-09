package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.rollerCoasterApiModel
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import org.junit.Test

internal class RollerCoastersApiMapperTest {

    @Test
    fun `map roller coaster api model to domain model when metric system`() {
        val result = rollerCoasterApiModel.toDomain(Metric)
        assertThat(result).isEqualTo(rollerCoaster)
    }

    @Test
    fun `map roller coaster api model to domain model when imperial Us system`() {
        val result = rollerCoasterApiModel.toDomain(ImperialUs)
        assertThat(result).isEqualTo(rollerCoaster(ImperialUs))
    }

    @Test
    fun `map roller coaster api model to domain model when imperial Uk system`() {
        val result = rollerCoasterApiModel.toDomain(ImperialUk)
        assertThat(result).isEqualTo(rollerCoaster(ImperialUk))
    }
}