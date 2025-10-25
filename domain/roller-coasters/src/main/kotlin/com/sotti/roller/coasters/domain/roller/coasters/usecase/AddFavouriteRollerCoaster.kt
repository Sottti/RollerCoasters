package com.sotti.roller.coasters.domain.roller.coasters.usecase

import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sotti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import javax.inject.Inject

public class AddFavouriteRollerCoaster @Inject constructor(
    private val repository: RollerCoastersRepository,
) {
    public suspend operator fun invoke(id: RollerCoasterId) {
        repository.addFavouriteRollerCoaster(id)
    }
}
