package com.sottti.roller.coasters.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.settings.mapper.MEASUREMENT_SYSTEM_IMPERIAL_UK
import com.sottti.roller.coasters.data.settings.mapper.MEASUREMENT_SYSTEM_IMPERIAL_US
import com.sottti.roller.coasters.data.settings.mapper.MEASUREMENT_SYSTEM_METRIC
import com.sottti.roller.coasters.data.settings.mapper.MEASUREMENT_SYSTEM_SYSTEM
import com.sottti.roller.coasters.data.settings.mapper.key
import com.sottti.roller.coasters.data.settings.mapper.toAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System
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
        assertThat(MEASUREMENT_SYSTEM_IMPERIAL_UK.toAppMeasurementSystem())
            .isEqualTo(ImperialUk)
    }

    @Test
    fun `imperial us key maps to imperial us`() {
        assertThat(MEASUREMENT_SYSTEM_IMPERIAL_US.toAppMeasurementSystem())
            .isEqualTo(ImperialUs)
    }

    @Test
    fun `metric key maps to metric`() {
        assertThat(MEASUREMENT_SYSTEM_METRIC.toAppMeasurementSystem())
            .isEqualTo(Metric)
    }

    @Test
    fun `system key maps to system`() {
        assertThat(MEASUREMENT_SYSTEM_SYSTEM.toAppMeasurementSystem())
            .isEqualTo(System)
    }

    @Test
    fun `unknown key maps to system`() {
        assertThat("unknown_measurement".toAppMeasurementSystem())
            .isEqualTo(System)
    }

    @Test
    fun `empty key maps to system`() {
        assertThat("".toAppMeasurementSystem())
            .isEqualTo(System)
    }
}