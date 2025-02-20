package com.sottti.roller.coasters.data.roller.coasters.datasources.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.RollerCoastersLocalDataSource
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.PAGE_NUMBER_INITIAL
import com.sottti.roller.coasters.data.roller.coasters.datasources.local.stubs.PAGE_NUMBER_SECOND
import com.sottti.roller.coasters.data.roller.coasters.datasources.paging.RollerCoastersRemoteMediator
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.RollerCoastersRemoteDataSource
import com.sottti.roller.coasters.data.roller.coasters.repository.RollerCoastersRepositoryImpl.Companion.PAGE_SIZE
import com.sottti.roller.coasters.data.roller.coasters.stubs.rollerCoaster
import com.sottti.roller.coasters.domain.model.PageNumber
import com.sottti.roller.coasters.domain.model.RollerCoaster
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
internal class RollerCoastersRemoteMediatorTest {

    private lateinit var localDataSource: RollerCoastersLocalDataSource
    private lateinit var remoteDataSource: RollerCoastersRemoteDataSource
    private lateinit var remoteMediator: RollerCoastersRemoteMediator

    @Before
    fun setup() {
        localDataSource = mockk()
        remoteDataSource = mockk()
        remoteMediator = RollerCoastersRemoteMediator(localDataSource, remoteDataSource)
    }

    @Test
    fun `refresh load fetches data from API and stores it in DB`() = runTest {
        val page = PageNumber(PAGE_NUMBER_INITIAL)
        coEvery { remoteDataSource.getRollerCoastersPage(page) } returns Ok(listOf(rollerCoaster))
        coEvery {
            localDataSource.storeRollerCoasters(
                page = page,
                rollerCoasters = listOf(rollerCoaster),
            )
        } just runs

        val pagingState = PagingState<PageNumber, RollerCoaster>(
            pages = emptyList(),
            anchorPosition = null,
            config = PagingConfig(pageSize = PAGE_SIZE),
            leadingPlaceholderCount = 0,
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        coVerify(exactly = 1) { remoteDataSource.getRollerCoastersPage(page) }
        coVerify(exactly = 1) {
            localDataSource.storeRollerCoasters(
                page = page,
                rollerCoasters = listOf(rollerCoaster),
            )
        }
    }

    @Test
    fun `append load fetches next page correctly`() = runTest {
        val page = PageNumber(PAGE_NUMBER_SECOND)
        coEvery { remoteDataSource.getRollerCoastersPage(page) } returns Ok(listOf(rollerCoaster))
        coEvery {
            localDataSource.storeRollerCoasters(
                page = page,
                rollerCoasters = listOf(rollerCoaster),
            )
        } just runs

        val pagingState = PagingState<PageNumber, RollerCoaster>(
            anchorPosition = null,
            config = PagingConfig(pageSize = PAGE_SIZE),
            leadingPlaceholderCount = 0,
            pages = listOf(PagingSource.LoadResult.Page(emptyList(), null, page)),
        )

        val result = remoteMediator.load(LoadType.APPEND, pagingState)

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        coVerify(exactly = 1) { remoteDataSource.getRollerCoastersPage(page) }
        coVerify(exactly = 1) {
            localDataSource.storeRollerCoasters(
                page = page,
                rollerCoasters = listOf(rollerCoaster),
            )
        }
    }

    @Test
    fun `handles end of pagination when API returns fewer elements`() = runTest {
        val page = PageNumber(PAGE_NUMBER_SECOND)
        val fewerElementsStub = listOf(rollerCoaster).take(1)
        coEvery { remoteDataSource.getRollerCoastersPage(page) } returns Ok(fewerElementsStub)
        coEvery {
            localDataSource.storeRollerCoasters(
                page = page,
                rollerCoasters = fewerElementsStub,
            )
        } just runs

        val pagingState = PagingState<PageNumber, RollerCoaster>(
            anchorPosition = null,
            config = PagingConfig(pageSize = PAGE_SIZE),
            leadingPlaceholderCount = 0,
            pages = listOf(PagingSource.LoadResult.Page(emptyList(), null, page)),
        )

        val result = remoteMediator.load(LoadType.APPEND, pagingState)

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        assertThat(
            (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached
        ).isTrue()
    }

    @Test
    fun `returns error when API fails`() = runTest {
        val page = PageNumber(PAGE_NUMBER_INITIAL)
        val exception = Exception("API error")
        coEvery { remoteDataSource.getRollerCoastersPage(page) } returns Err(exception)

        val pagingState = PagingState<PageNumber, RollerCoaster>(
            anchorPosition = null,
            config = PagingConfig(pageSize = 20),
            leadingPlaceholderCount = 0,
            pages = emptyList(),
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Error::class.java)
        assertThat((result as RemoteMediator.MediatorResult.Error).throwable)
            .hasMessageThat()
            .contains("API error")
    }

    @Test
    fun `returns success without fetching when no new page needs to be loaded`() = runTest {
        val pagingState = PagingState<PageNumber, RollerCoaster>(
            anchorPosition = null,
            config = PagingConfig(pageSize = 20),
            leadingPlaceholderCount = 0,
            pages = listOf(PagingSource.LoadResult.Page(emptyList(), null, null)),
        )

        val result = remoteMediator.load(LoadType.APPEND, pagingState)

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isTrue()
    }
}