package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveAppMeasurementSystem @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<AppMeasurementSystem> =
        settingsRepository.observeAppMeasurementSystem()
}
