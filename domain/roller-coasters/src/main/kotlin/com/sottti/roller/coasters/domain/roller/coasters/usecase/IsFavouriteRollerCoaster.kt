package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import javax.inject.Inject

public class IsFavouriteRollerCoaster @Inject constructor(
    private val repository: RollerCoastersRepository,
) {
    public suspend operator fun invoke(id: RollerCoasterId): Boolean =
        repository.isFavouriteRollerCoaster(id)
}