package com.sottti.roller.coasters.domain.settings.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

internal class MeasurementSystemMapperTest {

    private val getSystemMeasurementSystem: () -> SystemMeasurementSystem = mockk()

    @Test
    fun `maps metric to resolved metric`() {
        val appMeasurementSystem = AppMeasurementSystem.Metric
        val result = appMeasurementSystem.toResolvedMeasurementSystem(getSystemMeasurementSystem)
        assertThat(result).isEqualTo(ResolvedMeasurementSystem.Metric)
        verify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `maps imperial us to resolved imperial us`() {
        val appMeasurementSystem = AppMeasurementSystem.ImperialUs
        val result = appMeasurementSystem.toResolvedMeasurementSystem(getSystemMeasurementSystem)
        assertThat(result).isEqualTo(ResolvedMeasurementSystem.ImperialUs)
        verify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `maps imperial uk to resolved imperial uk`() {
        val appMeasurementSystem = AppMeasurementSystem.ImperialUk
        val result = appMeasurementSystem.toResolvedMeasurementSystem(getSystemMeasurementSystem)
        assertThat(result).isEqualTo(ResolvedMeasurementSystem.ImperialUk)
        verify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `maps system to resolved metric when system is metric`() {
        val appMeasurementSystem = AppMeasurementSystem.System
        every { getSystemMeasurementSystem() } returns SystemMeasurementSystem.Metric
        val result = appMeasurementSystem.toResolvedMeasurementSystem(getSystemMeasurementSystem)
        assertThat(result).isEqualTo(ResolvedMeasurementSystem.Metric)
        verify(exactly = 1) { getSystemMeasurementSystem() }
    }

    @Test
    fun `maps system to resolved imperial us when system is imperial us`() {
        val appMeasurementSystem = AppMeasurementSystem.System
        every { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUs
        val result = appMeasurementSystem.toResolvedMeasurementSystem(getSystemMeasurementSystem)
        assertThat(result).isEqualTo(ResolvedMeasurementSystem.ImperialUs)
        verify(exactly = 1) { getSystemMeasurementSystem() }
    }

    @Test
    fun `maps system to resolved imperial uk when system is imperial uk`() {
        val appMeasurementSystem = AppMeasurementSystem.System
        every { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUk
        val result = appMeasurementSystem.toResolvedMeasurementSystem(getSystemMeasurementSystem)
        assertThat(result).isEqualTo(ResolvedMeasurementSystem.ImperialUk)
        verify(exactly = 1) { getSystemMeasurementSystem() }
    }
}