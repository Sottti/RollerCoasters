package com.sottti.roller.coasters.domain.roller.coasters.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import org.junit.Test

internal class SystemMeasurementSystemMapperTest {

    val getSystemMeasurementSystemMetric = { Metric }
    val getSystemMeasurementSystemImperialUk = { ImperialUk }
    val getSystemMeasurementSystemImperialUs = { ImperialUs }

    @Test
    fun `maps uk imperial to system uk imperial`() {
        val result = AppMeasurementSystem
            .ImperialUk
            .toSystemMeasurementSystem(getSystemMeasurementSystemMetric)
        assertThat(result).isEqualTo(ImperialUk)
    }

    @Test
    fun `maps us imperial to system us imperial`() {
        val result = AppMeasurementSystem
            .ImperialUs
            .toSystemMeasurementSystem(getSystemMeasurementSystemMetric)
        assertThat(result).isEqualTo(ImperialUs)
    }

    @Test
    fun `maps metric to system metric`() {
        val result = AppMeasurementSystem
            .Metric
            .toSystemMeasurementSystem(getSystemMeasurementSystemImperialUk)
        assertThat(result).isEqualTo(Metric)
    }

    @Test
    fun `maps system to provided uk imperial`() {
        val result = AppMeasurementSystem
            .System
            .toSystemMeasurementSystem(getSystemMeasurementSystemImperialUk)
        assertThat(result).isEqualTo(ImperialUk)
    }

    @Test
    fun `maps system to provided us imperial`() {
        val result = AppMeasurementSystem
            .System
            .toSystemMeasurementSystem(getSystemMeasurementSystemImperialUs)
        assertThat(result).isEqualTo(ImperialUs)
    }

    @Test
    fun `maps system to provided metric`() {
        val result = AppMeasurementSystem
            .System
            .toSystemMeasurementSystem(getSystemMeasurementSystemMetric)
        assertThat(result).isEqualTo(Metric)
    }
}