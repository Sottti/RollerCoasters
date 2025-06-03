package com.sottti.roller.coasters.domain.roller.coasters.usecase

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveResolvedMeasurementSystem
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ObserveRollerCoastersTest {

    private lateinit var observeResolvedMeasurementSystem: ObserveResolvedMeasurementSystem
    private lateinit var observeFilteredRollerCoasters: ObserveFilteredRollerCoasters
    private lateinit var rollerCoastersRepository: RollerCoastersRepository

    private val sortByFilter = SortByFilter.Alphabetical
    private val typeFilter = TypeFilter.Steel

    @Before
    fun setUp() {
        observeResolvedMeasurementSystem = mockk()
        rollerCoastersRepository = mockk()
        observeFilteredRollerCoasters = ObserveFilteredRollerCoasters(
            observeResolvedMeasurementSystem = observeResolvedMeasurementSystem,
            rollerCoastersRepository = rollerCoastersRepository,
        )
    }

    @Test
    fun `observing roller coasters with metric system emits metric roller coasters`() = runTest {
        val metricRollerCoasters = listOf(rollerCoaster(Metric))
        val pagingData = PagingData.from(metricRollerCoasters)

        every { observeResolvedMeasurementSystem() } returns flowOf(Metric)
        every {
            rollerCoastersRepository.observeFilteredRollerCoasters(
                measurementSystem = Metric,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(pagingData)

        val snapshot = observeFilteredRollerCoasters(sortByFilter, typeFilter).asSnapshot()

        assertThat(snapshot).containsExactlyElementsIn(metricRollerCoasters)
    }

    @Test
    fun `observing roller coasters with imperial Uk system emits imperial Uk roller coasters`() =
        runTest {
            val imperialUkRollerCoasters = listOf(rollerCoaster(ImperialUk))
            val pagingData = PagingData.from(imperialUkRollerCoasters)

            every { observeResolvedMeasurementSystem() } returns flowOf(ImperialUk)
            every {
                rollerCoastersRepository.observeFilteredRollerCoasters(
                    measurementSystem = ImperialUk,
                    sortByFilter = sortByFilter,
                    typeFilter = typeFilter,
                )
            } returns flowOf(pagingData)

            val snapshot = observeFilteredRollerCoasters(sortByFilter, typeFilter).asSnapshot()

            assertThat(snapshot).containsExactlyElementsIn(imperialUkRollerCoasters)
        }

    @Test
    fun `observing roller coasters with imperial Us system emits imperial Us roller coasters`() =
        runTest {
            val imperialUsRollerCoasters = listOf(rollerCoaster(ImperialUs))
            val pagingData = PagingData.from(imperialUsRollerCoasters)

            every { observeResolvedMeasurementSystem() } returns flowOf(ImperialUs)
            every {
                rollerCoastersRepository.observeFilteredRollerCoasters(
                    measurementSystem = ImperialUs,
                    sortByFilter = sortByFilter,
                    typeFilter = typeFilter,
                )
            } returns flowOf(pagingData)

            val snapshot = observeFilteredRollerCoasters(sortByFilter, typeFilter).asSnapshot()

            assertThat(snapshot).containsExactlyElementsIn(imperialUsRollerCoasters)
        }

    @Test
    fun `switching measurement system emits roller coasters for each system`() = runTest {
        val metricRollerCoasters = listOf(rollerCoaster(Metric))
        val imperialUkRollerCoasters = listOf(rollerCoaster(ImperialUk))
        val imperialUsRollerCoasters = listOf(rollerCoaster(ImperialUs))
        val metricPagingData = PagingData.from(metricRollerCoasters)
        val imperialUkPagingData = PagingData.from(imperialUkRollerCoasters)
        val imperialUsPagingData = PagingData.from(imperialUsRollerCoasters)

        every { observeResolvedMeasurementSystem() } returns flow {
            emit(Metric)
            emit(ImperialUk)
            emit(ImperialUs)
        }
        every {
            rollerCoastersRepository.observeFilteredRollerCoasters(
                measurementSystem = Metric,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(metricPagingData)
        every {
            rollerCoastersRepository.observeFilteredRollerCoasters(
                measurementSystem = ImperialUk,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(imperialUkPagingData)
        every {
            rollerCoastersRepository.observeFilteredRollerCoasters(
                measurementSystem = ImperialUs,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(imperialUsPagingData)

        val emissions = observeFilteredRollerCoasters(sortByFilter, typeFilter).take(3).toList()

        assertThat(emissions.size).isEqualTo(3)
    }

    @Test
    fun `observing roller coasters with no data emits empty paging data`() = runTest {
        val emptyPagingData = PagingData.empty<RollerCoaster>()

        every { observeResolvedMeasurementSystem() } returns flowOf(Metric)
        every {
            rollerCoastersRepository.observeFilteredRollerCoasters(
                measurementSystem = Metric,
                sortByFilter = sortByFilter,
                typeFilter = typeFilter,
            )
        } returns flowOf(emptyPagingData)

        val snapshot = observeFilteredRollerCoasters(sortByFilter, typeFilter).asSnapshot()

        assertThat(snapshot).isEmpty()
    }
}