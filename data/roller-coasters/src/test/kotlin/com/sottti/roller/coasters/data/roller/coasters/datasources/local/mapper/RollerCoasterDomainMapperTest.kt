package com.sottti.roller.coasters.data.roller.coasters.datasources.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherNotMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.anotherRollerCoasterRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.notMainPictureRoomModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.rollerCoasterRoomModel
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import com.sottti.roller.coasters.utils.test.stubs.anotherRollerCoaster
import com.sottti.roller.coasters.utils.test.stubs.rollerCoaster
import kotlinx.serialization.InternalSerializationApi
import org.junit.Test

@OptIn(InternalSerializationApi::class)
internal class RollerCoasterDomainMapperTest {

    @Test
    fun `map roller coaster room model to domain model when metric system`() {
        val result = rollerCoasterRoomModel.toDomain(
            measurementSystem = Metric,
            pictures = listOf(notMainPictureRoomModel),
        )
        assertThat(result).isEqualTo(rollerCoaster(Metric))
    }

    @Test
    fun `map another roller coaster room model to domain model when metric system`() {
        val result = anotherRollerCoasterRoomModel.toDomain(
            measurementSystem = Metric,
            pictures = listOf(anotherNotMainPictureRoomModel),
        )
        assertThat(result).isEqualTo(anotherRollerCoaster(Metric))
    }

    @Test
    fun `map roller coaster room model to domain model when imperial UK system`() {
        val result = rollerCoasterRoomModel.toDomain(
            measurementSystem = ImperialUk,
            pictures = listOf(notMainPictureRoomModel),
        )
        assertThat(result).isEqualTo(rollerCoaster(ImperialUk))
    }

    @Test
    fun `map another roller coaster room model to domain model when imperial UK system`() {
        val result = anotherRollerCoasterRoomModel.toDomain(
            measurementSystem = ImperialUk,
            pictures = listOf(anotherNotMainPictureRoomModel),
        )
        assertThat(result).isEqualTo(anotherRollerCoaster(ImperialUk))
    }

    @Test
    fun `map roller coaster room model to domain model when imperial US system`() {
        val result = rollerCoasterRoomModel.toDomain(
            measurementSystem = ImperialUs,
            pictures = listOf(notMainPictureRoomModel),
        )
        assertThat(result).isEqualTo(rollerCoaster(ImperialUs))
    }

    @Test
    fun `map another roller coaster room model to domain model when imperial US system`() {
        val result = anotherRollerCoasterRoomModel.toDomain(
            measurementSystem = ImperialUs,
            pictures = listOf(anotherNotMainPictureRoomModel),
        )
        assertThat(result).isEqualTo(anotherRollerCoaster(ImperialUs))
    }
}