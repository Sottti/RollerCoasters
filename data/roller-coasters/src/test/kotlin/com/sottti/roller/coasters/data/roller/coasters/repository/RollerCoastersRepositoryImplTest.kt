package com.sottti.roller.coasters.data.roller.coasters.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.pageNumber
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.stubs.networkErrorException
import com.sottti.roller.coasters.data.roller.coasters.stubs.notFoundException
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterId
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasters
import com.sottti.roller.coasters.data.roller.coasters.stubs.syncFailedException
import com.sottti.roller.coasters.domain.model.NotFound
import com.sottti.roller.coasters.domain.model.RollerCoaster
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class RollerCoastersRepositoryImplTest {
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
        coEvery { localDataSource.getRollerCoaster(rollerCoasterId) } returns Ok(localRollerCoaster)

        val result = repository.getRollerCoaster(rollerCoasterId).value

        assertThat(result).isEqualTo(localRollerCoaster)
        coVerify(exactly = 1) { localDataSource.getRollerCoaster(rollerCoasterId) }
        coVerify(exactly = 0) { remoteDataSource.getRollerCoaster(rollerCoasterId) }
    }

    @Test
    fun `Get roller coaster by id - Not found locally, exists remotely`() = runTest {
        val remoteRollerCoaster = mockk<RollerCoaster>()
        coEvery { localDataSource.getRollerCoaster(rollerCoasterId) } returns Err(notFoundException)
        coEvery { remoteDataSource.getRollerCoaster(rollerCoasterId) } returns Ok(
            remoteRollerCoaster
        )
        coEvery { localDataSource.storeRollerCoaster(remoteRollerCoaster) } just runs

        val result = repository.getRollerCoaster(rollerCoasterId).value

        assertThat(result).isEqualTo(remoteRollerCoaster)
        coVerify(exactly = 1) { localDataSource.getRollerCoaster(rollerCoasterId) }
        coVerify(exactly = 1) { remoteDataSource.getRollerCoaster(rollerCoasterId) }
        coVerify(exactly = 1) { localDataSource.storeRollerCoaster(remoteRollerCoaster) }
    }

    @Test
    fun `Get roller coaster by id - Not found locally or remotely`() = runTest {
        coEvery { localDataSource.getRollerCoaster(rollerCoasterId) } returns Err(notFoundException)
        coEvery {
            remoteDataSource.getRollerCoaster(rollerCoasterId)
        } returns Err(networkErrorException)

        val result = repository.getRollerCoaster(rollerCoasterId)

        assertThat(result).isEqualTo(Err(networkErrorException))
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

        assertThat(result).isEqualTo(Ok(Unit))
        coVerify(exactly = 1) { remoteDataSource.syncRollerCoasters(any()) }
        coVerify(exactly = 1) { localDataSource.storeRollerCoasters(rollerCoasters) }
    }

    @Test
    fun `Sync roller coasters - Fails when remote fails`() = runTest {
        coEvery { remoteDataSource.syncRollerCoasters(any()) } returns Err(syncFailedException)

        val result = repository.syncAllRollerCoasters()

        assertThat(result).isEqualTo(Err(syncFailedException))
        coVerify(exactly = 1) { remoteDataSource.syncRollerCoasters(any()) }
        coVerify(exactly = 0) { localDataSource.storeRollerCoasters(any()) }
    }






    @Test
    fun `Get roller coasters - returns local data when available`() = runTest {
        coEvery { localDataSource.getRollerCoasters(pageNumber) } returns Ok(rollerCoasters)

        val result = repository.getRollerCoasters(pageNumber)

        assertThat(result).isEqualTo(Ok(rollerCoasters))

        coVerify(exactly = 1) { localDataSource.getRollerCoasters(pageNumber) }
        coVerify(exactly = 0) { remoteDataSource.getRollerCoastersPage(any()) }
    }

    @Test
    fun `Get roller coasters - fetches from remote when local data is missing`() = runTest {
        coEvery { localDataSource.getRollerCoasters(pageNumber) } returns Err(NotFound)
        coEvery { remoteDataSource.getRollerCoastersPage(pageNumber) } returns Ok(rollerCoasters)
        coEvery { localDataSource.storeRollerCoasters(rollerCoasters, pageNumber) } just runs

        val result = repository.getRollerCoasters(pageNumber)

        assertThat(result).isEqualTo(Ok(rollerCoasters))

        coVerify(exactly = 1) { localDataSource.getRollerCoasters(pageNumber) }
        coVerify(exactly = 1) { remoteDataSource.getRollerCoastersPage(pageNumber) }
        coVerify(exactly = 1) { localDataSource.storeRollerCoasters(rollerCoasters, pageNumber) }
    }

    @Test
    fun `Get roller coasters - returns error when both local and remote fail`() = runTest {
        coEvery { localDataSource.getRollerCoasters(pageNumber) } returns Err(NotFound)
        coEvery { remoteDataSource.getRollerCoastersPage(pageNumber) } returns Err(NotFound)

        val result = repository.getRollerCoasters(pageNumber)

        assertThat(result).isEqualTo(Err(NotFound))

        coVerify(exactly = 1) { localDataSource.getRollerCoasters(pageNumber) }
        coVerify(exactly = 1) { remoteDataSource.getRollerCoastersPage(pageNumber) }
        coVerify(exactly = 0) { localDataSource.storeRollerCoasters(any(), any()) }
    }
}