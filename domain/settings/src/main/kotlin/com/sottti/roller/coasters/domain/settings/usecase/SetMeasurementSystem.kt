package com.sottti.roller.coasters.domain.settings.usecase

import com.sottti.roller.coasters.domain.model.MeasurementSystem
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetMeasurementSystem @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(measurementSystem: MeasurementSystem) {
        settingsRepository.setMeasurementSystem(measurementSystem)
    }
}