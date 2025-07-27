package com.sottti.roller.coasters.presentation.explore.data

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveRollerCoasters
import com.sottti.roller.coasters.domain.settings.model.language.AppLanguage.EnglishGb
import com.sottti.roller.coasters.domain.settings.usecase.language.ObserveAppLanguage
import com.sottti.roller.coasters.domain.settings.usecase.locale.ObserveSystemLocale
import com.sottti.roller.coasters.presentation.explore.model.AllFilter
import com.sottti.roller.coasters.presentation.explore.model.AlphabeticalFilter
import com.sottti.roller.coasters.presentation.explore.model.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.SecondaryFilterAction
import com.sottti.roller.coasters.presentation.explore.model.ExploreEvent
import com.sottti.roller.coasters.presentation.explore.model.GForceFilter
import com.sottti.roller.coasters.presentation.explore.model.HeightFilter
import com.sottti.roller.coasters.presentation.explore.model.InversionsFilter
import com.sottti.roller.coasters.presentation.explore.model.LengthFilter
import com.sottti.roller.coasters.presentation.explore.model.MaxVerticalFilter
import com.sottti.roller.coasters.presentation.explore.model.SpeedFilter
import com.sottti.roller.coasters.presentation.explore.model.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.WoodFilter
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
internal class ExploreViewModelTest {

    @Test
    fun `emits initial state on creation`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        assertThat(viewModel.state.value.filters).isEqualTo(filtersInitialState())
    }

    @Test
    fun `changing type filter emits a new value`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.Steel,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.state.test {
            val initial = awaitItem()
            assertThat(initial.allFilter.selected).isTrue()
            assertThat(initial.steelFilter.selected).isFalse()

            viewModel.onAction(SecondaryFilterAction.SelectTypeSteel)

            val next = awaitItem()
            assertThat(next.steelFilter.selected).isTrue()
            assertThat(next.allFilter.selected).isFalse()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `changing sort by filter emits a new value`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Drop,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.state.test {
            val initial = awaitItem()
            assertThat(initial.alphabeticalFilter.selected).isTrue()
            assertThat(initial.dropFilter.selected).isFalse()

            viewModel.onAction(SecondaryFilterAction.SelectSortByDrop)

            val next = awaitItem()
            assertThat(next.dropFilter.selected).isTrue()
            assertThat(next.alphabeticalFilter.selected).isFalse()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emits scroll to top event when secondary filter changes`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.Steel,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.events.test {
            viewModel.onAction(SecondaryFilterAction.SelectTypeSteel)
            assertThat(awaitItem()).isEqualTo(ExploreEvent.ScrollToTop)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `show sort filters expands sort and collapses type`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )
        assertThat(viewModel.state.value.filters).isEqualTo(filtersInitialState())
        viewModel.onAction(PrimaryFilterAction.ShowSortFilters)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { expandSortByPrimaryFilter() }.value.filters
        assertThat(viewModel.state.value.filters).isEqualTo(expected)
    }

    @Test
    fun `hide sort filters collapses sort and does not expand type`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )
        viewModel.onAction(PrimaryFilterAction.ShowSortFilters)

        viewModel.onAction(PrimaryFilterAction.HideSortFilters)

        val expected =
            MutableStateFlow(viewModel.state.value).apply { collapseSortByPrimaryFilter() }.value.filters

        assertThat(viewModel.state.value.filters).isEqualTo(expected)
    }

    @Test
    fun `show type filters expands type and collapses sort`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(PrimaryFilterAction.ShowTypeFilters)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { expandTypePrimaryFilter() }.value.filters
        assertThat(viewModel.state.value.filters).isEqualTo(expected)
    }

    @Test
    fun `hide type filters collapses type and does not expand sort`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )
        viewModel.onAction(PrimaryFilterAction.ShowTypeFilters)
        viewModel.onAction(PrimaryFilterAction.HideTypeFilters)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { collapseTypePrimaryFilter() }.value.filters
        assertThat(viewModel.state.value.filters).isEqualTo(expected)
    }

    @Test
    fun `process select alphabetical sort filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectSortByAlphabetical)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<AlphabeticalFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select drop sort filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Drop,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectSortByDrop)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<DropFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select steel type filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.Steel,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectTypeSteel)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<SteelFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select all type filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectTypeAll)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<AllFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select gforce sort filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.GForce,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectSortByGForce)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<GForceFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select height sort filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Height,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectSortByHeight)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<HeightFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select inversions sort filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Inversions,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectSortByInversions)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<InversionsFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select length sort filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Length,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectSortByLength)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<LengthFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select max vertical sort filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.MaxVertical,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectSortByMaxVertical)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<MaxVerticalFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select speed sort filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Speed,
                typeFilter = TypeFilter.All,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectSortBySpeed)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<SpeedFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }

    @Test
    fun `process select wood type filter action updates state correctly`() = runTest {
        val observeAppLanguage = mockk<ObserveAppLanguage>()
        val observeSystemLocale = mockk<ObserveSystemLocale>()
        val observeRollerCoasters = mockk<ObserveRollerCoasters>()

        every { observeAppLanguage() } returns flowOf(EnglishGb)
        every { observeSystemLocale() } returns flowOf(Locale.US)
        coEvery {
            observeRollerCoasters.invoke(
                sortByFilter = SortByFilter.Alphabetical,
                typeFilter = TypeFilter.Wood,
            )
        } returns flowOf(PagingData.empty())

        val viewModel = viewModel(
            observeAppLanguage = observeAppLanguage,
            observeRollerCoasters = observeRollerCoasters,
            observeSystemLocale = observeSystemLocale,
        )

        viewModel.onAction(SecondaryFilterAction.SelectTypeWood)
        val expected = MutableStateFlow(viewModel.state.value)
            .apply { select<WoodFilter>() }.value
        assertThat(viewModel.state.value).isEqualTo(expected)
    }
}
