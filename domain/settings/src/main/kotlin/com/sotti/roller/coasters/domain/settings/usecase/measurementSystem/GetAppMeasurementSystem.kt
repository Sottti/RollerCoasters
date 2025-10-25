package com.sotti.roller.coasters.domain.settings.usecase.measurementSystem

import com.sotti.roller.coasters.domain.settings.model.measurementSystem.AppMeasurementSystem
import com.sotti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetAppMeasurementSystem @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(): AppMeasurementSystem =
        settingsRepository.getAppMeasurementSystem()
}
