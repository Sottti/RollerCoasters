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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private typealias Emission = Pair<Boolean, List<RollerCoaster>?>

private fun loading(): Emission = true to null
private fun idle(): Emission = false to null
private fun data(items: List<RollerCoaster>): Emission = false to items

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchRollerCoasters: SearchRollerCoasters,
) : ViewModel() {

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
        const val DEBOUNCE_MS = 300L
    }

    private val _state = MutableStateFlow(initialState)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val searchResultsFlow: Flow<Emission> =
        _state
            .map { it.searchBar.query.orEmpty().trim() }
            .distinctUntilChanged()
            .debounce(DEBOUNCE_MS)
            .flatMapLatest { query ->
                if (query.isBlank()) flowOf(idle())
                else flow {
                    emit(loading())
                    val items = searchRollerCoasters(SearchQuery(query)).getOrElse { emptyList() }
                    emit(data(items))
                }
            }
            .scan(loading()) { prev, next ->
                if (next.first) next.copy(second = prev.second) else next
            }

    val state: StateFlow<SearchState> =
        combine(
            flow = _state,
            flow2 = searchResultsFlow,
        ) { state, (loading, results) ->
            when {
                loading -> {
                    val withLoading = state.loading()
                    if (results != null) withLoading.updateResults(results) else withLoading
                }

                results == null -> state.notLoading().copy(searchResult = searchResultsEmpty())
                else -> state.notLoading().updateResults(results)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIMEOUT_MS),
            initialValue = initialState
        )

    internal val onAction: (SearchAction) -> Unit = ::processAction

    private fun processAction(action: SearchAction) {
        when (action) {
            is QueryChanged -> {
                val raw = action.query.orEmpty()
                _state.updateQuery(raw).updateClearIcon(raw)
            }
        }
    }
}
