package com.sottti.roller.coasters.data.roller.coasters.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.stubs.syncFailedException
import com.sottti.roller.coasters.data.roller.coasters.sync.RollerCoasterSyncScheduler
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUk
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.ImperialUs
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class RollerCoastersRepositoryImplTest {
    private lateinit var repository: RollerCoastersRepositoryImpl
    private val localDataSource: RollerCoastersLocalDataSource = mockk()
    private val remoteDataSource: RollerCoastersRemoteDataSource = mockk()
    private val rollerCoasterSyncScheduler = mockk<RollerCoasterSyncScheduler>()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        repository = RollerCoastersRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            rollerCoasterSyncScheduler = rollerCoasterSyncScheduler,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `observing roller coaster with local data emits roller coaster for metric system`() =
        runTest {
            coEvery {
                localDataSource.observeRollerCoaster(rollerCoasterId(), Metric)
            } returns flowOf(rollerCoaster())

            val emissions = repository.observeRollerCoaster(rollerCoasterId(), Metric).toList()

            assertThat(emissions).containsExactly(rollerCoaster())
        }

    @Test
    fun `observing roller coaster with local data emits roller coaster for imperial Us system`() =
        runTest {
            val imperialRollerCoaster = rollerCoaster(ImperialUs)
            coEvery {
                localDataSource.observeRollerCoaster(rollerCoasterId(), ImperialUs)
            } returns flowOf(imperialRollerCoaster)

            val emissions = repository.observeRollerCoaster(rollerCoasterId(), ImperialUs).toList()

            assertThat(emissions).containsExactly(imperialRollerCoaster)
        }

    @Test
    fun `observing roller coaster with local data emits roller coaster for imperial Uk system`() =
        runTest {
            val imperialRollerCoaster = rollerCoaster(ImperialUk)
            coEvery {
                localDataSource.observeRollerCoaster(rollerCoasterId(), ImperialUk)
            } returns flowOf(imperialRollerCoaster)

            val emissions = repository.observeRollerCoaster(rollerCoasterId(), ImperialUk).toList()

            assertThat(emissions).containsExactly(imperialRollerCoaster)
        }

    @Test
    fun `observing roller coaster with no local data fetches from remote and emits roller coaster`() =
        runTest {
            coEvery {
                localDataSource.observeRollerCoaster(
                    rollerCoasterId(),
                    Metric,
                )
            } returns flow {
                emit(null)
                emit(rollerCoaster())
            }
            coEvery {
                remoteDataSource.getRollerCoaster(rollerCoasterId(), Metric)
            } returns Ok(rollerCoaster())
            coEvery { localDataSource.storeRollerCoaster(rollerCoaster()) } just runs

            val emissions = repository.observeRollerCoaster(rollerCoasterId(), Metric).toList()

            assertThat(emissions).containsExactly(rollerCoaster())
        }

    @Test
    fun `observing roller coaster with no local data and remote failure emits nothing`() = runTest {
        coEvery {
            localDataSource.observeRollerCoaster(rollerCoasterId(), Metric)
        } returns flowOf(null)
        coEvery {
            remoteDataSource.getRollerCoaster(rollerCoasterId(), Metric)
        } returns Err(syncFailedException)

        val emissions = repository.observeRollerCoaster(rollerCoasterId(), Metric).toList()

        assertThat(emissions).isEmpty()
    }

    @Test
    fun `observing roller coaster reacts to local data update after remote fetch`() = runTest {
        coEvery { localDataSource.observeRollerCoaster(rollerCoasterId(), Metric) } returns flow {
            emit(null)
            emit(rollerCoaster())
            emit(anotherRollerCoaster())
        }
        coEvery { remoteDataSource.getRollerCoaster(rollerCoasterId(), Metric) } returns Ok(
            rollerCoaster()
        )
        coEvery { localDataSource.storeRollerCoaster(rollerCoaster()) } just runs

        val emissions = repository.observeRollerCoaster(rollerCoasterId(), Metric).toList()

        assertThat(emissions).containsExactly(rollerCoaster(), anotherRollerCoaster())
    }

    @Test
    fun `sync roller coasters calls remote and stores data`() = runTest {
        val rollerCoasters = listOf(rollerCoaster(), anotherRollerCoaster())

        coEvery {
            remoteDataSource.syncRollerCoasters(onStoreRollerCoasters = any())
        } coAnswers {
            val lambda = firstArg<suspend (List<RollerCoaster>) -> Unit>()
            lambda(rollerCoasters)
            Ok(Unit)
        }
        coEvery { localDataSource.storeRollerCoasters(rollerCoasters) } just runs

        val result = repository.syncAllRollerCoasters()

        assertThat(result).isEqualTo(Ok(Unit))
        coVerify(exactly = 1) { remoteDataSource.syncRollerCoasters(onStoreRollerCoasters = any()) }
        coVerify(exactly = 1) { localDataSource.storeRollerCoasters(rollerCoasters) }
    }

    @Test
    fun `sync roller coasters fails when remote fails`() = runTest {
        coEvery {
            remoteDataSource.syncRollerCoasters(onStoreRollerCoasters = any())
        } returns Err(syncFailedException)

        val result = repository.syncAllRollerCoasters()

        assertThat(result).isEqualTo(Err(syncFailedException))
        coVerify(exactly = 1) { remoteDataSource.syncRollerCoasters(onStoreRollerCoasters = any()) }
        coVerify(exactly = 0) { localDataSource.storeRollerCoasters(rollerCoasters = any()) }
    }

    @Test
    fun `search roller coasters returns expected result and stores them`() = runTest {
        val query = SearchQuery(rollerCoaster().name.current.value)
        val measurementSystem = Metric
        val rollerCoasters = listOf(rollerCoaster(), anotherRollerCoaster())

        coEvery {
            remoteDataSource.searchRollerCoasters(query, measurementSystem)
        } returns Ok(rollerCoasters)
        coEvery { localDataSource.storeRollerCoasters(rollerCoasters) } just runs

        val result = repository.searchRollerCoasters(measurementSystem, query)

        assertThat(result).isEqualTo(Ok(rollerCoasters))
        coVerify(exactly = 1) { remoteDataSource.searchRollerCoasters(query, measurementSystem) }
        coVerify(exactly = 1) { localDataSource.storeRollerCoasters(rollerCoasters) }
    }

    @Test
    fun `search roller coasters returns error when remote fails`() = runTest {
        val query = SearchQuery(rollerCoaster(Metric).name.current.value)
        val measurementSystem = Metric

        coEvery {
            remoteDataSource.searchRollerCoasters(query, measurementSystem)
        } returns Err(syncFailedException)

        val result = repository.searchRollerCoasters(measurementSystem, query)

        assertThat(result).isEqualTo(Err(syncFailedException))
        coVerify(exactly = 1) { remoteDataSource.searchRollerCoasters(query, measurementSystem) }
        coVerify(exactly = 0) { localDataSource.storeRollerCoasters(any()) }
    }
}