package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem.System
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class GetAppMeasurementSystemTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getAppMeasurementSystem: GetAppMeasurementSystem

    @Before
    fun setUp() {
        settingsRepository = mockk()
        getAppMeasurementSystem = GetAppMeasurementSystem(settingsRepository)
    }

    @Test
    fun `returns imperial uk when repository provides imperial uk`() = runTest {
        coEvery { settingsRepository.getAppMeasurementSystem() } returns ImperialUk
        val result = getAppMeasurementSystem()
        assertEquals(ImperialUk, result)
    }

    @Test
    fun `returns imperial us when repository provides imperial us`() = runTest {
        coEvery { settingsRepository.getAppMeasurementSystem() } returns ImperialUs
        val result = getAppMeasurementSystem()
        assertEquals(ImperialUs, result)
    }

    @Test
    fun `returns metric when repository provides metric`() = runTest {
        coEvery { settingsRepository.getAppMeasurementSystem() } returns Metric
        val result = getAppMeasurementSystem()
        assertEquals(Metric, result)
    }

    @Test
    fun `returns system when repository provides system`() = runTest {
        coEvery { settingsRepository.getAppMeasurementSystem() } returns System
        val result = getAppMeasurementSystem()
        assertEquals(System, result)
    }
}