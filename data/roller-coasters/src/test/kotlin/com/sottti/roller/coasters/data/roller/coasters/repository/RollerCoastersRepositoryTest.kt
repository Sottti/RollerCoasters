package com.sottti.roller.coasters.data.roller.coasters.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.rollerCoasterId
import com.sottti.roller.coasters.domain.model.RollerCoaster
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class RollerCoastersRepositoryTest {

    internal val notFoundException: Exception = Exception("Not found")
    internal val syncFailedException = Exception("Sync failed")

    private lateinit var repository: RollerCoastersRepositoryImpl

    private val localDataSource: RollerCoastersLocalDataSource = mockk()
    private val remoteDataSource: RollerCoastersRemoteDataSource = mockk()

    @Before
    fun setup() {
        repository = RollerCoastersRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `Get roller coaster by id - Exists locally`() = runTest {
        val localRollerCoaster = mockk<RollerCoaster>()
        coEvery {
            localDataSource.getRollerCoaster(rollerCoasterId)
        } returns Ok(localRollerCoaster)

        val result = repository.getRollerCoaster(rollerCoasterId).value

        TestCase.assertEquals(localRollerCoaster, result)

        coVerify(exactly = 1) { localDataSource.getRollerCoaster(rollerCoasterId) }
        coVerify(exactly = 0) { remoteDataSource.getRollerCoaster(rollerCoasterId) }
    }

    @Test
    fun `Get roller coaster by id - Not found locally, exists remotely`() = runTest {
        val remoteRollerCoaster = mockk<RollerCoaster>()
        coEvery {
            localDataSource.getRollerCoaster(rollerCoasterId)
        } returns Err(notFoundException)

        coEvery {
            remoteDataSource.getRollerCoaster(rollerCoasterId)
        } returns Ok(remoteRollerCoaster)

        coEvery {
            localDataSource.storeRollerCoaster(remoteRollerCoaster)
        } just runs

        val result = repository.getRollerCoaster(rollerCoasterId).value

        TestCase.assertEquals(remoteRollerCoaster, result)
        coVerify(exactly = 1) { localDataSource.getRollerCoaster(rollerCoasterId) }
        coVerify(exactly = 1) { remoteDataSource.getRollerCoaster(rollerCoasterId) }
        coVerify(exactly = 1) { localDataSource.storeRollerCoaster(remoteRollerCoaster) }
    }

    @Test
    fun `Get roller coaster by id - Not found locally or remotely`() = runTest {
        val networkError = Exception("Network error")
        coEvery { localDataSource.getRollerCoaster(rollerCoasterId) } returns Err(notFoundException)
        coEvery { remoteDataSource.getRollerCoaster(rollerCoasterId) } returns Err(networkError)

        val result = repository.getRollerCoaster(rollerCoasterId)

        TestCase.assertEquals(Err(networkError), result)
        coVerify(exactly = 1) { localDataSource.getRollerCoaster(rollerCoasterId) }
        coVerify(exactly = 1) { remoteDataSource.getRollerCoaster(rollerCoasterId) }
        coVerify(exactly = 0) { localDataSource.storeRollerCoaster(any()) }
    }

    @Test
    fun `Sync roller coasters - Calls remote and stores data`() = runTest {
        val rollerCoasters = listOf(mockk<RollerCoaster>())

        coEvery {
            remoteDataSource.syncRollerCoasters(any())
        } coAnswers {
            val lambda = firstArg<suspend (List<RollerCoaster>) -> Unit>()
            lambda(rollerCoasters)
            Ok(Unit)
        }

        coEvery { localDataSource.storeRollerCoasters(rollerCoasters) } just runs

        val result = repository.syncAllRollerCoasters()

        TestCase.assertEquals(Ok(Unit), result)
        coVerify(exactly = 1) { remoteDataSource.syncRollerCoasters(any()) }
        coVerify(exactly = 1) { localDataSource.storeRollerCoasters(rollerCoasters) }
    }

    @Test
    fun `Sync roller coasters - Fails when remote fails`() = runTest {
        coEvery { remoteDataSource.syncRollerCoasters(any()) } returns Err(syncFailedException)

        val result = repository.syncAllRollerCoasters()

        TestCase.assertEquals(Err(syncFailedException), result)
        coVerify(exactly = 1) { remoteDataSource.syncRollerCoasters(any()) }
        coVerify(exactly = 0) { localDataSource.storeRollerCoasters(any()) }
    }
}