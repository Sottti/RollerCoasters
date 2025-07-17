package com.sottti.roller.coasters.presentation.roller.coaster.details.data

// Unit tests for RollerCoasterDetailsViewModel

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.fixtures.OPENED_DATE
import com.sottti.roller.coasters.domain.fixtures.CLOSED_DATE
import com.sottti.roller.coasters.domain.fixtures.DROP
import com.sottti.roller.coasters.domain.fixtures.DURATION_IN_MMSS
import com.sottti.roller.coasters.domain.fixtures.GFORCE
import com.sottti.roller.coasters.domain.fixtures.HEIGHT
import com.sottti.roller.coasters.domain.fixtures.LENGTH
import com.sottti.roller.coasters.domain.fixtures.SPEED
import com.sottti.roller.coasters.domain.fixtures.anotherRollerCoaster
import com.sottti.roller.coasters.domain.fixtures.rollerCoaster
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveIsFavouriteRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ToggleFavouriteRollerCoaster
import com.sottti.roller.coasters.presentation.format.DateFormatter
import com.sottti.roller.coasters.presentation.format.DisplayUnitFormatter
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.initialState
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.updateIsFavouriteRollerCoaster
import com.sottti.roller.coasters.presentation.roller.coaster.details.data.updateRollerCoaster
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsAction
import com.sottti.roller.coasters.presentation.roller.coaster.details.model.RollerCoasterDetailsState
import io.mockk.coVerify
import io.mockk.every
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import java.util.Locale
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class RollerCoasterDetailsViewModelTest {

    @Test
    fun `load ui emits loaded state`() = runTest {
        val appLanguage = AppLanguage.EnglishGb
        val systemLocale = Locale.US
        val rollerCoaster = rollerCoaster()
        val rollerCoasterId = rollerCoaster.id
        val dateFormatter = mockk<DateFormatter>()
        val displayUnitFormatter = mockk<DisplayUnitFormatter>()
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoaster = mockk<ObserveRollerCoaster>()
        val observeIsFavourite = mockk<ObserveIsFavouriteRollerCoaster>()
        val toggleFavouriteRollerCoaster = mockk<ToggleFavouriteRollerCoaster>()

        every { observeAppLanguage() } returns flowOf(appLanguage)
        every { observeSystemLocale() } returns flowOf(systemLocale)
        every { observeRollerCoaster(rollerCoasterId) } returns flowOf(rollerCoaster)
        every { observeIsFavourite(rollerCoasterId) } returns flowOf(true)

        every { displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, rollerCoaster.specs.ride!!.height!!) } returns "$HEIGHT meters"
        every { displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, rollerCoaster.specs.ride!!.length!!) } returns "$LENGTH meters"
        every { displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, rollerCoaster.specs.ride!!.drop!!) } returns "$DROP meters"
        every { displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, rollerCoaster.specs.ride!!.speed!!) } returns "$SPEED km/h"
        every { displayUnitFormatter.toDisplayFormat(appLanguage, systemLocale, rollerCoaster.specs.ride!!.gForce!!) } returns "$GFORCE"
        every { displayUnitFormatter.toDisplayFormat(systemLocale, rollerCoaster.specs.ride!!.duration!!) } returns DURATION_IN_MMSS
        every { displayUnitFormatter.toDisplayFormat(rollerCoaster.specs.ride!!.maxVertical!!) } returns "${rollerCoaster.specs.ride!!.maxVertical!!.degrees.value}°"
        every { dateFormatter.format(rollerCoaster.status.openedDate!!.date) } returns OPENED_DATE
        every { dateFormatter.format(rollerCoaster.status.closedDate?.date ?: error("")) } returns CLOSED_DATE

        val viewModel = viewModel(
            dateFormatter = dateFormatter,
            displayUnitFormatter = displayUnitFormatter,
            observeAppLanguage = observeAppLanguage,
            observeIsFavouriteRollerCoaster = observeIsFavourite,
            observeRollerCoaster = observeRollerCoaster,
            observeSystemLocale = observeSystemLocale,
            rollerCoasterId = rollerCoasterId,
            toggleFavouriteRollerCoaster = toggleFavouriteRollerCoaster,
        )

        val expectedState = MutableStateFlow(initialState()).apply {
            updateRollerCoaster(
                appLanguage = appLanguage,
                dateFormatter = dateFormatter,
                rollerCoaster = rollerCoaster,
                systemLocale = systemLocale,
                displayUnitFormatter = displayUnitFormatter,
            )
            updateIsFavouriteRollerCoaster(true)
        }.value

        viewModel.state.test {
            viewModel.onAction(RollerCoasterDetailsAction.LoadUi)
            // initial state emitted on subscription
            assertThat(awaitItem()).isEqualTo(initialState())
            assertThat(awaitItem()).isEqualTo(expectedState)
            cancelAndIgnoreRemainingEvents()
        }
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
