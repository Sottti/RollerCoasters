package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoasterId
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
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ObserveRollerCoasterTest {

    private lateinit var observeResolvedMeasurementSystem: ObserveResolvedMeasurementSystem
    private lateinit var observeRollerCoaster: ObserveRollerCoaster
    private lateinit var rollerCoastersRepository: RollerCoastersRepository

    @Before
    fun setUp() {
        observeResolvedMeasurementSystem = mockk()
        rollerCoastersRepository = mockk()
        observeRollerCoaster = ObserveRollerCoaster(
            observeResolvedMeasurementSystem = observeResolvedMeasurementSystem,
            rollerCoastersRepository = rollerCoastersRepository,
        )
    }

    @Test
    fun `observing roller coaster with metric system emits metric coaster`() = runTest {
        val metricRollerCoaster = rollerCoaster(Metric)
        every { observeResolvedMeasurementSystem() } returns flowOf(Metric)
        every {
            rollerCoastersRepository.observeRollerCoaster(
                id = rollerCoasterId(),
                measurementSystem = Metric,
            )
        } returns flowOf(metricRollerCoaster)

        val result = observeRollerCoaster(rollerCoasterId()).toList()

        assertThat(result).containsExactly(metricRollerCoaster)
    }

    @Test
    fun `observing roller coaster with imperial uk system emits imperial uk coaster`() = runTest {
        val imperialUkRollerCoaster = rollerCoaster(ImperialUk)
        every { observeResolvedMeasurementSystem() } returns flowOf(ImperialUk)
        every {
            rollerCoastersRepository.observeRollerCoaster(
                id = rollerCoasterId(),
                measurementSystem = ImperialUk,
            )
        } returns flowOf(imperialUkRollerCoaster)

        val result = observeRollerCoaster(rollerCoasterId()).toList()

        assertThat(result).containsExactly(imperialUkRollerCoaster)
    }

    @Test
    fun `observing roller coaster with imperial us system emits imperial us coaster`() = runTest {
        val imperialUsRollerCoaster = rollerCoaster(ImperialUs)
        every { observeResolvedMeasurementSystem() } returns flowOf(ImperialUs)
        every {
            rollerCoastersRepository.observeRollerCoaster(
                id = rollerCoasterId(),
                measurementSystem = ImperialUs,
            )
        } returns flowOf(imperialUsRollerCoaster)

        val result = observeRollerCoaster(rollerCoasterId()).toList()

        assertThat(result).containsExactly(imperialUsRollerCoaster)
    }

    @Test
    fun `switching measurement system emits roller coaster for each system`() = runTest {
        val metricRollerCoaster = rollerCoaster(Metric)
        val imperialUkRollerCoaster = rollerCoaster(ImperialUk)
        val imperialUsRollerCoaster = rollerCoaster(ImperialUs)
        every { observeResolvedMeasurementSystem() } returns flow {
            emit(Metric)
            emit(ImperialUk)
            emit(ImperialUs)
        }
        every {
            rollerCoastersRepository.observeRollerCoaster(
                id = rollerCoasterId(),
                measurementSystem = Metric,
            )
        } returns flowOf(metricRollerCoaster)
        every {
            rollerCoastersRepository.observeRollerCoaster(
                id = rollerCoasterId(),
                measurementSystem = ImperialUk,
            )
        } returns flowOf(imperialUkRollerCoaster)
        every {
            rollerCoastersRepository.observeRollerCoaster(
                id = rollerCoasterId(),
                measurementSystem = ImperialUs,
            )
        } returns flowOf(imperialUsRollerCoaster)

        val result = observeRollerCoaster(rollerCoasterId()).toList()

        assertThat(result)
            .containsExactly(metricRollerCoaster, imperialUkRollerCoaster, imperialUsRollerCoaster)
            .inOrder()
    }
}
