package com.sottti.roller.coasters.domain.roller.coasters.usecase

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetSystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveAppMeasurementSystem
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ObserveRollerCoastersTest {

    private lateinit var getSystemMeasurementSystem: GetSystemMeasurementSystem
    private lateinit var observeAppMeasurementSystem: ObserveAppMeasurementSystem
    private lateinit var rollerCoastersRepository: RollerCoastersRepository
    private lateinit var observeRollerCoasters: ObserveRollerCoasters

    private val sortByFilter = SortByFilter.Alphabetical
    private val typeFilter = TypeFilter.Steel

    @Before
    fun setUp() {
        getSystemMeasurementSystem = mockk()
        observeAppMeasurementSystem = mockk()
        rollerCoastersRepository = mockk()
        observeRollerCoasters = ObserveRollerCoasters(
            getSystemMeasurementSystem = getSystemMeasurementSystem,
            observeAppMeasurementSystem = observeAppMeasurementSystem,
            rollerCoastersRepository = rollerCoastersRepository
        )
    }

    @Test
    fun `observes roller coasters with given filters`() = runTest {
        every { observeAppMeasurementSystem() } returns flowOf(AppMeasurementSystem.Metric)
        every {
            rollerCoastersRepository.observeRollerCoasters(
                measurementSystem = SystemMeasurementSystem.Metric,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(PagingData.from(listOf(rollerCoaster(SystemMeasurementSystem.Metric))))

        val result = observeRollerCoasters(sortByFilter, typeFilter).toList()

        assertThat(result).hasSize(1)
        verify(exactly = 1) {
            rollerCoastersRepository.observeRollerCoasters(
                measurementSystem = SystemMeasurementSystem.Metric,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        }
        verify(exactly = 0) { getSystemMeasurementSystem() }
    }

    @Test
    fun `uses system measurement system when app system is selected`() = runTest {
        every { observeAppMeasurementSystem() } returns flowOf(AppMeasurementSystem.System)
        every { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUs
        every {
            rollerCoastersRepository.observeRollerCoasters(
                measurementSystem = SystemMeasurementSystem.ImperialUs,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(PagingData.from(listOf(rollerCoaster(SystemMeasurementSystem.ImperialUs))))

        val result = observeRollerCoasters(sortByFilter, typeFilter).toList()

        assertThat(result).hasSize(1)
        verify(exactly = 1) {
            rollerCoastersRepository.observeRollerCoasters(
                measurementSystem = SystemMeasurementSystem.ImperialUs,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        }
        verify(exactly = 1) { getSystemMeasurementSystem() }
    }

    @Test
    fun `reacts to multiple measurement system changes`() = runTest {
        every { observeAppMeasurementSystem() } returns flowOf(
            AppMeasurementSystem.Metric,
            AppMeasurementSystem.System,
        )
        every { getSystemMeasurementSystem() } returns SystemMeasurementSystem.ImperialUs
        every {
            rollerCoastersRepository.observeRollerCoasters(
                measurementSystem = SystemMeasurementSystem.Metric,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(PagingData.from(listOf(rollerCoaster(SystemMeasurementSystem.Metric))))
        every {
            rollerCoastersRepository.observeRollerCoasters(
                measurementSystem = SystemMeasurementSystem.ImperialUs,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(PagingData.from(listOf(rollerCoaster(SystemMeasurementSystem.ImperialUs))))

        val result = observeRollerCoasters(sortByFilter, typeFilter).toList()

        assertThat(result).hasSize(2)
        verify(exactly = 1) {
            rollerCoastersRepository.observeRollerCoasters(
                measurementSystem = SystemMeasurementSystem.Metric,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        }
        verify(exactly = 1) {
            rollerCoastersRepository.observeRollerCoasters(
                measurementSystem = SystemMeasurementSystem.ImperialUs,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        }
        verify(exactly = 1) { getSystemMeasurementSystem() }
    }
}