package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SetAppMeasurementSystemTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var setAppMeasurementSystem: SetAppMeasurementSystem

    @Before
    fun setUp() {
        settingsRepository = mockk()
        setAppMeasurementSystem = SetAppMeasurementSystem(settingsRepository)
    }

    @Test
    fun `sets imperial uk in repository when invoked with imperial uk`() = runTest {
        coEvery { settingsRepository.setAppMeasurementSystem(ImperialUk) } returns Unit
        setAppMeasurementSystem(ImperialUk)
        coVerify { settingsRepository.setAppMeasurementSystem(ImperialUk) }
    }

    @Test
    fun `sets imperial us in repository when invoked with imperial us`() = runTest {
        coEvery { settingsRepository.setAppMeasurementSystem(ImperialUs) } returns Unit
        setAppMeasurementSystem(ImperialUs)
        coVerify { settingsRepository.setAppMeasurementSystem(ImperialUs) }
    }

    @Test
    fun `sets metric in repository when invoked with metric`() = runTest {
        coEvery { settingsRepository.setAppMeasurementSystem(Metric) } returns Unit
        setAppMeasurementSystem(Metric)
        coVerify { settingsRepository.setAppMeasurementSystem(Metric) }
    }

    @Test
    fun `sets system in repository when invoked with system`() = runTest {
        coEvery { settingsRepository.setAppMeasurementSystem(System) } returns Unit
        setAppMeasurementSystem(System)
        coVerify { settingsRepository.setAppMeasurementSystem(System) }
    }
}