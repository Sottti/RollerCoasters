import com.sottti.roller.coasters.presentation.explore.data.filtersInitialState
import com.sottti.roller.coasters.presentation.explore.model.AllFilter
import com.sottti.roller.coasters.presentation.explore.model.AlphabeticalFilter
import com.sottti.roller.coasters.presentation.explore.model.DropFilter
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.HideTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowSortFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreAction.PrimaryFilterAction.ShowTypeFilters
import com.sottti.roller.coasters.presentation.explore.model.ExploreState
import com.sottti.roller.coasters.presentation.explore.model.SortByPrimaryFilter
import com.sottti.roller.coasters.presentation.explore.model.SteelFilter
import com.sottti.roller.coasters.presentation.explore.model.TypePrimaryFilter

internal fun filtersWithExpansion(
    sortByExpanded: Boolean = false,
    typeExpanded: Boolean = false,
    sortBySecondaryVisible: Boolean = false,
    typeSecondaryVisible: Boolean = false,
) = filtersInitialState().copy(
    primary = filtersInitialState().primary.map {
        when (it) {
            is SortByPrimaryFilter -> it.copy(
                expanded = sortByExpanded,
                action = if (sortByExpanded) HideSortFilters else ShowSortFilters,
            )

            is TypePrimaryFilter -> it.copy(
                expanded = typeExpanded,
                action = if (typeExpanded) HideTypeFilters else ShowTypeFilters,
            )
        }
    },
    secondary = filtersInitialState().secondary.map {
        when (it) {
            is AlphabeticalFilter -> it.copy(visible = sortBySecondaryVisible)
            is DropFilter -> it.copy(visible = sortBySecondaryVisible)
            is AllFilter -> it.copy(visible = typeSecondaryVisible)
            is SteelFilter -> it.copy(visible = typeSecondaryVisible)
            else -> it
        }
    }
)

internal val ExploreState.sortByPrimary: SortByPrimaryFilter
    get() = filters.primary.filterIsInstance<SortByPrimaryFilter>().first()

internal val ExploreState.typePrimary: TypePrimaryFilter
    get() = filters.primary.filterIsInstance<TypePrimaryFilter>().first()

internal val ExploreState.dropFilter: DropFilter
    get() = filters.secondary.filterIsInstance<DropFilter>().first()

internal val ExploreState.alphaFilter: AlphabeticalFilter
    get() = filters.secondary.filterIsInstance<AlphabeticalFilter>().first()

internal val ExploreState.allFilter: AllFilter
    get() = filters.secondary.filterIsInstance<AllFilter>().first()

internal val ExploreState.steelFilter: SteelFilter
    get() = filters.secondary.filterIsInstance<SteelFilter>().first()