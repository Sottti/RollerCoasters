package com.sotti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class SetAppMeasurementSystem @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(appMeasurementSystem: AppMeasurementSystem) {
        settingsRepository.setAppMeasurementSystem(appMeasurementSystem)
    }
}
