package com.sottti.roller.coasters.domain.settings.usecase

import com.sottti.roller.coasters.domain.model.MeasurementSystem
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveMeasurementSystem @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<MeasurementSystem> {
        return settingsRepository.observeMeasurementSystem()
    }
}