package com.sottti.roller.coasters.domain.roller.coasters.usecase

import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveResolvedMeasurementSystem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

public class ObserveRollerCoaster @Inject constructor(
    private val observeResolvedMeasurementSystem: ObserveResolvedMeasurementSystem,
    private val rollerCoastersRepository: RollerCoastersRepository,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    public operator fun invoke(
        id: RollerCoasterId,
    ): Flow<RollerCoaster> =
        observeResolvedMeasurementSystem()
            .flatMapLatest { measurementSystem ->
                rollerCoastersRepository.observeRollerCoaster(
                    id = id,
                    measurementSystem = measurementSystem,
                )
            }
}