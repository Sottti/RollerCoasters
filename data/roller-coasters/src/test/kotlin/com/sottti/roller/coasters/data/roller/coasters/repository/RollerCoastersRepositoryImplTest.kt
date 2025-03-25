package com.sottti.roller.coasters.data.roller.coasters.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.stubs.anotherRollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.networkErrorException
import com.sottti.roller.coasters.data.roller.coasters.stubs.notFoundException
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterId
import com.sottti.roller.coasters.data.roller.coasters.stubs.syncFailedException
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem.Metric
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class RollerCoastersRepositoryImplTest {
    private lateinit var repository: RollerCoastersRepositoryImpl
    private val localDataSource: RollerCoastersLocalDataSource = mockk()
    private val remoteDataSource: RollerCoastersRemoteDataSource = mockk()

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        repository = RollerCoastersRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            rollerCoasterSyncScheduler = mockk(),
        )
    }

    @After
    @OptIn(ExperimentalCoroutinesApi::class)
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Get roller coaster by id - Exists locally`() = runTest {
        val localRollerCoaster = mockk<RollerCoaster>()
        coEvery {
            localDataSource.getRollerCoaster(rollerCoasterId, Metric)
        } returns Ok(localRollerCoaster)

        val result = repository.getRollerCoaster(rollerCoasterId, Metric).value

        assertThat(result).isEqualTo(localRollerCoaster)
        coVerify(exactly = 1) { localDataSource.getRollerCoaster(rollerCoasterId, Metric) }
        coVerify(exactly = 0) {
            remoteDataSource.getRollerCoaster(
                id = rollerCoasterId,
                measurementSystem = Metric,
            )
        }
    }

    @Test
    fun `Get roller coaster by id - Not found locally, exists remotely`() = runTest {
        val remoteRollerCoaster = mockk<RollerCoaster>()
        coEvery {
            localDataSource.getRollerCoaster(rollerCoasterId, Metric)
        } returns Err(notFoundException)
        coEvery {
            remoteDataSource.getRollerCoaster(
                id = rollerCoasterId,
                measurementSystem = Metric,
            )
        } returns Ok(
            remoteRollerCoaster
        )
        coEvery { localDataSource.storeRollerCoaster(remoteRollerCoaster) } just runs

        val result = repository.getRollerCoaster(rollerCoasterId, Metric).value

        assertThat(result).isEqualTo(remoteRollerCoaster)
        coVerify(exactly = 1) { localDataSource.getRollerCoaster(rollerCoasterId, Metric) }
        coVerify(exactly = 1) {
            remoteDataSource.getRollerCoaster(
                id = rollerCoasterId,
                measurementSystem = Metric,
            )
        }
        coVerify(exactly = 1) { localDataSource.storeRollerCoaster(remoteRollerCoaster) }
    }

    @Test
    fun `Get roller coaster by id - Not found locally or remotely`() = runTest {
        coEvery {
            localDataSource.getRollerCoaster(id = rollerCoasterId, systemMeasurementSystem = Metric)
        } returns Err(notFoundException)
        coEvery {
            remoteDataSource.getRollerCoaster(id = rollerCoasterId, measurementSystem = Metric)
        } returns Err(networkErrorException)

        val result = repository.getRollerCoaster(id = rollerCoasterId, measurementSystem = Metric)

        assertThat(result).isEqualTo(Err(networkErrorException))
        coVerify(exactly = 1) {
            localDataSource.getRollerCoaster(
                id = rollerCoasterId,
                systemMeasurementSystem = Metric,
            )
        }
        coVerify(exactly = 1) {
            remoteDataSource.getRollerCoaster(
                id = rollerCoasterId,
                measurementSystem = Metric,
            )
        }
        coVerify(exactly = 0) { localDataSource.storeRollerCoaster(rollerCoaster = any()) }
    }

    @Test
    fun `Sync roller coasters - Calls remote and stores data`() = runTest {
        val rollerCoasters = listOf(rollerCoaster, anotherRollerCoaster)

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
    fun `Sync roller coasters - Fails when remote fails`() = runTest {
        coEvery {
            remoteDataSource.syncRollerCoasters(onStoreRollerCoasters = any())
        } returns Err(syncFailedException)

        val result = repository.syncAllRollerCoasters()

        assertThat(result).isEqualTo(Err(syncFailedException))
        coVerify(exactly = 1) { remoteDataSource.syncRollerCoasters(onStoreRollerCoasters = any()) }
        coVerify(exactly = 0) { localDataSource.storeRollerCoasters(rollerCoasters = any()) }
    }
}