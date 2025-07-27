package com.sottti.roller.coasters.presentation.favourites.data

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveFavouriteRollerCoasters
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class FavouritesViewModelTest {

    private val rollerCoasters = listOf(rollerCoaster(), anotherRollerCoaster())
    private val pagingData = PagingData.from(rollerCoasters)
    private val pagingFlow = flowOf(pagingData)

    @Test
    fun `coasters flow emits expected data`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher(testScheduler))

        try {
            val observeFavouriteRollerCoasters = mockk<ObserveFavouriteRollerCoasters>()
            coEvery { observeFavouriteRollerCoasters() } returns pagingFlow

            val viewModel = FavouritesViewModel(
                observeFavouriteRollerCoasters = observeFavouriteRollerCoasters,
                testScope = this,
            )
            val state = viewModel.state.value
            val snapshot = state.rollerCoasters.asSnapshot()
            assertThat(snapshot).containsExactlyElementsIn(rollerCoasters.map { it.toUiModel() })
        } finally {
            Dispatchers.resetMain()
        }
    }
}
