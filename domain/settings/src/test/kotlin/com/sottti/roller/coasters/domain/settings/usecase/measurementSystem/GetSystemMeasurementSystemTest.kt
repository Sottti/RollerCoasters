package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class GetSystemMeasurementSystemTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getSystemMeasurementSystem: GetSystemMeasurementSystem

    @Before
    fun setUp() {
        settingsRepository = mockk()
        getSystemMeasurementSystem = GetSystemMeasurementSystem(settingsRepository)
    }

    @Test
    fun `returns imperial uk when repository provides imperial uk`() {
        every { settingsRepository.getSystemMeasurementSystem() } returns ImperialUk
        val result = getSystemMeasurementSystem()
        assertEquals(ImperialUk, result)
    }

    @Test
    fun `returns imperial us when repository provides imperial us`() {
        every { settingsRepository.getSystemMeasurementSystem() } returns ImperialUs
        val result = getSystemMeasurementSystem()
        assertEquals(ImperialUs, result)
    }

    @Test
    fun `returns metric when repository provides metric`() {
        every { settingsRepository.getSystemMeasurementSystem() } returns Metric
        val result = getSystemMeasurementSystem()
        assertEquals(Metric, result)
    }
}
