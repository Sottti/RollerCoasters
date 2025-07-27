package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sottti.roller.coasters.domain.settings.mapper.toResolvedMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

public class ObserveResolvedMeasurementSystem @Inject constructor(
    private val getSystemMeasurementSystem: GetSystemMeasurementSystem,
    private val observeAppMeasurementSystem: ObserveAppMeasurementSystem,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    public operator fun invoke(): Flow<ResolvedMeasurementSystem> =
        observeAppMeasurementSystem()
            .map { measurementSystem ->
                measurementSystem.toResolvedMeasurementSystem { getSystemMeasurementSystem() }
            }
}
