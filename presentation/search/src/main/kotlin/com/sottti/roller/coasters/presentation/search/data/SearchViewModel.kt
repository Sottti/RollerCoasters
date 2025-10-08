package com.sottti.roller.coasters.presentation.search.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.getOrElse
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.roller.coasters.usecase.SearchRollerCoasters
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchAction.QueryChanged
import com.sottti.roller.coasters.presentation.search.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
internal class SearchViewModel @Inject constructor(
    private val searchRollerCoasters: SearchRollerCoasters,
) : ViewModel() {

    internal val onAction: (SearchAction) -> Unit = ::processAction

    private val searchResults: Flow<List<RollerCoaster>> =
        queryChanges
            .map { newQuery -> newQuery.query.orEmpty().trim() }
            .distinctUntilChanged()
            .debounce(300.milliseconds)
            .flatMapLatest { query ->
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
                }.let { flowOf(it) }
            }.onStart { emit(emptyList()) }

    val state: StateFlow<SearchState> =
        combine(
            flow = queryChanges,
            flow2 = loading,
            flow3 = searchResults,
        ) { queryChanged, loading, searchResults -> reducer(loading, queryChanged, searchResults) }
            .scan(initialState) { previous, reduce -> reduce(previous) }
            .drop(1)
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = initialState,
            )
}

private fun processAction(action: SearchAction) {
    when (action) {
        is QueryChanged -> queryChanges.tryEmit(action)
    }
}

private val loading = MutableStateFlow(false)

private val queryChanges: MutableSharedFlow<QueryChanged> =
    MutableSharedFlow(extraBufferCapacity = 1)

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
