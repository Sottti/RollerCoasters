package com.sottti.roller.coasters.domain.settings.usecase.dynamicColor

import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveDynamicColor @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<Boolean> {
        return settingsRepository.observeDynamicColor()
    }
}