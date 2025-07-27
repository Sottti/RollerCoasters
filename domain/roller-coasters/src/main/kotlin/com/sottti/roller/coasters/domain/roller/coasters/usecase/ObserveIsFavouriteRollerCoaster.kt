package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveIsFavouriteRollerCoaster @Inject constructor(
    private val repository: RollerCoastersRepository,
) {
    public operator fun invoke(id: RollerCoasterId): Flow<Boolean> =
        repository.observeIsFavouriteRollerCoaster(id)
}
