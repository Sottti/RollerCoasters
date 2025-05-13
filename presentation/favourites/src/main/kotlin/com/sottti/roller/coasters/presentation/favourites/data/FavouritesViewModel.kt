package com.sottti.roller.coasters.presentation.favourites.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveFavouriteRollerCoasters
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesAction
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesState
import com.sottti.roller.coasters.presentation.favourites.ui.FavouritesEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class FavouritesViewModel @Inject constructor(
    observeFavouriteRollerCoasters: ObserveFavouriteRollerCoasters,
) : ViewModel() {
    private val _rollerCoastersFlow: Flow<PagingData<FavouritesRollerCoaster>> =
        observeFavouriteRollerCoasters()
            .map { pagingData ->
                pagingData.map { coaster ->
                    FavouritesRollerCoaster(name = coaster.name.current.value)
                }
            }
            .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(FavouritesState(_rollerCoastersFlow))
    val state: StateFlow<FavouritesState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<FavouritesEvent>()
    val events = _events.asSharedFlow()

    internal val onAction: (FavouritesAction) -> Unit = { action -> processAction(action) }

    private fun processAction(action: FavouritesAction) {

    }
}