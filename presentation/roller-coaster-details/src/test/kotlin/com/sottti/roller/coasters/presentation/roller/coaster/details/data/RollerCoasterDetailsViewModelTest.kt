package com.sottti.roller.coasters.presentation.roller.coaster.details.data

import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ToggleFavouriteRollerCoaster
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class RollerCoasterDetailsViewModelTest {

    @Test
    fun `load ui emits loaded state`() = runTest {
        // WIP
    }

    @Test
    fun `toggle favourite calls use case`() = runTest {
        val rollerCoaster = anotherRollerCoaster()
        val toggleFavourite = mockk<ToggleFavouriteRollerCoaster>()
        coEvery { toggleFavourite(rollerCoaster.id) } returns Unit

        val viewModel = viewModel(
            rollerCoasterId = rollerCoaster.id,
            toggleFavouriteRollerCoaster = toggleFavourite,
        )

        viewModel.onAction(RollerCoasterDetailsAction.ToggleFavourite)

        coVerify(exactly = 1) { toggleFavourite(rollerCoaster.id) }
    }
}