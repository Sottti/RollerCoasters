package com.sottti.roller.coasters.presentation.search.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.getOrElse
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.roller.coasters.usecase.SearchRollerCoasters
import com.sottti.roller.coasters.presentation.search.R
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchAction.ClearQuery
import com.sottti.roller.coasters.presentation.search.model.SearchAction.QueryChanged
import com.sottti.roller.coasters.presentation.search.model.SearchBarViewState
import com.sottti.roller.coasters.presentation.search.model.SearchViewState
import com.sottti.roller.coasters.presentation.search.model.toViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
internal class SearchViewModel @Inject constructor(
    private val searchRollerCoasters: SearchRollerCoasters,
) : ViewModel() {
    private val initialViewState: SearchViewState =
        SearchViewState(
            SearchBarViewState(
                hint = R.string.search_hint,
                query = null,
                showClearIcon = false,
            )
        )
    private val _state = MutableStateFlow(initialViewState)
    val state: StateFlow<SearchViewState> = _state.asStateFlow()

    internal val onAction: (SearchAction) -> Unit = { action -> processAction(action) }

    private fun processAction(action: SearchAction) {
        when (action) {
            is QueryChanged -> _state.updateQuery(action.query).updateClearIcon(action.query)
            ClearQuery -> _state.clearQuery()
        }
    }

    init {
        viewModelScope.launch {
            state
                .map { it.searchBar.query.orEmpty().trim() }
                .distinctUntilChanged()
                .debounce(300)
                .flatMapLatest { query ->
                    if (query.isBlank()) {
                        flowOf(emptyList())
                    } else {
                        _state.loading()
                        flow {
                            emit(searchRollerCoasters(SearchQuery(query)).getOrElse { emptyList() })
                        }
                    }
                }
                .collect { results ->
                    _state.notLoading().updateResults(results.map { it.toViewState() })
                }
        }
    }
}