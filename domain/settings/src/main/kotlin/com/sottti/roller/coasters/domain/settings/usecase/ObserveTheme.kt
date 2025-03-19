package com.sottti.roller.coasters.domain.settings.usecase

import com.sottti.roller.coasters.domain.model.Theme
import com.sottti.roller.coasters.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<Theme> {
        return settingsRepository.observeTheme()
    }
}