package com.sottti.roller.coasters.presentation.search.data

import androidx.lifecycle.ViewModel
import com.github.michaelbull.result.getOrElse
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.roller.coasters.usecase.SearchRollerCoasters
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchAction.QueryChanged
import com.sottti.roller.coasters.presentation.search.model.SearchState
import com.sottti.roller.coasters.presentation.utils.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
internal class SearchViewModel @Inject constructor(
    private val searchRollerCoasters: SearchRollerCoasters,
) : ViewModel() {

    private val loading = MutableStateFlow(false)

    private val queryChanges: MutableSharedFlow<QueryChanged> =
        MutableSharedFlow(extraBufferCapacity = 64)

    private val searchResults: Flow<List<RollerCoaster>> =
        queryChanges
            .map { newQuery -> newQuery.query.orEmpty().trim() }
            .distinctUntilChanged()
            .debounce(300.milliseconds)
            .mapLatest { query -> search(query) }
            .onStart { emit(emptyList()) }

    val state: StateFlow<SearchState> =
        combine(
            flow = queryChanges,
            flow2 = loading,
            flow3 = searchResults,
        ) { queryChanged, loading, searchResults -> reducer(loading, queryChanged, searchResults) }
            .scan(initialState) { previous, reduce -> reduce(previous) }
            .drop(1)
            .stateInWhileSubscribed(initialState)

    private suspend fun search(
        query: String,
    ): List<RollerCoaster> =
        when {
            query.isBlank() -> emptyList()
            else -> {
                loading.tryEmit(true)
                val searchResult =
                    searchRollerCoasters(SearchQuery(query))
                        .getOrElse { emptyList() }
                loading.tryEmit(false)
                searchResult
            }
        }

    private val reducer: (
        loading: Boolean,
        queryChanged: QueryChanged,
        searchResults: List<RollerCoaster>,
    ) -> (SearchState) -> SearchState =
        { loading, queryChanged, searchResults ->
            { previous: SearchState ->
                previous
                    .updateLoading(loading)
                    .updateQuery(queryChanged.query)
                    .updateResults(searchResults)
            }
        }

    internal val onAction: (SearchAction) -> Unit = ::processAction
    private fun processAction(action: SearchAction) {
        when (action) {
            is QueryChanged -> queryChanges.tryEmit(action)
        }
    }
}
