package com.sottti.roller.coasters.presentation.favourites.data

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sottti.roller.coasters.domain.roller.coasters.usecase.ObserveFavouriteRollerCoasters
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import com.sottti.roller.coasters.presentation.favourites.model.FavouritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class FavouritesViewModel @Inject constructor(
    observeFavouriteRollerCoasters: ObserveFavouriteRollerCoasters,
    @VisibleForTesting testScope: CoroutineScope? = null,
) : ViewModel() {
    private val _rollerCoastersFlow: Flow<PagingData<FavouritesRollerCoaster>> =
        observeFavouriteRollerCoasters()
            .map { pagingData -> pagingData.map { rollerCoaster -> rollerCoaster.toUiModel() } }
            .let { flow -> if (testScope == null) flow.cachedIn(viewModelScope) else flow }

    private val _state = MutableStateFlow(FavouritesState(_rollerCoastersFlow))
    val state: StateFlow<FavouritesState> = _state.asStateFlow()
}