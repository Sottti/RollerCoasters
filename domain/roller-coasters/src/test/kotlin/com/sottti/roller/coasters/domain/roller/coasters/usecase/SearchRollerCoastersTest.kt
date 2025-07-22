package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetResolvedMeasurementSystem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SearchRollerCoastersTest {

    private lateinit var getResolvedMeasurementSystem: GetResolvedMeasurementSystem
    private lateinit var repository: RollerCoastersRepository
    private lateinit var searchRollerCoasters: SearchRollerCoasters

    @Before
    fun setUp() {
        getResolvedMeasurementSystem = mockk()
        repository = mockk()
        searchRollerCoasters = SearchRollerCoasters(
            getResolvedMeasurementSystem = getResolvedMeasurementSystem,
            rollerCoastersRepository = repository,
        )
    }

    @Test
    fun `uses metric when app metric`() = runTest {
        val query = SearchQuery(rollerCoaster(Metric).name.current.value)
        val expected = listOf(rollerCoaster(Metric))
        coEvery { getResolvedMeasurementSystem() } returns Metric
        coEvery { repository.searchRollerCoasters(Metric, query) } returns Ok(expected)

        val result = searchRollerCoasters(query)

        assertThat(result).isEqualTo(Ok(expected))
        coVerify { repository.searchRollerCoasters(Metric, query) }
    }

    @Test
    fun `uses imperial uk when app imperial uk`() = runTest {
        val query = SearchQuery(rollerCoaster(Metric).name.current.value)
        val expected = listOf(rollerCoaster(ImperialUk))
        coEvery { getResolvedMeasurementSystem() } returns ImperialUk
        coEvery { repository.searchRollerCoasters(ImperialUk, query) } returns Ok(expected)

        val result = searchRollerCoasters(query)

        assertThat(result).isEqualTo(Ok(expected))
        coVerify { repository.searchRollerCoasters(ImperialUk, query) }
    }

    @Test
    fun `uses resolved system when app system`() = runTest {
        val query = SearchQuery(rollerCoaster(Metric).name.current.value)
        val expected = listOf(rollerCoaster(ImperialUs))
        coEvery { getResolvedMeasurementSystem() } returns ImperialUs
        coEvery { repository.searchRollerCoasters(ImperialUs, query) } returns Ok(expected)

        val result = searchRollerCoasters(query)

        assertThat(result).isEqualTo(Ok(expected))
        coVerify { repository.searchRollerCoasters(ImperialUs, query) }
    }
}