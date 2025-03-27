package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveAppMeasurementSystemTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var observeAppMeasurementSystem: ObserveAppMeasurementSystem

    @Before
    fun setUp() {
        settingsRepository = mockk()
        observeAppMeasurementSystem = ObserveAppMeasurementSystem(settingsRepository)
    }

    @Test
    fun `emits imperial uk when repository observes imperial uk`() = runTest {
        every { settingsRepository.observeAppMeasurementSystem() } returns flowOf(ImperialUk)
        val emissions = observeAppMeasurementSystem().toList()
        assertThat(emissions).containsExactly(ImperialUk)
    }

    @Test
    fun `emits imperial us when repository observes imperial us`() = runTest {
        every { settingsRepository.observeAppMeasurementSystem() } returns flowOf(ImperialUs)
        val emissions = observeAppMeasurementSystem().toList()
        assertThat(emissions).containsExactly(ImperialUs)
    }

    @Test
    fun `emits metric when repository observes metric`() = runTest {
        every { settingsRepository.observeAppMeasurementSystem() } returns flowOf(Metric)
        val emissions = observeAppMeasurementSystem().toList()
        assertThat(emissions).containsExactly(Metric)
    }

    @Test
    fun `emits system when repository observes system`() = runTest {
        every { settingsRepository.observeAppMeasurementSystem() } returns flowOf(System)
        val emissions = observeAppMeasurementSystem().toList()
        assertThat(emissions).containsExactly(System)
    }

    @Test
    fun `emits multiple values when repository observes multiple measurement systems`() = runTest {
        every {
            settingsRepository.observeAppMeasurementSystem()
        } returns flowOf(ImperialUk, Metric)
        val emissions = observeAppMeasurementSystem().toList()
        assertThat(emissions).containsExactly(ImperialUk, Metric)
    }
}