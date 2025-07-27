package com.sottti.roller.coasters.domain.roller.coasters.usecase

import androidx.paging.PagingData
import com.sottti.roller.coasters.domain.roller.coasters.model.RollerCoaster
import com.sottti.roller.coasters.domain.roller.coasters.repository.RollerCoastersRepository
import com.sottti.roller.coasters.domain.settings.usecase.measurementSystem.ObserveResolvedMeasurementSystem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

public class ObserveFavouriteRollerCoasters @Inject constructor(
    private val observeResolvedMeasurementSystem: ObserveResolvedMeasurementSystem,
    private val rollerCoastersRepository: RollerCoastersRepository,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    public operator fun invoke(): Flow<PagingData<RollerCoaster>> =
        observeResolvedMeasurementSystem()
            .flatMapLatest { measurementSystem ->
                rollerCoastersRepository.observeFavouriteRollerCoasters(measurementSystem)
            }
}
