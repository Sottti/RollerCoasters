package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.SearchQuery
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.mapper.toResolvedMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetAppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.GetSystemMeasurementSystem
import com.sottti.roller.coasters.domain.model.Result
import javax.inject.Inject

public class SearchRollerCoasters @Inject constructor(
    private val getAppMeasurementSystem: GetAppMeasurementSystem,
    private val getSystemMeasurementSystem: GetSystemMeasurementSystem,
    private val rollerCoastersRepository: RollerCoastersRepository,
) {
    public suspend operator fun invoke(query: SearchQuery): Result<List<RollerCoaster>> {
        val measurementSystem = getAppMeasurementSystem()
            .toResolvedMeasurementSystem { getSystemMeasurementSystem() }
        return rollerCoastersRepository.searchRollerCoasters(query, measurementSystem)
    }
}
