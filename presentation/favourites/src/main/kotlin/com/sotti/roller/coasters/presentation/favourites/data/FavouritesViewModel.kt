package com.sotti.roller.coasters.presentation.favourites.data

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sotti.roller.coasters.domain.roller.coasters.usecase.ObserveFavouriteRollerCoasters
import com.sotti.roller.coasters.presentation.favourites.model.FavouritesRollerCoaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class FavouritesViewModel @Inject constructor(
    observeFavouriteRollerCoasters: ObserveFavouriteRollerCoasters,
    @VisibleForTesting testScope: CoroutineScope? = null,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: Flow<PagingData<FavouritesRollerCoaster>> =
        observeFavouriteRollerCoasters()
            .map { pagingData -> pagingData.map { rollerCoaster -> rollerCoaster.toUiModel() } }
            .let { flow -> if (testScope == null) flow.cachedIn(viewModelScope) else flow }
}
