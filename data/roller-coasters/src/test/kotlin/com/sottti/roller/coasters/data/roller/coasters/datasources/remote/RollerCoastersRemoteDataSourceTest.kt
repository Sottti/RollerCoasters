package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api.RollerCoastersApiCalls
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.noInterNetException
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.rollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.rollerCoastersApiModelPage1
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.searchRollercoastersResponseApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs.serverErrorException
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem.Metric
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
    fun `Get roller coaster - API returns success`() = runTest {
        coEvery { api.getRollerCoaster(rollerCoasterId()) } returns Ok(rollerCoasterApiModel)

        val result = dataSource.getRollerCoaster(id = rollerCoasterId(), measurementSystem = Metric)

        assertThat(result.isOk).isTrue()
        assertThat(result.value).isEqualTo(rollerCoaster())

        coVerify(exactly = 1) { api.getRollerCoaster(rollerCoasterId()) }
    }

    @Test
    fun `Get roller coaster - API returns error`() = runTest {
        coEvery { api.getRollerCoaster(rollerCoasterId()) } returns Err(noInterNetException)

        val result = dataSource.getRollerCoaster(id = rollerCoasterId(), measurementSystem = Metric)

        assertThat(result.isErr).isTrue()
        assertThat(result.error.message).isEqualTo(noInterNetException.message)

        coVerify(exactly = 1) { api.getRollerCoaster(rollerCoasterId()) }
    }

    @Test
    fun `Sync roller coasters - API returns error on first call`() = runTest {
        coEvery {
            api.getRollerCoasters(offset = any(), limit = any())
        } returns Err(serverErrorException)

        val storedCoasters = mutableListOf<List<RollerCoaster>>()
        val onStore: suspend (List<RollerCoaster>) -> Unit =
            { rollerCoasters -> storedCoasters.add(rollerCoasters) }

        val result = dataSource.syncRollerCoasters(onStore)

        assertThat(result.error.message).isEqualTo(serverErrorException.message)
        assertThat(storedCoasters.flatten()).isEmpty()

        coVerify(exactly = 1) { api.getRollerCoasters(offset = any(), limit = any()) }
    }

    @Test
    fun `Sync roller coasters - API returns error on some call`() = runTest {
        coEvery {
            api.getRollerCoasters(offset = 0, limit = 250)
        } returns Ok(rollerCoastersApiModelPage1)

        coEvery {
            api.getRollerCoasters(offset = 250, limit = 250)
        } returns Err(serverErrorException)

        val storedCoasters = mutableListOf<List<RollerCoaster>>()
        val onStore: suspend (List<RollerCoaster>) -> Unit =
            { rollerCoasters -> storedCoasters.add(rollerCoasters) }

        val result = dataSource.syncRollerCoasters(onStore)

        assertThat(result.error.message).isEqualTo(serverErrorException.message)
        coVerify(exactly = 2) { api.getRollerCoasters(offset = any(), limit = any()) }
    }


    @Test
    fun `Sync roller coasters - Fetches and stores all pages`() = runTest {
        coEvery {
            api.getRollerCoasters(offset = any(), limit = any())
        } returns Ok(rollerCoastersApiModelPage1)

        val storedCoasters = mutableListOf<List<RollerCoaster>>()
        val onStore: suspend (List<RollerCoaster>) -> Unit =
            { rollerCoasters -> storedCoasters.add(rollerCoasters) }

        val result = dataSource.syncRollerCoasters(onStore)

        assertThat(result).isEqualTo(Ok(Unit))
        assertThat(storedCoasters.flatten().first()).isEqualTo(rollerCoaster())

        coVerify(exactly = 2) { api.getRollerCoasters(offset = any(), limit = any()) }
    }

    @Test
    fun `search roller coasters - API returns success`() = runTest {
        val query = SearchQuery(searchRollercoastersResponseApiModel.rollerCoasters.first().name)
        coEvery { api.searchRollerCoasters(query) } returns Ok(searchRollercoastersResponseApiModel)

        val result = dataSource.searchRollerCoasters(query, Metric)

        assertThat(result.isOk).isTrue()
        assertThat(result.value).hasSize(searchRollercoastersResponseApiModel.rollerCoasters.size)
        assertThat(result.value.first()).isEqualTo(rollerCoaster())

        coVerify(exactly = 1) { api.searchRollerCoasters(query) }
    }

    @Test
    fun `search roller coasters - API returns error`() = runTest {
        val query = SearchQuery(searchRollercoastersResponseApiModel.rollerCoasters.first().name)
        coEvery { api.searchRollerCoasters(query) } returns Err(noInterNetException)

        val result = dataSource.searchRollerCoasters(query, Metric)

        assertThat(result.isErr).isTrue()
        assertThat(result.error.message).isEqualTo(noInterNetException.message)

        coVerify(exactly = 1) { api.searchRollerCoasters(query) }
    }
}