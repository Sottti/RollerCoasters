package com.sottti.roller.coasters.presentation.search.data

import androidx.lifecycle.ViewModel
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveFavouriteRollerCoasters
import com.sottti.roller.coasters.presentation.search.model.SearchAction
import com.sottti.roller.coasters.presentation.search.model.SearchBarState
import com.sottti.roller.coasters.presentation.search.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    observeFavouriteRollerCoasters: ObserveFavouriteRollerCoasters,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState(SearchBarState("Hint")))
    val state: StateFlow<SearchState> = _state.asStateFlow()

    internal val onAction: (SearchAction) -> Unit = { action -> processAction(action) }

    private fun processAction(action: SearchAction) {

    }
}