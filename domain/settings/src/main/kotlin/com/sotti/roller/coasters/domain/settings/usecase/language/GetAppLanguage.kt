package com.sotti.roller.coasters.domain.settings.usecase.language

import com.sotti.roller.coasters.domain.settings.model.language.AppLanguage
import com.sotti.roller.coasters.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetAppLanguage @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public suspend operator fun invoke(): AppLanguage =
        settingsRepository.getAppLanguage()
}
