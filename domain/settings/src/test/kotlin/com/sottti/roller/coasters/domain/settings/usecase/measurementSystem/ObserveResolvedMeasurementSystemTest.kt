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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveResolvedMeasurementSystemTest {

    private lateinit var getSystemMeasurementSystem: GetSystemMeasurementSystem
    private lateinit var observeAppMeasurementSystem: ObserveAppMeasurementSystem
    private lateinit var observeResolvedMeasurementSystem: ObserveResolvedMeasurementSystem

    @Before
    fun setUp() {
        getSystemMeasurementSystem = mockk()
        observeAppMeasurementSystem = mockk()
        observeResolvedMeasurementSystem = ObserveResolvedMeasurementSystem(
            getSystemMeasurementSystem = getSystemMeasurementSystem,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
        )
    }

    @Test
    fun `emits resolved metric for app metric`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.Metric
        coEvery { observeAppMeasurementSystem() } returns flowOf(appMeasurementSystem)
        val result = observeResolvedMeasurementSystem().toList()
        assertThat(result).containsExactly(Metric)
        coVerify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `emits resolved imperial us for app imperial us`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.ImperialUs
        coEvery { observeAppMeasurementSystem() } returns flowOf(appMeasurementSystem)
        val result = observeResolvedMeasurementSystem().toList()
        assertThat(result).containsExactly(ImperialUs)
        coVerify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `emits resolved imperial uk for app imperial uk`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.ImperialUk
        coEvery { observeAppMeasurementSystem() } returns flowOf(appMeasurementSystem)
        val result = observeResolvedMeasurementSystem().toList()
        assertThat(result).containsExactly(ImperialUk)
        coVerify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `emits resolved metric for app system when system is metric`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.System
        coEvery { observeAppMeasurementSystem() } returns flowOf(appMeasurementSystem)
        coEvery { getSystemMeasurementSystem() } returns SystemMeasurementSystem.Metric
        val result = observeResolvedMeasurementSystem().toList()
        assertThat(result).containsExactly(Metric)
        coVerify { getSystemMeasurementSystem() }
    }

    @Test
    fun `emits resolved imperial us for app system when system is imperial us`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.System
        coEvery { observeAppMeasurementSystem() } returns flowOf(appMeasurementSystem)
        coEvery { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUs
        val result = observeResolvedMeasurementSystem().toList()
        assertThat(result).containsExactly(ImperialUs)
        coVerify { getSystemMeasurementSystem() }
    }

    @Test
    fun `emits resolved imperial uk for app system when system is imperial uk`() = runTest {
        val appMeasurementSystem = AppMeasurementSystem.System
        coEvery { observeAppMeasurementSystem() } returns flowOf(appMeasurementSystem)
        coEvery { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUk
        val result = observeResolvedMeasurementSystem().toList()
        assertThat(result).containsExactly(ImperialUk)
        coVerify { getSystemMeasurementSystem() }
    }

    @Test
    fun `emits multiple resolved systems when app system changes`() = runTest {
        val appMeasurementSystems =
            listOf(AppMeasurementSystem.Metric, AppMeasurementSystem.ImperialUs)
        coEvery { observeAppMeasurementSystem() } returns flowOf(
            appMeasurementSystems[0],
            appMeasurementSystems[1],
        )
        val result = observeResolvedMeasurementSystem().toList()
        assertThat(result).containsExactly(
            Metric,
            ImperialUs
        ).inOrder()
        coVerify(exactly = 0) { getSystemMeasurementSystem() }
    }
}
