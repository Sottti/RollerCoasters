package com.sottti.roller.coasters.presentation.search.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.roller.coasters.usecase.SearchRollerCoasters
import com.sottti.roller.coasters.presentation.search.model.SearchResult
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchBarState
import com.sottti.roller.coasters.presentation.search.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchRollerCoasters: SearchRollerCoasters,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState(SearchBarState("")))
    val state: StateFlow<SearchState> = _state.asStateFlow()

    internal val onAction: (SearchAction) -> Unit = { action -> processAction(action) }

    private fun processAction(action: SearchAction) {
        when (action) {
            is SearchAction.QueryChanged -> {
                _state.value = _state.value.copy(
                    searchBar = _state.value.searchBar.copy(query = action.query)
                )
            }
            SearchAction.SubmitQuery -> submitQuery()
        }
    }

    private fun submitQuery() {
        val query = _state.value.searchBar.query.trim()
        if (query.isEmpty()) return

        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = searchRollerCoasters(SearchQuery(query))
            val results = result.getOrElse { emptyList() }
                .map { coaster ->
                    SearchResult(
                        id = coaster.id.value,
                        imageUrl = coaster.pictures.main?.url,
                        name = coaster.name.current.value,
                        parkName = coaster.park.name.value,
                    )
                }
            _state.value = _state.value.copy(isLoading = false, results = results)
        }
    }
}