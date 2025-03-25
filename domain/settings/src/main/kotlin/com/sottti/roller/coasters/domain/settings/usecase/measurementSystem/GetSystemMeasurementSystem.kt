package com.sottti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sottti.roller.coasters.domain.settings.model.measurementSystem.SystemMeasurementSystem
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetSystemMeasurementSystem @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): SystemMeasurementSystem =
        settingsRepository.getSystemMeasurementSystem()
}