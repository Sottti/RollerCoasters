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
import com.sottti.roller.coasters.presentation.search.model.toState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
internal class SearchViewModel @Inject constructor(
    private val searchRollerCoasters: SearchRollerCoasters,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<SearchState> = _state.asStateFlow()

    internal val onAction: (SearchAction) -> Unit = ::processAction

    private fun processAction(action: SearchAction) {
        when (action) {
            is QueryChanged -> {
                _state.updateQuery(action.query)
                    .updateClearIcon(action.query)
            }
        }
    }

    init {
        viewModelScope.launch {
            state
                .map { it.searchBar.query.orEmpty().trim() }
                .distinctUntilChanged()
                .debounce(300)
                .flatMapLatest { query ->
                    when {
                        query.isBlank() -> flowOf(emptyList())
                        else -> {
                            _state.loading()
                            flow {
                                val searchResults =
                                    searchRollerCoasters(SearchQuery(query)).getOrElse { emptyList() }
                                emit(searchResults)
                            }
                        }
                    }
                }
                .collect { searchResults ->
                    _state
                        .notLoading()
                        .updateResults(searchResults.map(RollerCoaster::toState))
                }
        }
    }
}