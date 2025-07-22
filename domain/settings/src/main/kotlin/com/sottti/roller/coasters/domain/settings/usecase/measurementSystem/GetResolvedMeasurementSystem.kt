package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sottti.roller.coasters.domain.settings.mapper.toResolvedMeasurementSystem
import com.sottti.roller.coasters.domain.settings.model.measurementSystem.ResolvedMeasurementSystem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

public class GetResolvedMeasurementSystem @Inject constructor(
    private val getAppMeasurementSystem: GetAppMeasurementSystem,
    private val getSystemMeasurementSystem: GetSystemMeasurementSystem,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    public suspend operator fun invoke(): ResolvedMeasurementSystem =
        getAppMeasurementSystem()
            .toResolvedMeasurementSystem { getSystemMeasurementSystem() }
}