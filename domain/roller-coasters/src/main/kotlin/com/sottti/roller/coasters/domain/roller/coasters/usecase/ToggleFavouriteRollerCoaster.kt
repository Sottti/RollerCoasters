package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import javax.inject.Inject

public class ToggleFavouriteRollerCoaster @Inject constructor(
    private val addFavouriteRollerCoaster: AddFavouriteRollerCoaster,
    private val isFavouriteRollerCoaster: IsFavouriteRollerCoaster,
    private val removeFavouriteRollerCoaster: RemoveFavouriteRollerCoaster,
) {
    public suspend operator fun invoke(rollerCoasterId: RollerCoasterId) {
        when {
            isFavouriteRollerCoaster(rollerCoasterId) ->
                removeFavouriteRollerCoaster(rollerCoasterId)

            else -> addFavouriteRollerCoaster(rollerCoasterId)
        }
    }
}
