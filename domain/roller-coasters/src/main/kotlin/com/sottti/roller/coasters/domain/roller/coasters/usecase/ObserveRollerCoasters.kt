package com.sottti.roller.coasters.domain.roller.coasters.usecase

import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SortByFilter
import com.sottti.roller.coasters.domain.roller.coasters.model.TypeFilter
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveRollerCoasters @Inject constructor(
    private val rollerCoastersRepository: RollerCoastersRepository,
) {
    public operator fun invoke(
        sortByFilter: SortByFilter,
        typeFilter: TypeFilter,
    ): Flow<PagingData<RollerCoaster>> =
        rollerCoastersRepository.observeRollerCoasters(sortByFilter, typeFilter)
}