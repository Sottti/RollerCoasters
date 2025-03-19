package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.MeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.MeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.MeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.MeasurementSystem.System
import org.junit.Test

internal class MeasurementSystemMapperTest {

    @Test
    fun `metric maps to its key`() {
        assertThat(Metric.key).isEqualTo(MEASUREMENT_SYSTEM_METRIC)
    }

    @Test
    fun `imperial us maps to its key`() {
        assertThat(ImperialUs.key).isEqualTo(MEASUREMENT_SYSTEM_IMPERIAL_US)
    }

    @Test
    fun `imperial uk maps to its key`() {
        assertThat(ImperialUk.key).isEqualTo(MEASUREMENT_SYSTEM_IMPERIAL_UK)
    }

    @Test
    fun `system maps to its key`() {
        assertThat(System.key).isEqualTo(MEASUREMENT_SYSTEM_SYSTEM)
    }

    @Test
    fun `imperial uk key maps to imperial uk`() {
        assertThat(MEASUREMENT_SYSTEM_IMPERIAL_UK.toMeasurementSystem())
            .isEqualTo(ImperialUk)
    }

    @Test
    fun `imperial us key maps to imperial us`() {
        assertThat(MEASUREMENT_SYSTEM_IMPERIAL_US.toMeasurementSystem())
            .isEqualTo(ImperialUs)
    }

    @Test
    fun `metric key maps to metric`() {
        assertThat(MEASUREMENT_SYSTEM_METRIC.toMeasurementSystem())
            .isEqualTo(Metric)
    }

    @Test
    fun `system key maps to system`() {
        assertThat(MEASUREMENT_SYSTEM_SYSTEM.toMeasurementSystem())
            .isEqualTo(System)
    }

    @Test
    fun `unknown key maps to system`() {
        assertThat("unknown_measurement".toMeasurementSystem())
            .isEqualTo(System)
    }

    @Test
    fun `empty key maps to system`() {
        assertThat("".toMeasurementSystem())
            .isEqualTo(System)
    }
}