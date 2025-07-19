package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.*
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetSystemMeasurementSystem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchRollerCoastersTest {

    private lateinit var getAppMeasurementSystem: GetAppMeasurementSystem
    private lateinit var getSystemMeasurementSystem: GetSystemMeasurementSystem
    private lateinit var repository: RollerCoastersRepository
    private lateinit var searchRollerCoasters: SearchRollerCoasters

    @Before
    fun setUp() {
        getAppMeasurementSystem = mockk()
        getSystemMeasurementSystem = mockk()
        repository = mockk()
        searchRollerCoasters = SearchRollerCoasters(
            getAppMeasurementSystem = getAppMeasurementSystem,
            getSystemMeasurementSystem = getSystemMeasurementSystem,
            rollerCoastersRepository = repository,
        )
    }

    @Test
    fun `uses metric when app metric`() = runTest {
        val query = "test"
        val expected = listOf(rollerCoaster(Metric))
        coEvery { getAppMeasurementSystem() } returns AppMeasurementSystem.Metric
        coEvery { repository.searchRollerCoasters(query, Metric) } returns Ok(expected)

        val result = searchRollerCoasters(query)

        assertThat(result).isEqualTo(Ok(expected))
        coVerify { repository.searchRollerCoasters(query, Metric) }
    }

    @Test
    fun `uses imperial uk when app imperial uk`() = runTest {
        val query = "test"
        val expected = listOf(rollerCoaster(ImperialUk))
        coEvery { getAppMeasurementSystem() } returns AppMeasurementSystem.ImperialUk
        coEvery { repository.searchRollerCoasters(query, ImperialUk) } returns Ok(expected)

        val result = searchRollerCoasters(query)

        assertThat(result).isEqualTo(Ok(expected))
        coVerify { repository.searchRollerCoasters(query, ImperialUk) }
    }

    @Test
    fun `uses resolved system when app system`() = runTest {
        val query = "test"
        val expected = listOf(rollerCoaster(ImperialUs))
        coEvery { getAppMeasurementSystem() } returns AppMeasurementSystem.System
        coEvery { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUs
        coEvery { repository.searchRollerCoasters(query, ImperialUs) } returns Ok(expected)

        val result = searchRollerCoasters(query)

        assertThat(result).isEqualTo(Ok(expected))
        coVerify { repository.searchRollerCoasters(query, ImperialUs) }
    }
}
