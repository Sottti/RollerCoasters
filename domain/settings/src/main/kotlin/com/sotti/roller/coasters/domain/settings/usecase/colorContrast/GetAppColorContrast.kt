package com.sotti.roller.coasters.domain.settings.usecase.colorContrast

import com.sotti.roller.coasters.domain.settings.model.colorContrast.AppColorContrast
import com.sotti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetAppColorContrast @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(): AppColorContrast =
        settingsRepository.getAppColorContrast()
}
