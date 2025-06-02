package com.sottti.roller.coasters.presentation.explore.data

import allFilter
import alphaFilter
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.presentation.explore.model.AllFilter
import com.sottti.roller.coasters.presentation.explore.model.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.SortBySecondaryFilter
import com.sottti.roller.coasters.presentation.explore.model.TypeSecondaryFilter
import dropFilter
import filtersWithExpansion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Before
import org.junit.Test
import sortByPrimary
import steelFilter
import typePrimary

internal class ExploreReducersTest {

    private lateinit var stateFlow: MutableStateFlow<ExploreState>

    @Before
    fun setUp() {
        stateFlow = MutableStateFlow(initialState(emptyFlow()))
    }

    @Test
    fun `test expanding sort by primary filter shows only sort by secondary filters visible`() {
        stateFlow.expandSortByPrimaryFilter()
        assertThat(stateFlow.value.sortByPrimary.expanded).isTrue()
        assertThat(stateFlow.value.sortByPrimary.action).isEqualTo(HideSortFilters)
        assertThat(stateFlow.value.typePrimary.expanded).isFalse()
        stateFlow.value.filters.secondary.forEach { filter ->
            when (filter) {
                is SortBySecondaryFilter -> assertThat(filter.visible).isTrue()
                else -> assertThat(filter.visible).isFalse()
            }
        }
    }

    @Test
    fun `test expanding type primary filter shows only type secondary filters visible`() {
        stateFlow.expandTypePrimaryFilter()
        assertThat(stateFlow.value.typePrimary.expanded).isTrue()
        assertThat(stateFlow.value.typePrimary.action).isEqualTo(HideTypeFilters)
        assertThat(stateFlow.value.sortByPrimary.expanded).isFalse()
        stateFlow.value.filters.secondary.forEach { filter ->
            when (filter) {
                is TypeSecondaryFilter -> assertThat(filter.visible).isTrue()
                else -> assertThat(filter.visible).isFalse()
            }
        }
    }

    @Test
    fun `test collapsing sort by primary filter hides all secondary filters`() {
        stateFlow = MutableStateFlow(
            initialState(emptyFlow()).copy(
                filters = filtersWithExpansion(
                    sortByExpanded = true,
                    sortBySecondaryVisible = true
                )
            )
        )
        stateFlow.collapseSortByPrimaryFilter()
        assertThat(stateFlow.value.sortByPrimary.expanded).isFalse()
        assertThat(stateFlow.value.sortByPrimary.action).isEqualTo(ShowSortFilters)
        stateFlow.value.filters.secondary.forEach { filter ->
            assertThat(filter.visible).isFalse()
        }
    }

    @Test
    fun `test collapsing type primary filter hides all secondary filters`() {

        stateFlow = MutableStateFlow(
            initialState(emptyFlow()).copy(
                filters = filtersWithExpansion(
                    typeExpanded = true,
                    typeSecondaryVisible = true
                )
            )
        )
        stateFlow.collapseTypePrimaryFilter()
        assertThat(stateFlow.value.typePrimary.expanded).isFalse()
        assertThat(stateFlow.value.typePrimary.action).isEqualTo(ShowTypeFilters)
        stateFlow.value.filters.secondary.forEach { filter ->
            assertThat(filter.visible).isFalse()
        }
    }

    @Test
    fun `test selecting sort by secondary filter selects and collapses primary and hides secondary`() {

        stateFlow = MutableStateFlow(
            initialState(emptyFlow()).copy(
                filters = filtersWithExpansion(
                    sortByExpanded = true,
                    sortBySecondaryVisible = true
                )
            )
        )
        stateFlow.select<DropFilter>()
        assertThat(stateFlow.value.dropFilter.selected).isTrue()
        assertThat(stateFlow.value.dropFilter.leadingIcon).isNotNull()
        assertThat(stateFlow.value.dropFilter.visible).isFalse()
        assertThat(stateFlow.value.alphaFilter.selected).isFalse()
        assertThat(stateFlow.value.sortByPrimary.expanded).isFalse()
        assertThat(stateFlow.value.sortByPrimary.action).isEqualTo(ShowSortFilters)
        stateFlow.value.filters.secondary.forEach { filter ->
            assertThat(filter.visible).isFalse()
        }
    }

    @Test
    fun `test selecting type secondary filter selects and collapses primary and hides secondary`() {

        stateFlow = MutableStateFlow(
            initialState(emptyFlow()).copy(
                filters = filtersWithExpansion(
                    typeExpanded = true,
                    typeSecondaryVisible = true
                )
            )
        )
        stateFlow.select<AllFilter>()
        assertThat(stateFlow.value.allFilter.selected).isTrue()
        assertThat(stateFlow.value.allFilter.leadingIcon).isNotNull()
        assertThat(stateFlow.value.allFilter.visible).isFalse()
        assertThat(stateFlow.value.steelFilter.selected).isFalse()
        assertThat(stateFlow.value.typePrimary.expanded).isFalse()
        assertThat(stateFlow.value.typePrimary.action).isEqualTo(ShowTypeFilters)
        stateFlow.value.filters.secondary.forEach { filter ->
            assertThat(filter.visible).isFalse()
        }
    }
}