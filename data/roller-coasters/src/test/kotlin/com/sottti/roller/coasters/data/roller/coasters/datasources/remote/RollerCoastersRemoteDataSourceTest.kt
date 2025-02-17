package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.network.model.ExceptionApiModel.NoInternet
import com.sottti.roller.coasters.data.network.model.ExceptionApiModel.ServerError
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.RollerCoastersApiCalls
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.rollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.rollerCoastersApiModelPage1
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.rollerCoastersApiModelPage2
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl.Companion.PAGE_SIZE
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasterId
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoasters
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RollerCoastersRemoteDataSourceTest {

    private lateinit var dataSource: RollerCoastersRemoteDataSource
    private val api: RollerCoastersApiCalls = mockk()

    @Before
    fun setup() {
        dataSource = RollerCoastersRemoteDataSource(api)
    }

    @Test
    fun `Get roller coasters page - Returns correct data on success`() = runTest {
        val pageNumber = PageNumber(2)
        val expectedOffset = pageNumber * PAGE_SIZE
        val expectedResult = Ok(rollerCoasters)

        coEvery {
            api.getRollerCoasters(offset = expectedOffset, limit = PAGE_SIZE)
        } returns Ok(rollerCoastersApiModelPage2)

        val result = dataSource.getRollerCoastersPage(pageNumber)

        assertThat(result).isEqualTo(expectedResult)

        coVerify(exactly = 1) { api.getRollerCoasters(offset = expectedOffset, limit = PAGE_SIZE) }
    }

    @Test
    fun `Get roller coasters page - Returns error when API call fails`() = runTest {
        val pageNumber = PageNumber(2)
        val expectedOffset = pageNumber * PAGE_SIZE
        val error = NoInternet

        coEvery {
            api.getRollerCoasters(offset = expectedOffset, limit = PAGE_SIZE)
        } returns Err(error)

        val result = dataSource.getRollerCoastersPage(pageNumber)

        assertThat(result).isEqualTo(Err(error))

        coVerify(exactly = 1) { api.getRollerCoasters(offset = expectedOffset, limit = PAGE_SIZE) }
    }

    @Test
    fun `Get roller coaster - API returns success`() = runTest {
        coEvery { api.getRollerCoaster(rollerCoasterId) } returns Ok(rollerCoasterApiModel)

        val result = dataSource.getRollerCoaster(rollerCoasterId)

        assertThat(result.isOk).isTrue()
        assertThat(result.value).isEqualTo(rollerCoaster)

        coVerify(exactly = 1) { api.getRollerCoaster(rollerCoasterId) }
    }

    @Test
    fun `Get roller coaster - API returns error`() = runTest {
        coEvery { api.getRollerCoaster(rollerCoasterId) } returns Err(NoInternet)

        val result = dataSource.getRollerCoaster(rollerCoasterId)

        assertThat(result.isErr).isTrue()
        assertThat(result.error.message).isEqualTo(NoInternet.message)

        coVerify(exactly = 1) { api.getRollerCoaster(rollerCoasterId) }
    }

    @Test
    fun `Sync roller coasters - API returns error on first call`() = runTest {
        val serverError = ServerError(503)
        coEvery {
            api.getRollerCoasters(offset = 0, limit = PAGE_SIZE)
        } returns Err(serverError)

        val storedCoasters = mutableListOf<List<RollerCoaster>>()
        val onStore: suspend (List<RollerCoaster>) -> Unit = { storedCoasters.add(it) }

        val result = dataSource.syncRollerCoasters(onStore)

        assertThat(result.error.message).isEqualTo(serverError.message)
        assertThat(storedCoasters.flatten()).isEmpty()

        coVerify(exactly = 1) { api.getRollerCoasters(offset = 0, limit = PAGE_SIZE) }
    }

    @Test
    fun `Sync roller coasters - API returns error on second call`() = runTest {
        val serverError = ServerError(503)
        coEvery {
            api.getRollerCoasters(offset = 0, limit = PAGE_SIZE)
        } returns Ok(rollerCoastersApiModelPage1)

        coEvery {
            api.getRollerCoasters(offset = PAGE_SIZE, limit = PAGE_SIZE)
        } returns Err(serverError)

        val storedCoasters = mutableListOf<List<RollerCoaster>>()
        val onStore: suspend (List<RollerCoaster>) -> Unit = { storedCoasters.add(it) }

        val result = dataSource.syncRollerCoasters(onStore)

        assertThat(result.error.message).isEqualTo(serverError.message)
        assertThat(storedCoasters.flatten().size)
            .isEqualTo(rollerCoastersApiModelPage1.rollerCoasters.size)

        coVerify(exactly = 1) { api.getRollerCoasters(offset = 0, limit = PAGE_SIZE) }
        coVerify(exactly = 1) { api.getRollerCoasters(offset = PAGE_SIZE, limit = PAGE_SIZE) }
    }


    @Test
    fun `Sync roller coasters - Fetches and stores all pages`() = runTest {
        coEvery {
            api.getRollerCoasters(offset = 0, limit = PAGE_SIZE)
        } returns Ok(rollerCoastersApiModelPage1)

        coEvery {
            api.getRollerCoasters(offset = PAGE_SIZE, limit = PAGE_SIZE)
        } returns Ok(rollerCoastersApiModelPage2)

        val storedCoasters = mutableListOf<List<RollerCoaster>>()
        val onStore: suspend (List<RollerCoaster>) -> Unit = { storedCoasters.add(it) }

        val result = dataSource.syncRollerCoasters(onStore)

        assertThat(result).isEqualTo(Ok(Unit))
        assertThat(storedCoasters.flatten()).isNotEmpty()
        assertThat(storedCoasters.flatten().first()).isEqualTo(rollerCoaster)

        coVerify(exactly = 1) { api.getRollerCoasters(offset = 0, limit = PAGE_SIZE) }
        coVerify(exactly = 1) { api.getRollerCoasters(offset = PAGE_SIZE, limit = PAGE_SIZE) }
    }
}