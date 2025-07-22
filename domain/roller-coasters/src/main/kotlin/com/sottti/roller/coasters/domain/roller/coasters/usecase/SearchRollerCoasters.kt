package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.model.Result
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetResolvedMeasurementSystem
import javax.inject.Inject

public class SearchRollerCoasters @Inject constructor(
    private val getResolvedMeasurementSystem: GetResolvedMeasurementSystem,
    private val rollerCoastersRepository: RollerCoastersRepository,
) {
    public suspend operator fun invoke(
        query: SearchQuery,
    ): Result<List<RollerCoaster>> =
        rollerCoastersRepository.searchRollerCoasters(
            measurementSystem = getResolvedMeasurementSystem(),
            query = query,
        )
}