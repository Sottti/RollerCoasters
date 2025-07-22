package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class GetResolvedMeasurementSystemTest {

    private lateinit var getAppMeasurementSystem: GetAppMeasurementSystem
    private lateinit var getResolvedMeasurementSystem: GetResolvedMeasurementSystem
    private lateinit var getSystemMeasurementSystem: GetSystemMeasurementSystem

    @Before
    fun setUp() {
        getSystemMeasurementSystem = mockk()
        getAppMeasurementSystem = mockk()
        getResolvedMeasurementSystem = GetResolvedMeasurementSystem(
            getSystemMeasurementSystem = getSystemMeasurementSystem,
            getAppMeasurementSystem = getAppMeasurementSystem,
        )
    }

    @Test
    fun `gets resolved metric for app metric`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.Metric
        coEvery { getAppMeasurementSystem() } returns appMeasurementSystem
        val result = getResolvedMeasurementSystem()
        assertThat(result).isEqualTo(Metric)
        coVerify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `gets resolved imperial us for app imperial us`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.ImperialUs
        coEvery { getAppMeasurementSystem() } returns appMeasurementSystem
        val result = getResolvedMeasurementSystem()
        assertThat(result).isEqualTo(ImperialUs)
        coVerify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `gets resolved imperial uk for app imperial uk`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.ImperialUk
        coEvery { getAppMeasurementSystem() } returns appMeasurementSystem
        val result = getResolvedMeasurementSystem()
        assertThat(result).isEqualTo(ImperialUk)
        coVerify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `gets resolved metric for app system when system is metric`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.System
        coEvery { getAppMeasurementSystem() } returns appMeasurementSystem
        coEvery { getSystemMeasurementSystem() } returns SystemMeasurementSystem.Metric
        val result = getResolvedMeasurementSystem()
        assertThat(result).isEqualTo(Metric)
        coVerify { getSystemMeasurementSystem() }
    }

    @Test
    fun `gets resolved imperial us for app system when system is imperial us`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.System
        coEvery { getAppMeasurementSystem() } returns appMeasurementSystem
        coEvery { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUs
        val result = getResolvedMeasurementSystem()
        assertThat(result).isEqualTo(ImperialUs)
        coVerify { getSystemMeasurementSystem() }
    }

    @Test
    fun `gets resolved imperial uk for app system when system is imperial uk`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.System
        coEvery { getAppMeasurementSystem() } returns appMeasurementSystem
        coEvery { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUk
        val result = getResolvedMeasurementSystem()
        assertThat(result).isEqualTo(ImperialUk)
        coVerify { getSystemMeasurementSystem() }
    }
}